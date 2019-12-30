package ir.maktab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String username;

    @Column
    private String nationalCode;

    @Column
    private String birthday;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Article> articleList = new ArrayList<>();

}
