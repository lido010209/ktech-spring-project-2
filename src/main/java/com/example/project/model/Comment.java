package com.example.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String content;
    @Setter
    private String writer;
    @Setter
    private String password;

    @Setter
    @ManyToOne
    private Article article;
}
