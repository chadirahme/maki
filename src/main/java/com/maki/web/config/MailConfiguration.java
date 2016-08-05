package com.maki.web.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

//http://blog.codeleak.pl/2014/09/using-configurationproperties-in-spring.html
//@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")

@Configuration
//@PropertySource(value = "mail.properties",ignoreResourceNotFound = true)
//@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")
public class MailConfiguration
{
   // @Value("${mail.protocol}")
    private String protocol;
   // @Value("${mail.host}")
    private String host;
    //@Value("${mail.port}")
    private int port;
    //@Value("${mail.smtp.auth}")
    private boolean auth;
   // @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
  // @Value("${mail.from}")
    private String from;
  //  @Value("${mail.username}")
    private String username;
   // @Value("${mail.password}")
    private String password;

   /* @Bean
    public JavaMailSender javaMailSenderLocal() {
        System.out.println("host>>" +host);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }*/

    @Bean
    public JavaMailSender javaMailSender() {

        host="smtp.gmail.com";
        protocol="smtp";
        auth=false;
        starttls=true;
        port=587;
        from="";
        username="";
        password="";


        host="localhost"; 
        protocol="smtp";
        auth=false;
        starttls=true;
        port=25;
        from="";
        username="";
        password="";

        System.out.println("host>>" +host);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
      //  mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);


        return mailSender;
    }


    @Bean
    public JavaMailSender javaGMailSender() {

        host="smtp.gmail.com";
        protocol="smtp";
        auth=false;
        starttls=true;
        port=587;
        from="";
        username="";
        password="";

        /* commit */
        String x="test";

        System.out.println("javaGMailSender host>>" +host);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        return mailSender;
    }
}
