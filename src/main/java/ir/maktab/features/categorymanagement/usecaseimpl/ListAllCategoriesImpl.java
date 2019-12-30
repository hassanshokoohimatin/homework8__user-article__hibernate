package ir.maktab.features.categorymanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Category;
import ir.maktab.features.categorymanagement.usecase.ListAllCategories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ListAllCategoriesImpl implements ListAllCategories {
    @Override
    public List<Category> categoryList() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Category ");
        List<Category> categoryList = query.list();
        session.getTransaction().commit();
        return categoryList;
    }
}
