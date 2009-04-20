package ${groupId}.controllers;

import ${groupId}.base.Common;
import ${groupId}.service.CandidateManager;
import ${groupId}.service.MailSenderManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class CandidateController extends Common {	
    private final CandidateManager candidateManager;
    private final MailSenderManager mailSenderManager;

    @Autowired
    public CandidateController(@Qualifier("candidateManager") CandidateManager candidateManager, @Qualifier("mailSenderManager") MailSenderManager mailSenderManager) {
        this.candidateManager = candidateManager;
        this.mailSenderManager = mailSenderManager;
    }
	
    @RequestMapping("/welcome.do")
    public ModelAndView welcomeHandler(HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.logger.debug("welcomeHandler()");
        return new ModelAndView("welcome");
    }

    @RequestMapping("/candidates.do")
    public ModelMap candidatesHandler(HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("candidatesHandler()");
        return new ModelMap(this.candidateManager.getProfiles());
    }

    @RequestMapping("/adminScreen.do")
    public ModelMap adminScreenHandler(HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("adminScreenHandler()");
        return new ModelMap();
    }

    @RequestMapping("/showCandidate.do")
    public ModelMap candidateHandler(@RequestParam("candId") long candId, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("candidateHandler(" + candId + ")");
        return new ModelMap(this.candidateManager.loadProfile(candId));
    }

    @RequestMapping(value = "/deleteCandidate.do", method = RequestMethod.POST)
    public RedirectView deleteCandidateHandler(@RequestParam("candId") long candId, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("deleteCandidateHandler(" + candId + ")");
        this.candidateManager.deleteProfile(candId);
        this.setFlashMessage("success", "Candidate succesfully deleted!", request);
        return new RedirectView("candidates.html");
    }

    @RequestMapping(value = "/emailCandidate.do", method = RequestMethod.POST)
    public RedirectView emailCandidateHandler(@RequestParam("candId") long candId, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("emailCandidateHandler(" + candId + ")");
        this.mailSenderManager.sendHelloEmail(this.candidateManager.loadProfile(candId));
        this.setFlashMessage("success", "E-mail sent to candidate!", request);
        return new RedirectView("candidates.html");
    }
}
