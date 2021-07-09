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

import evaluation.backoffice.helper.ActionResult;
import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.Categorie;
import evaluation.backoffice.service.AdminService;
import evaluation.backoffice.service.CategorieService;
import evaluation.backoffice.service.GeneralService;

@Controller
public class CategorieController {
    @Autowired
    CategorieService categorieService;

    @Autowired
    GeneralService service;

    @Autowired
    AdminService adminService;

    @Autowired
    CategorieService categorieservice;


    @RequestMapping("/ajout/categorie")
    public String viewPageAjout(Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        Categorie categorie=new Categorie();
        ActionResult results=new ActionResult();
        /// categories;
        modele.addAttribute("categorie", categorie);
        modele.addAttribute("results", results);
        return "ajoutcategorie";
    }


    @RequestMapping(path = "/ajout/categorie",method = RequestMethod.POST)
    public String saveEmploye(@ModelAttribute("categorie") Categorie categorie,Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        ActionResult results=new ActionResult();
        categorie.validation(results);
        if(results.hasErrors()){
            modele.addAttribute("categorie", categorie);
            modele.addAttribute("results", results);
            return "ajoutcategorie";
        }
        boolean result=categorieservice.save(connection, categorie);
        if(result){
            results.setError("save","Verification: verifier bien votre donne!");
        }
        if(results.hasErrors()){
            modele.addAttribute("categorie", categorie);
            modele.addAttribute("results", results);
            return "ajoutcategorie";
        }
        return "redirect:/ajout/categorie?success";
    }

    @RequestMapping("/liste/categorie")
    public String listesUsers(
        Model model,
        HttpSession session
    )throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        List<Categorie> categories=categorieService.findAll_2(connection);
        if(!check){
            return "redirect:/login?expired";
        }
        model.addAttribute("categories",categories);
        return "categories";
    }

    @RequestMapping("/update/categorie/{id}")
    public String viewPageUpdate(Model modele,HttpSession session,@PathVariable(name = "id") int id) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        Categorie categorie=categorieService.findById(connection, id);
        ActionResult results=new ActionResult();
        modele.addAttribute("categorie",categorie);
        modele.addAttribute("results", results);
        return "updatecategorie";
    }

    @RequestMapping(path = "/update/categorie",method = RequestMethod.POST)
   // @ResponseBody
    public String updatecategorie(@ModelAttribute("categorie") Categorie categorie,Model modele,HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        ActionResult results=new ActionResult();
        categorie.validation(results);
        if(results.hasErrors()){
            modele.addAttribute("categorie", categorie);
            modele.addAttribute("results", results);
            return "updatecategorie";
        }
        boolean result=categorieService.update(connection,categorie);
        if(result){
            results.setError("save","Verification: verifier bien votre donne (ex:age)!");
        }
        if(results.hasErrors()){
            modele.addAttribute("categorie",categorie);
            modele.addAttribute("results", results);
            return "updatecategorie";
        }
        return "redirect:/liste/categorie?success";
    }

}
