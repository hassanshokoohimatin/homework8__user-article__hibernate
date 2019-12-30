package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.features.articlemanagement.usecase.ListArticleById;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ListArticleByIdImpl implements ListArticleById {
    @Override
    public Article list(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Article article = session.load(Article.class , id);
        session.getTransaction().commit();
        return article;
    }
}
