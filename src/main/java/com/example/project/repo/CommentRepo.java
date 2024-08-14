package com.example.project.repo;

import com.example.project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends
        JpaRepository<Comment, Long> {
}
