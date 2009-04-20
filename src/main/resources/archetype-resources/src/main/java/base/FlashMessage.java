package ${groupId}.base;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FlashMessage implements Serializable {
    public final Log logger = LogFactory.getLog(getClass());
    private String message = "";

    public FlashMessage(String message) {
        logger.debug("New FlashMessage created with value '" + message + "'!");
        this.message = message;
    }

    public String getMessage() {
        return this.toString(); // go through the reseting step
    }

    public void setMessage(String message) {
        logger.debug("Existing FlashMessage updated value to '" + message + "'!");
        this.message = message;
    }

    @Override
    public String toString() {
        logger.debug("Accessing FlashMessage message '" + message + "' and resetting it!");
        String result = this.message;
        this.message = "";  // reseting it
        return result;
    }
}
