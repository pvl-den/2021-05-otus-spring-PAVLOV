package ru.otus.homework26.entity.relationalstorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "genres")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RS_Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mongo_id")
    private String mongoId;
}
