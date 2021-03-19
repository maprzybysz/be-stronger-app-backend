package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.Article;
import pl.maprzybysz.bestrongerapp.Entity.ArticleCategories;
import pl.maprzybysz.bestrongerapp.Entity.DTO.ArticleDTO;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.ArticleMapper;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.MealMapper;
import pl.maprzybysz.bestrongerapp.Entity.Meal;
import pl.maprzybysz.bestrongerapp.exception.ArticleDoesNotExistException;
import pl.maprzybysz.bestrongerapp.exception.ArticlesNotFoundException;
import pl.maprzybysz.bestrongerapp.exception.MealDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.repository.ArticleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesServiceImpl implements ArticleService {

    private ArticleRepo articleRepo;

    @Autowired
    public ArticlesServiceImpl(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public List<Article> getArticlesByCategory(String category) {
        ArticleCategories categories = ArticleCategories.valueOf(category);
        Optional<List<Article>> articles = articleRepo.getArticleByCategory(categories);
        if (articles.isEmpty()) {
            return List.of();
        } else {
            return articles.get();
        }
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Optional<Article> article = articleRepo.findById(id);
        if (article.isEmpty()) {
            throw new ArticleDoesNotExistException(id);
        } else {
            return ArticleMapper.articleToArticleDTO().map(article.get(), ArticleDTO.class);
        }
    }
}

