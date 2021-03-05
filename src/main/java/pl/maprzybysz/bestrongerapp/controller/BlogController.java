package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maprzybysz.bestrongerapp.Entity.Article;
import pl.maprzybysz.bestrongerapp.Entity.DTO.ArticleDTO;
import pl.maprzybysz.bestrongerapp.exception.ArticleDoesNotExistException;
import pl.maprzybysz.bestrongerapp.exception.ArticlesNotFoundException;
import pl.maprzybysz.bestrongerapp.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private ArticleService articleService;

    @Autowired
    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getArticlesByCategory(@PathVariable String category){
        try{
            List<Article> articles = articleService.getArticlesByCategory(category);
            return ResponseEntity.ok(articles);
        }catch (ArticlesNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }

    }
    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id){
        try{
            ArticleDTO articles = articleService.getArticleById(id);
            return ResponseEntity.ok(articles);
        }catch (ArticleDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }

    }
}
