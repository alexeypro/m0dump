package ${groupId}.service.impl;

import ${groupId}.dao.CandidateDAO;
import ${groupId}.dao.UserDAO;
import ${groupId}.models.User;
import ${groupId}.service.UserDetailsManager;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("userDetailsManager")
public class UserDetailsManagerImpl implements UserDetailsManager {
    public final Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User foundUser = this.userDAO.loadUserByLogin(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException("Username '" + username + "' is not found!");
        }
        return foundUser;
    }
}
