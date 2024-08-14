package com.example.project;

import com.example.project.model.Article;
import com.example.project.model.Board;
import com.example.project.repo.ArticleRepo;
import com.example.project.repo.BoardRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepo boardRepo;
    private final ArticleService articleService;

    public BoardService(BoardRepo boardRepo, ArticleService articleService) {
        this.boardRepo = boardRepo;
        this.articleService = articleService;
    }
    public List<Board> boardList(){
        return boardRepo.findAll();
    }
    public Board board(Long id){
        return boardRepo.findById(id).orElseThrow();
    }

    public List<Long> articleIds(Long id){
        List<Long> Ids = new ArrayList<>();
        for (Article article: this.board(id).getArticleList()){
            Ids.add(article.getId());
        }
        return Ids;
    }
    public int targetIdx(Long id, Long articleId){
        int target=-1;
        List<Article> articleList= this.board(id).getArticleList();
        for (int i = 0; i < articleList.size(); i++) {
            if (articleId.equals(articleList.get(i).getId())){
                target=i; break;
            }
        }
        return target;
    }

    //Hashtag
//    public List<String> listHashTagsByArticle(Long articleId, Long boardId){
//        List<String> hashTags= new ArrayList<>();
//
//        Article article= this.board(boardId);
//        String content = article.getContent();
//        String[] words = content.split(" ");
//        for (String word: words){
//            if (word.startsWith("#")) hashTags.add(word);
//        }
//        return hashTags;
//    }

    public List<Article> containHashTag(String hashTag, Long boardId){
        List<Article> articleListTarget = new ArrayList<>();
        for (Article article: this.board(boardId).getArticleList()){
            if (articleService.listHashTagsByArticle(article.getId()).contains(hashTag)){
                articleListTarget.add(article);
            }
        }
        return articleListTarget;
    }

    //Search
    public List<Article> articlesWithKwContent (String keyWord, Long boardId){
        List<Article> articleTarget = new ArrayList<>();
        for (Article article: this.board(boardId).getArticleList()){
            if (article.getContent().toLowerCase().contains(keyWord)){
                articleTarget.add(article);
            }
        }
        return articleTarget;
    }

    public List<Article> articlesWithKwTitle (String keyWord, Long boardId){
        List<Article> articleTarget = new ArrayList<>();
        for (Article article: this.board(boardId).getArticleList()){
            if (article.getTitle().toLowerCase().contains(keyWord)){
                articleTarget.add(article);
            }
        }
        return articleTarget;
    }



}
