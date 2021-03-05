package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.Article;
import pl.maprzybysz.bestrongerapp.Entity.ArticleCategories;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    public Optional<List<Article>> getArticleByCategory(ArticleCategories categories);
}
