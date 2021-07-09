merge into authors (id, `name`)
values (1, 'Джеймс Клавелл');
merge into authors (id, `name`)
values (2, 'Эрих Мария Ремарк');

merge into genres (id, `name`)
values (1, 'Приключения');
merge into genres (id, `name`)
values (2, 'Роман');

merge into books (id,`name`, author_id, genre_id)
values (1, 'Сегун', (select id from authors a where a.name = 'Джеймс Клавелл'),
        (select id from genres g where g.name = 'Приключения'));

merge into books (id, `name`, author_id, genre_id)
values (2, 'Тай-пен', (select id from authors a where a.name = 'Джеймс Клавелл'),
        (select id from genres g where g.name = 'Приключения'));

merge into books (id,`name`, author_id, genre_id)
values (3, 'Три товарища', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from genres g where g.name = 'Роман'));

merge into books (id, `name`, author_id, genre_id)
values (4, 'Триумфайльная арка', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from genres g where g.name = 'Роман'));

merge into notes (id, note_text, note_date, note_author, book_id)
values (1, 'отличная книга','2021-07-01','Павлов Д.В.',1);

merge into notes (id, note_text, note_date, note_author, book_id)
values (2, 'одна из лучших книг','2021-07-01','Павлов Д.В.',2);