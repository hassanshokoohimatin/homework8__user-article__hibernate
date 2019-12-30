package ir.maktab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column

    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Article> articleList = new ArrayList<>();
}
