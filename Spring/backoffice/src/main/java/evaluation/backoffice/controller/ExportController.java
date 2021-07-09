package evaluation.backoffice.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.helper.ExcelExporter;
import evaluation.backoffice.modele.User;
import evaluation.backoffice.service.UserService;

@Controller
public class ExportController {
    
    @Autowired
    UserService service;

    @RequestMapping("/teste")
    @ResponseBody
    public String exrportExcel(HttpServletResponse response) throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        List<User> listesUses=service.findAll(connection, "username","ASC",0,10);
        List<Object> listes=User.toListObj(listesUses);
        ExcelExporter exporter=new ExcelExporter(new User(),listes);
        exporter.export(response);
        return "Succes";
    }
}
