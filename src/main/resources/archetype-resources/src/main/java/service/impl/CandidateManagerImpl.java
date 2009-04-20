package ${groupId}.service.impl;

import ${groupId}.dao.CandidateDAO;
import ${groupId}.models.Profile;
import ${groupId}.service.CandidateManager;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("candidateManager")
public class CandidateManagerImpl implements CandidateManager {

    @Autowired
    @Qualifier("candidateDAO")
    private CandidateDAO candidateDAO;

    public Collection<Profile> getProfiles() {
        return this.candidateDAO.getProfiles();
    }

    public Profile loadProfile(long id) {
        return this.candidateDAO.loadProfile(id);
    }

    public void saveProfile(Profile profile) {
        this.candidateDAO.saveProfile(profile);
    }

    public void deleteProfile(long id) {
        this.candidateDAO.deleteProfile(id);
    }

}
