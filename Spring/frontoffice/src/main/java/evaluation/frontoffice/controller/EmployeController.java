package evaluation.frontoffice.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import evaluation.frontoffice.dto.InfoPageDto;
import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.helper.Util;
import evaluation.frontoffice.modele.Employe;
import evaluation.frontoffice.service.EmployeService;
import evaluation.frontoffice.service.GeneralService;
import evaluation.frontoffice.service.UserService;

@Controller
public class EmployeController {

    @Autowired
    EmployeService employeservice;

    @Autowired
    GeneralService service;

    @Autowired
    UserService userService;

    static final int D_PAGE=1;
    static final int D_SIZE=10;
    static final String D_COLONNE="matricule";
    static final String D_ORDRE="ASC"; /// ASC 

    @RequestMapping("/liste/employe")
    public String viewPageListe(Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        return "redirect:/liste/employe" + "/" + D_PAGE + "/" + D_SIZE + "/" + D_COLONNE + "/" + D_ORDRE;
    }

    @RequestMapping("/liste/employe/{page}/{page-size}/{colonne}/{ordre}")
    public String listesUsers(
        Model model,
        @PathVariable(name = "page") int page,
        @PathVariable(name = "page-size") int size,
        @PathVariable(name = "colonne") String colonne,
        @PathVariable(name = "ordre") String ordre,
        HttpSession session
    )throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=userService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        int nombreItem=service.count(connection,"EMPLOYEE_D");
        int nombrePage=Util.nombrePage(nombreItem,size);
        System.out.println(nombrePage);
        InfoPageDto info=new InfoPageDto(nombrePage,page,size,colonne,ordre);
        int offset=(page*size)-size;
        List<Employe> listeEmployes=employeservice.findAll(connection, colonne, ordre, offset, size);
        model.addAttribute("employes", listeEmployes);
        model.addAttribute("info", info);
        return "employes";
    }
    

}
