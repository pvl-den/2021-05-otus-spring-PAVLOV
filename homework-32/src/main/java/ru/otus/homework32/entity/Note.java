package ru.otus.homework32.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notes", schema = "public")
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

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_id"), nullable = false)
    private Book book;

}
