package ${groupId}.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Common {
    public final Log logger = LogFactory.getLog(getClass());

    public void setFlashMessage(String messageName, String messageValue, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object previousMessageObj = session.getAttribute(messageName);
        FlashMessage fm = null;
        if ((previousMessageObj != null) && (previousMessageObj instanceof FlashMessage)) {
            fm = (FlashMessage) previousMessageObj;
            fm.setMessage(messageValue);
        } else {
            fm = new FlashMessage(messageValue);
        }
        session.setAttribute(messageName, fm);
    }
}
