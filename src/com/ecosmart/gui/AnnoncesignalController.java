/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;



import com.ecosmart.entities.Annonce;
import com.ecosmart.services.AnnonceServices;
import com.ecosmart.util.DataSource;
import com.ecosmart.util.EmailAttachmentSender;

import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AnnoncesignalController implements Initializable {

    AnnonceServices ws = new AnnonceServices();
  Annonce an = new Annonce();
    @FXML
    private ProgressBar progress1;
    @FXML
    private ProgressBar progress2;
    @FXML
    private Label tot1;
    @FXML
    private Label tot2;
    @FXML
    private Label pourcentage;
    @FXML
    private Label pour1;
    @FXML
    private Button btn;
    @FXML
    private Pane pp;
    @FXML
    private Label pr1;
    @FXML
    private Label pr2;
    @FXML
    private AnchorPane anxc;
 final String username = "firas.jerbi@esprit.tn";
        final String password = "zamasu123";
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

  
  
        loadchart2();
         loadchart3();
        if(ws.nbAnnonce()>5){
            
         Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
 try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("firas.jerbi@esprit.tn"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("firas.jerbi@esprit.tn")
            );
            message.setSubject("A propos des Annonces Signalés");
            message.setText("Le nombre de signal de la dateest plus que 5, veuillez consulter la page de "
                    + "statistique,"
                    + "\n\n Merci");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }else if(ws.nbAnnonce()<2){
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
 try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("firas.jerbi@esprit.tn"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("firas.jerbi@esprit.tn")
            );
            message.setSubject("A propos des Annonces Signalés");
            message.setText("Le nombre d'annonce de la date sont moins que 5"
                    + "\n\n Merci");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    }

 

  

    private void loadchart2() {

        Prog1();

    }


 private void loadchart3() {

        Prog2();

    }
  public void Prog2() {
        double x = ws.nbType2();
        double m = ws.nbAnnoncev2();
        tot2.setText("Nombre Total : " + ((Double) m).toString());
        double prc1 = (x / m) * 100;
        float k = (float) Math.round(prc1 * 100) / 100;
        pour1.setText((String.valueOf(k)) + "% Probleme Municipalité");
        //progress2.setProgress(k / 100);
        float j = k / 100;
        progress2.setProgress(j);
        pr2.setText("% Signal au total");
        System.out.println("Hello world prog2");

    }
   

    public void Prog1() {

        double x = ws.nbType1();
        double m = ws.nbAnnonce();
        tot1.setText("Nombre Total : " + ((Double) m).toString());
        double prc = (x / m) * 100;
        float k = (float) Math.round(prc * 100) / 100;
        pourcentage.setText((String.valueOf(k)) + "% Pollution");
        float j = k / 100;
        progress1.setProgress(j);
        pr1.setText("% au total");
        System.out.println("pourcentage ====>" + j);

    }

    @FXML
    private void onload(ActionEvent event) {
    }


 
   
}
