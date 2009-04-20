package ${groupId}.dao.impl;

import ${groupId}.dao.CandidateDAO;
import ${groupId}.models.Profile;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("candidateDAO")
@Transactional
public class CandidateDAOHibernate implements CandidateDAO {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public Collection<Profile> getProfiles() {
        return sessionFactory.getCurrentSession().createQuery("from Profile profile order by profile.firstName, profile.lastName").list();
    }

    @Transactional(readOnly = true)
    public Profile loadProfile(long id) {
        return (Profile) sessionFactory.getCurrentSession().get(Profile.class, id);
    }

    public void saveProfile(Profile profile) {
        sessionFactory.getCurrentSession().merge(profile);
    }

    public void deleteProfile(long id) {
        sessionFactory.getCurrentSession().delete(this.loadProfile(id));
    }
}
