package evaluation.frontoffice.helper;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender {
    String sender;
    String password;
    String receiver;
    String objet;
    String message;

    public EmailSender(String sender, String receiver, String objet, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.objet = objet;
        this.message = message;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public EmailSender() {
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getObjet() {
        return objet;
    }
    public void setObjet(String objet) {
        this.objet = objet;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void send(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        
        mailSender.setUsername(this.sender);
        mailSender.setPassword(this.password);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(this.sender);
        message.setTo(this.receiver);
        message.setSubject(this.objet);
        message.setText(this.message);
        JavaMailSender sender=mailSender;
        sender.send(message);
    }

    public void sendWithAttachement(String path) throws Exception{
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        
        mailSender.setUsername(this.sender);
        mailSender.setPassword(this.password);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        JavaMailSender emailSender=mailSender;

        MimeMessage message = emailSender.createMimeMessage();
     
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom(this.sender);
        helper.setTo(this.receiver);
        helper.setSubject(this.objet);
        helper.setText(this.message);
            
        FileSystemResource file  = new FileSystemResource(new File(path));
        helper.addAttachment("teste.pdf", file);
    
        emailSender.send(message);
    }
    
}
