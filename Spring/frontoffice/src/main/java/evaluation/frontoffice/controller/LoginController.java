package evaluation.frontoffice.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evaluation.frontoffice.dto.UserDto;
import evaluation.frontoffice.helper.ActionResult;
import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.service.UserService;

@Controller
public class LoginController {

    @Autowired
    UserService service;
    
    @RequestMapping("/login")
    public String viewLoginPage(Model modele){
        UserDto user=new UserDto("toky","toky");
        ActionResult results=new ActionResult();
        modele.addAttribute("user", user);
        modele.addAttribute("results",results);
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute("user") UserDto user,HttpServletRequest request,Model modele)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        ActionResult results=new ActionResult();
        user.validation(results);
        String token=service.connect(connection, user);
        if(token.equals("")){
            results.setError("connexion","Verifier : adresse email / mots de passe !");
        }
        if(token.equals("not valid")){
            results.setError("validation","Compte : non valid!");
        }
        if(results.hasErrors()){
            modele.addAttribute("user", user);
            modele.addAttribute("results",results);
            return "login";
        }
        request.getSession().setAttribute("SESSION_TOKEN",token);
        return "redirect:/liste/employe";
    }
}
