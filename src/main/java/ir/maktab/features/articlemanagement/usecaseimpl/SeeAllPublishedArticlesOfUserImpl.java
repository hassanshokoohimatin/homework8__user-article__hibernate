package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.features.articlemanagement.usecase.SeeAllPublishedArticlesOfUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SeeAllPublishedArticlesOfUserImpl implements SeeAllPublishedArticlesOfUser {
    @Override
    public List<Article> articleList(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where user.id = :id and isPublished = :i");
        query.setParameter("id" , id);
        query.setParameter("i" , "yes");
        List<Article> list = query.list();
        session.getTransaction().commit();
        return list;
    }
}
