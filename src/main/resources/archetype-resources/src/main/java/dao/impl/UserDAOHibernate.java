package ${groupId}.dao.impl;

import ${groupId}.dao.UserDAO;
import ${groupId}.models.User;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOHibernate implements UserDAO {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Collection<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User user order by user.login").list();
    }

    @Transactional(readOnly = true)
    public User loadUser(long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    public void deleteUser(long id) {
        sessionFactory.getCurrentSession().delete(this.loadUser(id));
    }

    @Transactional(readOnly = true)
    public User loadUserByLogin(String login) throws DataAccessException {
        return (User) sessionFactory.getCurrentSession().createQuery("from User user where user.login = ?").setString(0, login).uniqueResult();
    }
}
