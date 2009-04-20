/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ${groupId}.validators;

import ${groupId}.models.Profile;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class ProfileValidator {

    public void validate(Profile profile, Errors errors) {
        if (!StringUtils.hasLength(profile.getFirstName())) {
            errors.rejectValue("firstName", "required", "required");
        }
        if (!StringUtils.hasLength(profile.getLastName())) {
            errors.rejectValue("lastName", "required", "required");
        }
    }
}
