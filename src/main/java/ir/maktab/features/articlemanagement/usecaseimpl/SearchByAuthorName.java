package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SearchByAuthorName {

    public static List<Article> articles(String name){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where user.username = :name");
        query.setParameter("name" , name);
        List<Article> articles = query.list();
        session.getTransaction().commit();
        return articles;
    }
}
