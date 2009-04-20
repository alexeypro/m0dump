package ${groupId}.dao;

import ${groupId}.models.Profile;

import java.util.Collection;
import org.springframework.dao.DataAccessException;

public interface CandidateDAO extends DAO {
    Collection<Profile> getProfiles() throws DataAccessException;
    Profile loadProfile(long id) throws DataAccessException;
    void saveProfile(Profile profile) throws DataAccessException;
    void deleteProfile(long id) throws DataAccessException;
}
