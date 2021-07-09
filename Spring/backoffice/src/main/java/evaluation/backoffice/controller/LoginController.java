package evaluation.backoffice.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evaluation.backoffice.dto.AdminDto;
import evaluation.backoffice.helper.ActionResult;
import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.service.AdminService;

@Controller
public class LoginController {

    @Autowired
    AdminService service;
    
    @RequestMapping("/login")
    public String viewLoginPage(Model modele){
        AdminDto admin=new AdminDto("admin","admin");
        ActionResult results=new ActionResult();
        modele.addAttribute("admin", admin);
        modele.addAttribute("results",results);
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute("admin") AdminDto admin,HttpServletRequest request,Model modele)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        ActionResult results=new ActionResult();
        admin.validation(results);
        String token=service.connect(connection, admin);
        if(token.equals("")){
            results.setError("connexion","Verifier : adresse email / mots de passe !");
        }
      
        if(results.hasErrors()){
            modele.addAttribute("admin", admin);
            modele.addAttribute("results",results);
            return "login";
        }
        request.getSession().setAttribute("SESSION_TOKEN",token);
        return "redirect:/dashboard";
    }
}
