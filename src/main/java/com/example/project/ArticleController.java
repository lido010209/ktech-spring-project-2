package com.example.project;

import com.example.project.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final BoardService boardService;

    public ArticleController(ArticleService articleService, CommentService commentService, BoardService boardService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.boardService = boardService;
    }


    @GetMapping("{id}")
    public String readOne(@PathVariable("id") Long id,  Model model){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("article", articleService.article(id));
        model.addAttribute("hashTags", articleService.listHashTagsByArticle(id));
        model.addAttribute("index", articleService.idxTarget(id));
        List<Long> ids = articleService.articleIds();
        model.addAttribute("maxIndex", ids.size()-1);
        model.addAttribute("articleIds", ids);

        return "article/read.html";
    }

    @GetMapping("{id}/edit")
    public String updateArticle(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", articleService.article(id));
        model.addAttribute("boardList", boardService.boardList());
        return "article/edit.html";
    }
    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ){
        articleService.updateArticle(id, title, content, password, boardId);
        return String.format("redirect:/articles/%s", id);
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id,
            @RequestParam("password")
            String password
    ){
        articleService.deleteArticle(id, password);
        return "redirect:/boards";
    }

    @GetMapping("{id}/comments")
    public String createCommentView(
            @PathVariable("id")
            Long articleId,
            Model model){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("article", articleService.article(articleId));

        return "comment/create.html";
    }
    @PostMapping("{id}/comments")
    public String createComment(
            @PathVariable("id")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password
    ){
        commentService.createComment(content, writer, password, articleId);
        return String.format("redirect:/articles/%s", articleId);
    }

    @PostMapping("{id}/comments/{commentId}/delete")
    public String deleteComment(
            @PathVariable("id")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("password")
            String password
    ){
        commentService.deleteComment(commentId, password);
        return String.format("redirect:/articles/%s", articleId);
    }

    @GetMapping("/{id}/{hashTag}")
    public String hashTag(
            @PathVariable("id")
            Long id,
            @PathVariable("hashTag")
            String hashTag,
            Model model
    ){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("id", id);
        model.addAttribute("hashTag", hashTag);
        model.addAttribute("articleListTarget", articleService.containHashTag(hashTag));
        return "article/hashtag.html";
    }

}
