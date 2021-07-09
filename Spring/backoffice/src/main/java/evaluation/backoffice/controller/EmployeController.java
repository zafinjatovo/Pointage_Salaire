package evaluation.backoffice.controller;

import java.sql.Connection;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evaluation.backoffice.helper.Util;
import evaluation.backoffice.dto.EmployeDto;
import evaluation.backoffice.dto.InfoPageDto;
import evaluation.backoffice.helper.ActionResult;
import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.Categorie;
import evaluation.backoffice.modele.Employe;
import evaluation.backoffice.service.AdminService;
import evaluation.backoffice.service.CategorieService;
import evaluation.backoffice.service.EmployeService;
import evaluation.backoffice.service.GeneralService;

@Controller
public class EmployeController {

    @Autowired
    GeneralService service;

    @Autowired
    AdminService adminService;

    @Autowired
    EmployeService employeservice;

    @Autowired
    CategorieService categorieService;

    static final int D_PAGE=1;
    static final int D_SIZE=10;
    static final String D_COLONNE="matricule";
    static final String D_ORDRE="ASC"; /// ASC 

    @RequestMapping("/ajout/employe")
    public String viewPageAjout(Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        EmployeDto employeDto=new EmployeDto();
        ActionResult results=new ActionResult();
        /// categories;
        List<Categorie> catetegories=categorieService.findAll(connection);
        modele.addAttribute("categories",catetegories);
        modele.addAttribute("employe", employeDto);
        modele.addAttribute("results", results);
        return "ajoutemploye";
    }

    @RequestMapping(path = "/ajout/employe",method = RequestMethod.POST)
    public String saveEmploye(@ModelAttribute("employe") EmployeDto employeDto,Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        ActionResult results=new ActionResult();
        employeDto.validation(results);
        if(results.hasErrors()){
            List<Categorie> catetegories=categorieService.findAll(connection);
            modele.addAttribute("categories",catetegories);
            modele.addAttribute("employe", employeDto);
            modele.addAttribute("results", results);
            return "ajoutemploye";
        }
        String matricule=employeservice.save(connection, employeDto);
        if(matricule.equals("")){
            results.setError("save","Verification: verifier bien votre donne (ex:age)!");
        }
        if(results.hasErrors()){
            List<Categorie> catetegories=categorieService.findAll(connection);
            modele.addAttribute("categories",catetegories);
            modele.addAttribute("employe", employeDto);
            modele.addAttribute("results", results);
            return "ajoutemploye";
        }
        return "redirect:/ajout/employe?success";
    }


    @RequestMapping("/liste/employe")
    public String viewPageListe(Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
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
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
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
        return "employe";
    }



    //// update 
    @RequestMapping("/update/employe/{matricule}")
    public String viewPageUpdate(Model modele,HttpSession session,@PathVariable(name = "matricule") String matricule) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        EmployeDto employeDto=employeservice.findByMatricule(connection, matricule);
        System.out.println(employeDto.getMatricule());
        ActionResult results=new ActionResult();
        /// categories;
        List<Categorie> catetegories=categorieService.findAll(connection);
        modele.addAttribute("categories",catetegories);
        modele.addAttribute("employe", employeDto);
        modele.addAttribute("results", results);
        return "updateEmploye";
    }

    @RequestMapping(path = "/update/employe",method = RequestMethod.POST)
   // @ResponseBody
    public String updateEmploye(@ModelAttribute("employe") EmployeDto employeDto,Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        ActionResult results=new ActionResult();
        employeDto.validation(results);
        if(results.hasErrors()){
            List<Categorie> catetegories=categorieService.findAll(connection);
            modele.addAttribute("categories",catetegories);
            modele.addAttribute("employe", employeDto);
            modele.addAttribute("results", results);
            return "updateEmploye";
        }
        boolean result=employeservice.update(connection, employeDto);
        if(!result){
            results.setError("save","Verification: verifier bien votre donne (ex:age)!");
        }
        if(results.hasErrors()){
            List<Categorie> catetegories=categorieService.findAll(connection);
            modele.addAttribute("categories",catetegories);
            modele.addAttribute("employe", employeDto);
            modele.addAttribute("results", results);
            return "updateEmploye";
        }
        return "redirect:/liste/employe?success";
       // return employeDto;
    }

}
