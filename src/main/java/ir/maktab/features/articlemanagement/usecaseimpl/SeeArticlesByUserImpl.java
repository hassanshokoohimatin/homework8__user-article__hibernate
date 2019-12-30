package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.features.articlemanagement.usecase.SeeArticlesByUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SeeArticlesByUserImpl implements SeeArticlesByUser {
    @Override
    public List<Article> articleList(Long id) {
        List<Article> articleList;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where user.id = :id");
        query.setParameter("id" , id);
        articleList = query.list();
        session.getTransaction().commit();
        return articleList;
    }
}
