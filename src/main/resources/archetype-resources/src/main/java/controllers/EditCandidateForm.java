package ${groupId}.controllers;

import ${groupId}.base.Common;
import ${groupId}.models.Profile;
import ${groupId}.service.CandidateManager;
import ${groupId}.validators.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/editCandidate.do")
public class EditCandidateForm extends Common {	
    private final CandidateManager candidateManager;

    @Autowired
    public EditCandidateForm(@Qualifier("candidateManager") CandidateManager candidateManager) {
        this.candidateManager = candidateManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("candId") int candId, Model model, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("setupForm()");
        Profile profile = this.candidateManager.loadProfile(candId);
        model.addAttribute("profile", profile);
        return "editCandidate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("profile") Profile profile, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) {
        this.logger.debug("processSubmit()");
        new ProfileValidator().validate(profile, result);
        if (result.hasErrors()) {
            return "editCandidate";
        } else {
            this.candidateManager.saveProfile(profile);
            status.setComplete();
            this.setFlashMessage("success", "Candidate succesfully updated!", request);
            return "redirect:candidates.html";
        }
    }
}
