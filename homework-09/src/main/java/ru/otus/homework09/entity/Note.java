package ru.otus.homework09.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notes")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note_text", nullable = false)
    private String noteText;

    @Column(name = "note_date", nullable = false)
    private Date noteDate;

    @Column(name = "note_author")
    private String noteAuthor;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_book_id"))
    private Book book;

}
