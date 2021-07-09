package evaluation.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evaluation.backoffice.helper.EmailSender;

@Controller
public class SendEmailController {

    /// send mail info sender , receiver , objet , message
    
    @RequestMapping("/sendMail")
    @ResponseBody
    public String sendMail() throws Exception{
        String path="C:/Users/P12B-Tovo-93/Downloads/Documents/Hibernate Pagination _ Baeldung.pdf";
        EmailSender emailSender=new EmailSender("admin@tovogmail.com","tovo@tovogmail.com","Teste send Mail","Salut");
        emailSender.setPassword("01011101");
        emailSender.sendWithAttachement(path);
        return "succes";
    }

}
