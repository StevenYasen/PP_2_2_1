package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        if (user.getCar() != null) {
            session.persist(user.getCar());
        }
    }


    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        try {
            return sessionFactory.getCurrentSession().createQuery("from User u where u.car.model=:model" +
                            " and u.car.series=:series", User.class)
                    .setParameter("model", car.getModel())
                    .setParameter("series", car.getSeries())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
