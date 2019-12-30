package ir.maktab.features.articlemanagement.usecase;

import ir.maktab.entities.Article;

public interface ListArticleById {
    Article list(Long id);
}
