package com.metadata.metadata_crud.entity.sourcesys;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sourcesystem")
public class sourcesystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;
    @Column
    private String keyy;
    @Column
    private String Value;
    @Column
    private String username;

}
