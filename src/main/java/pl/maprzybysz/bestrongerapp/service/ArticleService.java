package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.Entity.Article;
import pl.maprzybysz.bestrongerapp.Entity.DTO.ArticleDTO;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticlesByCategory(String category);
    public ArticleDTO getArticleById(Long id);
}
