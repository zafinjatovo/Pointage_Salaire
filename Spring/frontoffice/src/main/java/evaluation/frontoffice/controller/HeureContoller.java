package evaluation.frontoffice.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import evaluation.frontoffice.helper.DatabaseAcess;

import evaluation.frontoffice.modele.HeureSup;

import evaluation.frontoffice.modele.PointageEmploye;
import evaluation.frontoffice.service.EmployeService;
import evaluation.frontoffice.service.HeureService;
import evaluation.frontoffice.service.PointageService;
import evaluation.frontoffice.service.UserService;

@Controller
public class HeureContoller {

    @Autowired
    HeureService heureservice;

    @Autowired
    UserService userService;

    @Autowired
    EmployeService employeService;

    @Autowired
    PointageService pointageservice;
    
    @RequestMapping("/heure/employe/{matricule}")
    public String heures(HttpSession session,Model modele,@PathVariable(name = "matricule") String matricule)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
            //return null;
        }
        List<HeureSup> heuesups=heureservice.findHeureSup(connection);
        PointageEmploye pointage=pointageservice.get(connection, matricule,heuesups);
        modele.addAttribute("pointage", pointage);
        //return pointage;
        return "heure";
    }
}
