package ${groupId}.controllers;

import ${groupId}.base.Common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class AuthController extends Common {

    @RequestMapping("/userLogin.do")
    public ModelAndView userLoginHandler(HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.logger.debug("userLoginHandler()");
        return new ModelAndView("login");
    }

    @RequestMapping("/userLogoutSuccess.do")
    public RedirectView userLogoutSuccessHandler(HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.logger.debug("userLogoutSuccessHandler()");
        this.setFlashMessage("success", "You logged out succesfully!", request);
        return new RedirectView("candidates.html");
    }
}
