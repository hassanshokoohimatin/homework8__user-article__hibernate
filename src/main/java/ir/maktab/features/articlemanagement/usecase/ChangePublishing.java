package ir.maktab.features.articlemanagement.usecase;

import ir.maktab.entities.User;

public interface ChangePublishing {
    void changePublishing(Long articleId , User user);
}
