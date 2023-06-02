package Blogify.controllers;

import Blogify.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String main(){
        return "user/main";
    }
    @ModelAttribute("remoteUser")
    public Object remoteUser(final HttpServletRequest request) {
        return request.getRemoteUser();
    }

}
