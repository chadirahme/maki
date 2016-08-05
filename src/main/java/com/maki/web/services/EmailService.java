package com.maki.web.services;


import com.maki.web.domains.EmailModel;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.concurrent.atomic.AtomicLong;

@RestController("contact")
public class EmailService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final JavaMailSender javaMailSender;
    private final JavaMailSender javaGMailSender;

    @Autowired
    EmailService(JavaMailSender javaMailSender , JavaMailSender javaGMailSender)
    {
        this.javaMailSender = javaMailSender;
        this.javaGMailSender=javaGMailSender;
    }

    @RequestMapping("/sendmail")
    public ResponseEntity<String> sendContactMail(String name, String email, String phone, String message)//@RequestParam(value="name", defaultValue="World")
    {
        //System.out.print(name + " " + email);
        try
        {
            System.out.println("sending...>>");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
           // helper.setTo("eng.chadi@gmail.com");
            helper.setFrom("info@makiformaterials.com");
            //helper.setTo("eng.chadi@gmail.com");//cr33
            helper.setTo("info@makiformaterials.com");
         //   helper.setBcc("eng.chadi@gmail.com");
            helper.setSubject("Contact Request from " + name);

            String body="Contact Request send from <b>" + name + "</b> Phone : " + phone + " Email :" + email ;
            body+="<br/>";
            body+="<br/>";
            body+=message;

            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);

           // msg.setContent(body,"text/html; charset=utf-8");

            //helper.setText("",body);


            /*SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("eng.chadi@gmail.com");
            //mailMessage.setReplyTo("someone@localhost");
            mailMessage.setFrom("Maki");

            mailMessage.setSubject("Contact Request from " + name);
            String body="Contact Request send from <b>" + name + "</b> Phone : " + phone ;
            body+="/r/n";
            body+=message;
            mailMessage.setText(body);*/

            javaMailSender.send(msg);
           // sendErrorMail(name,email,phone,message);
            sendThankyouMail(name,email);
            System.out.println("Done...>>");

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            sendError("Error at MAKI sendContactMail" , ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        //return "{result : Done}";
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    public String sendThankyouMail(String name,String email)
    {
        try
        {
            System.out.println("sending sendThankyouMail...>>");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
            helper.setFrom("info@makiformaterials.com");
            helper.setTo(email);
            helper.setSubject("Welcome to MAKI");
            String body="Thank you for getting in touch!";
            body+="<br/>";
            body+="<br/>";
            body+="We appreciate you contacting us. We try to respond as soon as possible. ";
            body+="<br/>";
            body+="Have a great day ahead!";
            body+="<br/><br/>";
            body+="MAKI for building Materials and Trading S.A.R.L.";

            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            javaMailSender.send(msg);

            System.out.println("Done sending sendThankyouMail...>>");
        }
        catch (Exception ex)
        {
            return  ex.getMessage();
        }
        return "Done";
    }

    public String sendErrorMail(String name,String email,String phone,String message)//@RequestParam(value="name", defaultValue="World")
    {
        try
        {
            System.out.println("sending Error...>>");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
            helper.setTo("eng.chadi@gmail.com");
            helper.setSubject("Maki Contact Request from " + name);
            String body="Contact Request send from <b>" + name + "</b> Phone : " + phone + " Email :" + email ;
            body+="<br/>";
            body+="<br/>";
            body+=message;

            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            javaMailSender.send(msg);

            System.out.println("Done sending error...>>");
        }
        catch (Exception ex)
        {
            return  ex.getMessage();
        }
        return "Done";
    }

    private void sendError(String mailSubject,String mailBody)
    {
        try
        {
            System.out.println("sending gmail Error...>>");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
            helper.setTo("eng.chadi@gmail.com");
            helper.setSubject(mailSubject);
            String body="Error at MAKI : ";
            body+="<br/>";
            body+="<br/>";
            body+=mailBody;

            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            javaGMailSender.send(msg);

            System.out.println("Done sending error...>>");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    @RequestMapping("/greeting")
    public EmailModel  greeting(@RequestParam(value="name", defaultValue="World") String name)
    {
     //  return new EmailModel(counter.incrementAndGet(), String.format(template, name));
        return new EmailModel();
    }


    @RequestMapping(value="/send", method= RequestMethod.POST)
    public String sendEmail(EmailModel contactEmail)//@RequestParam(value="contactEmail")
    {
        String result="";
        try
        {
/*
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("eng.chadi@gmail.com");
            //mailMessage.setReplyTo("someone@localhost");
            mailMessage.setFrom("chadi@localhost");
            mailMessage.setSubject("Lorem ipsum");
            mailMessage.setText("Lorem ipsum dolor sit amet [...]");
            javaMailSender.send(mailMessage);
            */
            System.out.print(contactEmail.getEmail());

            result="send..";
        }
        catch (Exception ex)
        {
            result=ex.getMessage();
        }
            return result;
    }
}
