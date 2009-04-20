package ${groupId}.dao;

import ${groupId}.models.User;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

public interface UserDAO extends DAO {
    Collection<User> getUsers() throws DataAccessException;
    User loadUser(long id) throws DataAccessException;
    User loadUserByLogin(String login) throws DataAccessException;
    void saveUser(User user) throws DataAccessException;
    void deleteUser(long id) throws DataAccessException;
}
