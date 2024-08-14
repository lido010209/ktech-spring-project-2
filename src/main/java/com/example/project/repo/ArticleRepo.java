package com.example.project.repo;

import com.example.project.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends
        JpaRepository<Article, Long> {
}
