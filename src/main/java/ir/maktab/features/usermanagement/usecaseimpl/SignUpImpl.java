package ir.maktab.features.usermanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.User;
import ir.maktab.features.usermanagement.usecase.SignUp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SignUpImpl implements SignUp {
    @Override
    public void signUp(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }
}
