package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.features.articlemanagement.usecase.EnterNewArticleByUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EnterNewArticleByUserImpl implements EnterNewArticleByUser {
    @Override
    public void enterArticle(Article article) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(article);
        session.getTransaction().commit();
    }
}
