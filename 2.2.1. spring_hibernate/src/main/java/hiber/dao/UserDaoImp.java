package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        if (user.getCar() != null) {
            session.persist(user.getCar());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query q1 = session.createQuery("from User u where u.car.model=:model and u.car.series=:series");
            q1.setParameter("model", car.getModel());
            q1.setParameter("series", car.getSeries());
            User user = (User) q1.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
