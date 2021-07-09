package evaluation.frontoffice.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evaluation.frontoffice.dto.PointageDto;
import evaluation.frontoffice.helper.ActionResult;
import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.modele.Employe;
import evaluation.frontoffice.modele.Pointage;
import evaluation.frontoffice.modele.PointageDetails;
import evaluation.frontoffice.service.EmployeService;
import evaluation.frontoffice.service.PointageService;
import evaluation.frontoffice.service.UserService;

@Controller
public class PointageController {
    @Autowired
    UserService userService;

    @Autowired
    EmployeService employeService;

    @Autowired
    PointageService pointageService;
    

    @RequestMapping("/pointage/employe/{matricule}")
   // @ResponseBody
    public String viewPointage(HttpSession session,Model modele,@PathVariable(name = "matricule") String matricule)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        Employe employe=employeService.findByMatricule(connection, matricule);
        List<PointageDetails> details=new ArrayList<>();
        details.add(new PointageDetails(0,0,0,1,0,0));
        details.add(new PointageDetails(0,0,0,2,0,0));
        details.add(new PointageDetails(0,0,0,3,0,0));
        details.add(new PointageDetails(0,0,0,4,0,0));
        details.add(new PointageDetails(0,0,0,5,0,0));
        details.add(new PointageDetails(0,0,0,6,0,0));
        details.add(new PointageDetails(0,0,0,7,0,0));
        PointageDto pointages=new PointageDto(new Pointage(0,employe.getMatricule(),""),details);
        ActionResult result=new ActionResult();
        modele.addAttribute("employe",employe);
        modele.addAttribute("pointages", pointages);
        modele.addAttribute("results", result);
        return "pointage";
    }

    @RequestMapping(path = "/pointage/employe",method = RequestMethod.POST)
    public String listeferier(HttpSession session,Model modele,@ModelAttribute("pointages") PointageDto pointageDto) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        Pointage pointage=pointageDto.getPointage();
        List<PointageDetails> details=pointageDto.getDetails();
        ActionResult result=new ActionResult();
        try {
            pointageService.pointage(connection, pointage, details);
        } catch (Exception e) {
            result.setError("save","Value:verifier bien votre donne (ex:positif)!");
        }
        if(result.hasErrors()){
            Employe employe=employeService.findByMatricule(connection,pointage.getMatricule());
            modele.addAttribute("employe",employe);
            modele.addAttribute("pointages", pointageDto);
            modele.addAttribute("results", result);
            return "pointage";
        }
       return "redirect:/heure/employe/" + pointage.getMatricule();
    }
}
