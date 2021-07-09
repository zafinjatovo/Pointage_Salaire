package evaluation.backoffice.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import evaluation.backoffice.dto.InfoPageDto;
import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.helper.Util;
import evaluation.backoffice.modele.User;
import evaluation.backoffice.service.AdminService;
import evaluation.backoffice.service.GeneralService;
import evaluation.backoffice.service.UserService;

@Controller
public class UserController {

    @Autowired
    AdminService adminService;

    @Autowired
    GeneralService service;

    @Autowired
    UserService userService;
    
    static final int D_PAGE=1;
    static final int D_SIZE=10;
    static final String D_COLONNE="dateinscription";
    static final String D_ORDRE="ASC"; /// ASC 

    @RequestMapping("/users")
    public String users(HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        return "redirect:/users/listes" + "/" + D_PAGE + "/" + D_SIZE + "/" + D_COLONNE + "/" + D_ORDRE;
    }

    @RequestMapping("/users/listes/{page}/{page-size}/{colonne}/{ordre}")
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
        int nombreItem=service.count(connection,"V_USER_NOVALID");
        int nombrePage=Util.nombrePage(nombreItem,size);
        System.out.println(nombrePage);
        InfoPageDto info=new InfoPageDto(nombrePage,page,size,colonne,ordre);
        int offset=(page*size)-size;
        List<User> listeUsers=userService.findAll(connection, colonne, ordre, offset, size);
        model.addAttribute("users", listeUsers);
        model.addAttribute("info", info);
        return "users";
    }
}
