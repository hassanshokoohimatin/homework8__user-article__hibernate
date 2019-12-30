package ir.maktab.features.articlemanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Article;
import ir.maktab.features.articlemanagement.usecase.EditArticle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class EditArticleImpl implements EditArticle {
    @Override
    public void edit(Article article) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

         Article beforeEditArticle = session.load(Article.class , article.getId());
         beforeEditArticle.setTitle(article.getTitle());
         beforeEditArticle.setBrief(article.getBrief());
         beforeEditArticle.setContent(article.getContent());
         beforeEditArticle.setIsPublished(article.getIsPublished());
         beforeEditArticle.setCategory(article.getCategory());
         beforeEditArticle.setLastUpdateDate(new Date().toString());

         session.update(beforeEditArticle);
         session.getTransaction().commit();
    }
}
