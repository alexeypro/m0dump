package ${groupId}.service;

import ${groupId}.models.Profile;

import java.util.Collection;

public interface CandidateManager {

    Collection<Profile> getProfiles();
    Profile loadProfile(long id);
    void saveProfile(Profile profile);
    void deleteProfile(long id);

}
