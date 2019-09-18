/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.util;

/**
 *
 * @author firas
 */
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailAttachmentSender {


   
 
 
    public static void sendEmailWithAttachments(String toAddress,String subject, String message)
            throws AddressException, MessagingException {
        
  String host="smtp.gmail.com" ;
  String port="587" ;
  final String userName="firas.jerbi@esprit.tn" ;
  final String password="zamasu123";
  //matansewch bech taamlou enable less secure apps fil email mteekom , caffeine member
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html ");
        
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
       
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
        msg.saveChanges();
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test sending e-mail with attachments
     */
    public static void main(String[] args) {
        // SMTP info
     
 
        // message info
        String mailTo = "firasjerbiv2@gmail.com";
        String subject = "This is an email from Ecosmart's Customer support";
        String message = "<h1> Dear user,</h1></br> <p>We are glad that you have joined our community of awesome people. All you need to do now is to confirm your registration using the code below.</p> </br></br> <h4>Thank you for your trust,</h4> </br> <h3> Your Souk Lemdina Team</h3>";
 
 
        try {
            sendEmailWithAttachments( mailTo,
                subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

  
}
