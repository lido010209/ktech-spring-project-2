package com.example.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;
    @Setter
    private String password;

    @Setter
    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;

    @Setter
    @ManyToOne
    private Board board;
}
