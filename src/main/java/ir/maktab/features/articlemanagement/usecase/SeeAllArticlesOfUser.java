package ir.maktab.features.articlemanagement.usecase;

import ir.maktab.entities.Article;

import java.util.List;

public interface SeeAllArticlesOfUser {
    List<Article> articleList(Long id);
}
