package csci5408.catme.controller;

import csci5408.catme.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aman Vishnani (aman.vishnani@dal.ca)
 */
@Controller
public class ApplicationController {

    private final AuthenticationService authenticationService;

    public ApplicationController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView greetings() {
        if(authenticationService.isAuthenticated()) {
            if(authenticationService.getLoggedInUser().getAdmin()) {
                return new ModelAndView("adminDashboard");
            }
            return new ModelAndView("redirect:courses");
        }
        return new ModelAndView("redirect:login");

    }
    
}
