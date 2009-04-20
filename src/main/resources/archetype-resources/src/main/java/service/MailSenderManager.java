package ${groupId}.service;

import ${groupId}.models.Profile;

public interface MailSenderManager {
    public Boolean sendHelloEmail(final Profile profile);
}