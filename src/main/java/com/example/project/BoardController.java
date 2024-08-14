package com.example.project;

import com.example.project.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;
    private final CommentService commentService;

    public BoardController(BoardService boardService, ArticleService articleService, CommentService commentService) {
        this.boardService = boardService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("articleList", articleService.articleList());

        return "board/home.html";
    }
    @GetMapping("{id}")
    public String readOne(@PathVariable("id") Long id, Model model){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("board", boardService.board(id));
        model.addAttribute("boardId", id);
        return "board/read.html";
    }

    @GetMapping("article")
    public String createArticle(
            Model model){
        model.addAttribute("boardList", boardService.boardList());
        return "board/createArticle.html";
    }
    @PostMapping("article")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId){
        Article newArticle=articleService.createArticle(title, content, writer, password, boardId);
        return String.format("redirect:/articles/%s",newArticle.getId());
    }

    //create by board
    @GetMapping("/{id}/article")
    public String createArticleByBoard(
            @PathVariable("id")
            Long boardId,
            Model model){
        model.addAttribute("boardId", boardId);
        model.addAttribute("boardList", boardService.boardList());
        return "board/createArticleByBoard.html";
    }
    @PostMapping("/{id}/article")
    public String create(
            @PathVariable("id")
            Long boardId,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password){
        Article newArticle=articleService.createArticle(title, content, writer, password, boardId);
        return String.format("redirect:/boards/%s/article/%s", boardId, newArticle.getId());
    }

    //readOne
    @GetMapping("/{id}/article/{articleId}")
    public String readByArticle(
            @PathVariable("id")
            Long id,
            @PathVariable("articleId")
            Long articleId,
            Model model
    ){
        model.addAttribute("boardId", id);
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("article", articleService.article(articleId));
        model.addAttribute("board", boardService.board(id));
        model.addAttribute("index", boardService.targetIdx(id, articleId));
        List<Article> articleList = boardService.board(id).getArticleList();
        model.addAttribute("maxIndex",articleList.size()-1);
        model.addAttribute("articleIds", boardService.articleIds(id));
        model.addAttribute("hashTags", articleService.listHashTagsByArticle(articleId));
        return "board/readByArticle.html";
    }

    @GetMapping("{id}/article/{articleId}/edit")
    public String updateArticle(
            @PathVariable("id") Long id,
            @PathVariable("articleId") Long articleId,
            Model model){
        model.addAttribute("board", boardService.board(id));
        model.addAttribute("boardId", id);
        model.addAttribute("article", articleService.article(articleId));
        model.addAttribute("boardList", boardService.boardList());
        return "board/editByArticle.html";
    }
    @PostMapping("{id}/article/{articleId}/update")
    public String update(
            @PathVariable("id")
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ){

        articleService.updateArticle(articleId, title, content, password, boardId);
        return String.format("redirect:/boards/%s/article/%s", boardId, articleId);
    }

    @PostMapping("{id}/article/{articleId}/delete")
    public String delete(
            @PathVariable("id")
            Long id,
            @PathVariable("articleId") Long articleId,
            @RequestParam("password")
            String password
    ){
        articleService.deleteArticle(articleId, password);
        return String.format("redirect:/boards/%s", id);
    }

    //New comments
    @GetMapping("/{id}/article/{articleId}/comment")
    public String createCommentView(
            @PathVariable("id")
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            Model model){
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("board", boardService.board(boardId));
        model.addAttribute("boardId", boardId);
        model.addAttribute("article", articleService.article(articleId));

        return "comment/createByBoard.html";
    }
    @PostMapping("/{id}/article/{articleId}/comment")
    public String createComment(
            @PathVariable("id")
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("password")
            String password
    ){
        commentService.createComment(content, writer, password, articleId);
        return String.format("redirect:/boards/%s/article/%s", boardId,articleId);
    }
    @PostMapping("{id}/article/{articleId}/comment/{commentId}/delete")
    public String deleteComment(
            @PathVariable("id")
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("password")
            String password
    ){
        commentService.deleteComment(commentId, password);
        return String.format("redirect:/boards/%s/article/%s", boardId,articleId);
    }


    //Search
    @PostMapping("/search")
    public String searchArticle(
            @RequestParam("keyWord")
            String keyWord,
            @RequestParam("searchBy")
            String searchBy
    ){

        if (searchBy.equals("title")) {
            articleService.articlesWithKwTitle(keyWord);
        }
        else if (searchBy.equals("content")) {
            articleService.articlesWithKwContent(keyWord);
        }
        return String.format("redirect:/boards/result/%s-%s", searchBy, keyWord);
    }
    //Result page for search if results exist
    @GetMapping("/result/{searchBy}-{keyWord}")
    public String result(
            @PathVariable("searchBy")
            String searchBy,
            @PathVariable("keyWord")
            String keyWord,
            Model model){
        model.addAttribute("searchBy", searchBy);
        model.addAttribute("keyWord", keyWord);
        model.addAttribute("articleTitle", articleService.articlesWithKwTitle(keyWord));
        model.addAttribute("articleContent", articleService.articlesWithKwContent(keyWord));
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("articleList", articleService.articleList());
        return "board/resultSearch.html";
    }

    //Hashtag by Board
    @GetMapping("/{id}/article/{articleId}/{hashTag}")
    public String hashTagByBoard(
            @PathVariable("id")
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("hashTag")
            String hashTag,
            Model model
    ){
        model.addAttribute("articleId", articleId);
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("boardId", boardId);
        model.addAttribute("hashTag", hashTag);
        model.addAttribute("articleListTarget", boardService.containHashTag(hashTag, boardId));
        return "board/hashtagByBoard.html";
    }

    //Search
    @PostMapping("/{id}/search")
    public String searchArticleByBoard(
            @PathVariable("id")
            Long boardId,
            @RequestParam("keyWord")
            String keyWord,
            @RequestParam("searchBy")
            String searchBy
    ){
        if (searchBy.equals("title")) {
            boardService.articlesWithKwTitle(keyWord, boardId);
        }
        else if (searchBy.equals("content")) {
            boardService.articlesWithKwContent(keyWord, boardId);
        }
        return String.format("redirect:/boards/%s/result/%s-%s", boardId, searchBy, keyWord);
    }
    //Result page for search if results exist
    @GetMapping("/{id}/result/{searchBy}-{keyWord}")
    public String resultByBoard(
            @PathVariable("id")
            Long boardId,
            @PathVariable("searchBy")
            String searchBy,
            @PathVariable("keyWord")
            String keyWord,
            Model model){
        model.addAttribute("searchBy", searchBy);
        model.addAttribute("keyWord", keyWord);
        model.addAttribute("articleTitle", boardService.articlesWithKwTitle(keyWord, boardId));
        model.addAttribute("articleContent", boardService.articlesWithKwContent(keyWord, boardId));
        model.addAttribute("boardId", boardId);
        model.addAttribute("boardList", boardService.boardList());
        model.addAttribute("articleList", articleService.articleList());
        return "board/resultSearchByBoard.html";
    }

}
