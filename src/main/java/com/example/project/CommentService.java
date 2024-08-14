package com.example.project;

import com.example.project.model.Comment;
import com.example.project.repo.ArticleRepo;
import com.example.project.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final ArticleRepo articleRepo;

    public CommentService(CommentRepo commentRepo, ArticleRepo articleRepo) {
        this.commentRepo = commentRepo;
        this.articleRepo = articleRepo;
    }

    public List<Comment> commentList(){
        return commentRepo.findAll();
    }
    public Comment comment(Long id){
        return commentRepo.findById(id).orElseThrow();
    }

    public Comment createComment(
            String content, String writer, String password, Long articleId
    ){
        Comment comment= new Comment();
        comment.setContent(content); comment.setWriter(writer);
        comment.setPassword(password); comment.setArticle(articleRepo.findById(articleId).orElseThrow());
        commentRepo.save(comment);
        return comment;
    }

    public void deleteComment(Long id, String password){
        Comment targetComment= commentRepo.findById(id).orElseThrow();
        if (password.equals(targetComment.getPassword())){
            commentRepo.delete(targetComment);
        }
    }
}
