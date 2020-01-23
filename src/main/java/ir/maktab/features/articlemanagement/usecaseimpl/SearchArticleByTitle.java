package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SearchArticleByTitle {

    public static List<Article> articles(String title){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where title = :title");
        query.setParameter("title" , title);
        List<Article> articles = query.list();
        session.getTransaction().commit();
        return articles;
    }
}
