package ir.maktab.features.usermanagement.usecaseimpl;

import ir.maktab.config.HibernateUtil;
import ir.maktab.entities.User;
import ir.maktab.features.usermanagement.usecase.ChangePassword;
import ir.maktab.share.AuthenticationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ChangePasswordImpl implements ChangePassword {
    @Override
    public void changePassword(String password) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.load(User.class , AuthenticationService.getInstance().getLoginUser().getId());
        user.setPassword(password);
        session.update(user);
        session.getTransaction().commit();
    }
}
