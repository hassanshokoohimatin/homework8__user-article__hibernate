package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.entities.User;
import ir.maktab.features.articlemanagement.usecase.ChangePublishing;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class ChangePublishingImpl implements ChangePublishing {
    Scanner scanner = new Scanner(System.in);
    @Override
    public void changePublishing(Long articleId , User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Article where user.id = :id");
        query.setParameter("id" , user.getId());
        List<Article> articles = query.list();
        for (Article a : articles){
            if (articleId == a.getId()){
                System.out.print("Enter publishing situation : (yes or no) : ");
                String isPublished = scanner.next();
                a.setIsPublished(isPublished);
                session.update(a);
                session.getTransaction().commit();
                break;
            }else
                System.out.println("You can not access this article...it is not yours...");
        }
        session.getTransaction().commit();
    }
}
