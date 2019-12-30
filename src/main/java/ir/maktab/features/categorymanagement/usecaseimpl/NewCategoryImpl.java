package ir.maktab.features.categorymanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.Category;
import ir.maktab.features.categorymanagement.usecase.NewCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NewCategoryImpl implements NewCategory {
    @Override
    public void createCategory(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
    }
}
