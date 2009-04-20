package ${groupId}.service.impl;

import ${groupId}.service.MailSenderManager;
import ${groupId}.models.Profile;

import java.util.HashMap;
import java.util.Map;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;



@Service("mailSenderManager")
public class MailSenderManagerImpl implements MailSenderManager {
    public final Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("velocityEngine")
    private VelocityEngine velocityEngine;

    @Autowired
    @Qualifier("emailFromAddress")
    private String emailFromAddress;

    @Autowired
    @Qualifier("emailSubjectLine")
    private String emailSubjectLine;

    public Boolean sendHelloEmail(final Profile profile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.emailFromAddress);
            helper.setSubject(this.emailSubjectLine);
            helper.setTo(profile.getEmail());
            Map model = new HashMap();
            model.put("profile", profile);
            String textBody = VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, "sendHelloEmail.vm", model);
            helper.setText(textBody, true);
            mailSender.send(message);
        } catch (Exception ex) {
            logger.error(ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
