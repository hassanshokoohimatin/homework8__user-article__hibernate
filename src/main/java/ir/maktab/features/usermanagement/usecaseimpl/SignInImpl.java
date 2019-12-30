package ir.maktab.features.usermanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.User;
import ir.maktab.features.usermanagement.usecase.SignIn;
import ir.maktab.share.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SignInImpl implements SignIn {
    @Override
    public User signIn(String username, String password) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where username = :u and password = :p");
        query.setParameter("u" , username);
        query.setParameter("p" , password);
        List result = query.list();
        session.getTransaction().commit();
        if (result.size() != 0)
            return (User)result.get(0);
        else
            return null;
    }
}
