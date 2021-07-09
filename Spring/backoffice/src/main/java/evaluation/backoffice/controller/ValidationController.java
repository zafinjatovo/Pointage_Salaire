package evaluation.backoffice.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.service.UserService;

@Controller
public class ValidationController {
    
    @Autowired
    UserService userService;

    @RequestMapping(path = "/valid",method = RequestMethod.POST)
    public String validUser(@RequestParam(name = "id") int id)throws Exception{
        Connection connection=DatabaseAcess.getConnexion();
        userService.valid(connection,id);
        return "redirect:/users";
    }
}
