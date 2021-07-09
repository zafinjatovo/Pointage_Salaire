package evaluation.frontoffice.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evaluation.frontoffice.dto.UserSaveDto;
import evaluation.frontoffice.dto.ValidUserDto;
import evaluation.frontoffice.helper.ActionResult;
import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.service.UserService;

@Controller
public class SignupController {
    @Autowired
    UserService service;
    
    @RequestMapping("/signup")
    public String viewLoginPage(Model modele){
        UserSaveDto user=new UserSaveDto();
        ActionResult results=new ActionResult();
        modele.addAttribute("user", user);
        modele.addAttribute("results",results);
        return "signup";
    }

    @RequestMapping(path = "/signup",method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") UserSaveDto user,Model modele)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        ActionResult results=new ActionResult();
        user.validation(results);
        if(results.hasErrors()){
            modele.addAttribute("user", user);
            modele.addAttribute("results",results);
            return "signup";
        }
        ValidUserDto valid=service.save(connection, user);
        if(valid.getValidEmail().equals("not valid")){
            results.setError("validationemail","déjà pris");
        }
        if(valid.getValidUsername().equals("not valid")){
            results.setError("validationusername","déjà pris");
        }
        if(results.hasErrors()){
            modele.addAttribute("user", user);
            modele.addAttribute("results",results);
            return "signup";
        }
        return "redirect:/signup?success";
    }
}
