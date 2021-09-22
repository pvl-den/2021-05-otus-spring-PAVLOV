package ru.otus.homework26.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework26.entity.mongo.Author;
import ru.otus.homework26.entity.mongo.Book;
import ru.otus.homework26.entity.mongo.Genre;
import ru.otus.homework26.entity.relationalstorage.RS_Author;
import ru.otus.homework26.entity.relationalstorage.RS_Book;
import ru.otus.homework26.entity.relationalstorage.RS_Genre;
import ru.otus.homework26.service.AuthorService;
import ru.otus.homework26.service.BookService;
import ru.otus.homework26.service.GenreService;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String MIGRATE_BOOK_CATALOGUE_JOB_NAME = "migrateBookCatalogueJob";


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public MongoItemReader<Author> mongoAuthorReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Author>()
                .name("mongoAuthorReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Author.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<Genre> mongoGenreReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Genre>()
                .name("mongoGenreReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Genre.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<Book> mongoBookReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<Book>()
                .name("mongoBookReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, RS_Author> authorProcessor(AuthorService authorService) {
        return authorService::convert;
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, RS_Genre> genreProcessor(GenreService genreService) {
        return genreService::convert;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, RS_Book> bookProcessor(BookService bookService) {
        return bookService::convert;
    }

    @StepScope
    @Bean
    public JpaItemWriter<RS_Author> jpaAuthorWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<RS_Author>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<RS_Genre> jpaGenreWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<RS_Genre>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<RS_Book> jpaBookWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<RS_Book>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Job migrateBookCatalogueJob(@Qualifier("splitFlow") Flow splitFlow,
                                       @Qualifier("convertBooks") Step booksConverter) {
        return jobBuilderFactory.get(MIGRATE_BOOK_CATALOGUE_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(splitFlow)
                .next(booksConverter)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Flow splitFlow(@Qualifier("taskExecutor") TaskExecutor taskExecutor,
                          @Qualifier("convertAuthorsFlow") Flow convertAuthorsFlow,
                          @Qualifier("convertGenresFlow") Flow convertGenresFlow) {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor)
                .add(convertAuthorsFlow, convertGenresFlow)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Flow convertAuthorsFlow(@Qualifier("convertAuthors") Step authorsConverter) {
        return new FlowBuilder<SimpleFlow>("convertAuthorsFlow")
                .start(authorsConverter)
                .build();
    }

    @Bean
    public Flow convertGenresFlow(@Qualifier("convertGenres") Step genresConverter) {
        return new FlowBuilder<SimpleFlow>("convertGenresFlow")
                .start(genresConverter)
                .build();
    }


    @Bean
    public Step convertAuthors(JpaItemWriter<RS_Author> jpaAuthorWriter, ItemReader<Author> mongoAuthorReader,
                               ItemProcessor<Author, RS_Author> authorProcessor) {
        return stepBuilderFactory.get("convertAuthors")
                .<Author, RS_Author>chunk(CHUNK_SIZE)
                .reader(mongoAuthorReader)
                .processor(authorProcessor)
                .writer(jpaAuthorWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(Author o) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Author o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Author o, RS_Author o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Author o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    @Bean
    public Step convertGenres(JpaItemWriter<RS_Genre> jpaGenreWriter, ItemReader<Genre> mongoGenreReader,
                              ItemProcessor<Genre, RS_Genre> genreProcessor) {
        return stepBuilderFactory.get("convertGenres")
                .<Genre, RS_Genre>chunk(CHUNK_SIZE)
                .reader(mongoGenreReader)
                .processor(genreProcessor)
                .writer(jpaGenreWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(Genre o) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Genre o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Genre o, RS_Genre o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Genre o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }

    @Bean
    public Step convertBooks(JpaItemWriter<RS_Book> jpaGenreWriter, ItemReader<Book> mongoGenreReader,
                             ItemProcessor<Book, RS_Book> genreProcessor) {
        return stepBuilderFactory.get("convertBooks")
                .<Book, RS_Book>chunk(CHUNK_SIZE)
                .reader(mongoGenreReader)
                .processor(genreProcessor)
                .writer(jpaGenreWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(Book o) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Book o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Book o, RS_Book o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Book o, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }
}