package ir.maktab.features.usermanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.User;
import ir.maktab.features.usermanagement.usecase.ListAllUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ListAllUsersImpl implements ListAllUsers {
    @Override
    public List<User> userList() {
        List<User> userList;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        userList = query.list();
        session.getTransaction().commit();
        return userList;
    }
}
