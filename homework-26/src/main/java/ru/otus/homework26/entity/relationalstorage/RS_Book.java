package ru.otus.homework26.entity.relationalstorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class RS_Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "fk_genre_id"))
    private RS_Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_author_id"))
    private RS_Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private List<RS_Note> notes;

    @Override
    public String toString() {
        return "\n" +
                id + ". " + name +
                "\nАвтор: " + author.getName() +
                " Жанр: " + genre.getName() +
                "\n";
    }
}
