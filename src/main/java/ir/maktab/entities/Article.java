package ir.maktab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String brief;

    @Column
    private String content;

    @Column
    private String createDate;

    @Column
    private String lastUpdateDate;

    @Column
    private String publishDate;

    @Column
    private String isPublished;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
