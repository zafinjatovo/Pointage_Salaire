package evaluation.frontoffice.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.modele.Employe;
import evaluation.frontoffice.modele.Fiche;
import evaluation.frontoffice.modele.FicheModele;
import evaluation.frontoffice.modele.HeureSup;
import evaluation.frontoffice.modele.Pointage;
import evaluation.frontoffice.service.EmployeService;
import evaluation.frontoffice.service.FicheService;
import evaluation.frontoffice.service.GeneralService;
import evaluation.frontoffice.service.HeureService;
import evaluation.frontoffice.service.PointageService;
import evaluation.frontoffice.service.UserService;

@Controller
public class FicheController {

    @Autowired
    UserService userService;

    @Autowired
    FicheService service;
    
    @RequestMapping("/fiche/employe/{matricule}/{pointage}")
    //@ResponseBody
    public String voirFiche(
        @PathVariable(name = "matricule") String matricule,
        @PathVariable(name = "pointage") int idpointage,
        Model modele,
        HttpSession session
    ) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        Fiche fiche=service.generate(connection, matricule, idpointage);
        if(!check){
            return "redirect:/login?expired";
        }
        modele.addAttribute("fiche",fiche);
        return "fiche";
        //return pointage;
        //return fiche;
    }
    
}
