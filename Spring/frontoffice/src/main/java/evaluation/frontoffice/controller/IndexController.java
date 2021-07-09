package evaluation.frontoffice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import evaluation.frontoffice.service.UserService;

@Controller
public class IndexController {

    @Autowired
    UserService service;
    
    @RequestMapping("/")
    public String index(Model modele,HttpSession session){
        return "redirect:/login";
    }

}
