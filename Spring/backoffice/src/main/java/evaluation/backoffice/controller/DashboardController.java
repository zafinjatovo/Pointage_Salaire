package evaluation.backoffice.controller;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.service.AdminService;

@Controller
public class DashboardController {
    @Autowired
    AdminService adminService;
    
    @RequestMapping("/dashboard")
    public String viewDashboard(HttpSession session) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        boolean check=adminService.checkToken(connection,(String) session.getAttribute("SESSION_TOKEN"));
        if(!check){
            return "redirect:/login?expired";
        }
        return "dashboard";
    }
}
