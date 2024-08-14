package com.example.project;

import com.example.project.model.Article;
import com.example.project.repo.ArticleRepo;
import com.example.project.repo.BoardRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleService {
    private final ArticleRepo articleRepo;
    private final BoardRepo boardRepo;

    public ArticleService(ArticleRepo articleRepo, BoardRepo boardRepo) {
        this.articleRepo = articleRepo;
        this.boardRepo = boardRepo;
    }

    public List<Article> articleList(){
        return articleRepo.findAll();
    }
    public Article article(Long id){
        return articleRepo.findById(id).orElseThrow();
    }

    public Article createArticle(
            String title, String content, String writer, String password, Long boardId
    ){
        Article article = new Article();
        article.setTitle(title); article.setContent(content); article.setWriter(writer);
        article.setPassword(password); article.setBoard(boardRepo.findById(boardId).orElseThrow());
        articleRepo.save(article);
        return article;
    }

    public void updateArticle(
            Long id, String title, String content, String password, Long boardId
    ){
        Article article = articleRepo.findById(id).orElseThrow();
        if (password.equals(article.getPassword())){
            article.setTitle(title); article.setContent(content); article.setBoard(boardRepo.findById(boardId).orElseThrow());
            articleRepo.save(article);
        }
    }
    public void deleteArticle(Long id, String password){
        Article targetArticle = articleRepo.findById(id).orElseThrow();
        if (password.equals(targetArticle.getPassword())) articleRepo.deleteById(id);
    }


    public List<String> listHashTagsByArticle(Long articleId){
        List<String> hashTags= new ArrayList<>();

        Article article= this.article(articleId);
        String content = article.getContent();
        String[] words = content.split(" ");
        for (String word: words){
            if (word.startsWith("#")) hashTags.add(word);
        }
        return hashTags;
    }

    public List<Article> containHashTag(String hashTag){
        List<Article> articleListTarget = new ArrayList<>();
        for (Article article: this.articleList()){
            if (this.listHashTagsByArticle(article.getId()).contains(hashTag)){
                articleListTarget.add(article);
            }
        }
        return articleListTarget;
    }

    //Search
    public List<Article> articlesWithKwContent (String keyWord){
        List<Article> articleTarget = new ArrayList<>();
        for (Article article: this.articleList()){
            if (article.getContent().toLowerCase().contains(keyWord)){
                articleTarget.add(article);
            }
        }
        return articleTarget;
    }

    public List<Article> articlesWithKwTitle (String keyWord){
        List<Article> articleTarget = new ArrayList<>();
        for (Article article: this.articleList()){
            if (article.getTitle().toLowerCase().contains(keyWord)){
                articleTarget.add(article);
            }
        }
        return articleTarget;
    }

    public List<Long> articleIds(){
        List<Long> ids= new ArrayList<>();
        for (Article article:this.articleList()){
            ids.add(article.getId());
        }
        return ids;
    }
    public int idxTarget(Long id){
        int idx=-1;
        for (int i = 0; i < this.articleIds().size(); i++) {

            if (this.articleIds().get(i).equals(id)){
                idx=i; break;
            }
        }
        return idx;
    }
}
