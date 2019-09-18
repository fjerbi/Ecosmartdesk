/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;
import com.ecosmart.util.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.ecosmart.entities.Annonce;
import com.ecosmart.entities.FosUser;
import com.ecosmart.services.AnnonceServices;

import com.ecosmart.util.SessionUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AnnonceAffFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane s1;
    private Rating rat;
    private GoogleMapView mapView;
    @FXML
    private MenuButton choice;
    @FXML
    private MenuItem aff1;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> typel = FXCollections.observableArrayList("Pollution", "Probleme municipalite", "autres", "vide");

    List<String> li = new ArrayList<>();
    AnnonceServices es = new AnnonceServices();
   
    FosUser f = SessionUser.getUser();
    FosUser f1 = new FosUser();
    Annonce evv = new Annonce();
    @FXML
    private AnchorPane a;
    @FXML
    private JFXButton Aj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        List<Annonce> le = new ArrayList<>();
        le = es.afficherev();

        for (Annonce evv : le) {
            String sd = evv.getDateDebut().toString().substring(0, 10);
            String sf = evv.getDateFin().toString().substring(0, 10);

            

            Rating rat = new Rating();
            Rating rat2 = new Rating();
            Label nom = new Label();
            Label address = new Label();
            Label dated = new Label();
            Label datef = new Label();
            Label type = new Label();
            Label heure = new Label();
            ImageView photo = new ImageView(new Image("http://localhost/Ecosmartweb/web/uploads/annonce/" + evv.getPhoto()));
            photo.setFitHeight(200);
            photo.setFitWidth(200);
            Text add = new Text("Adresse: ");
            Text ty = new Text("Type: ");
            Text dd = new Text("Début: ");
            Text df = new Text("Fin: ");
            Text h = new Text("Heure: ");
            add.setFill(Color.DARKORANGE);
            ty.setFill(Color.DARKORANGE);
            dd.setFill(Color.DARKORANGE);
            df.setFill(Color.DARKORANGE);
            h.setFill(Color.DARKORANGE);
            nom.setText(evv.getNomAnnonce());
            address.setText(evv.getAdresse());
            System.out.println(sd);
            dated.setText(sd);
            System.out.println(sf);
            datef.setText(sf);
            type.setText(evv.getType());
            heure.setText(evv.getHeure().toString().substring(11, 16));
           
            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Afficher Détails");
            Button bt2 = new Button("modifier"); 
            Button bt3 = new Button("Supprimer");
            Button sig = new Button("Signaler");
               Button btfb = new Button("Share on Facebook");
            final Separator sep = new Separator();
            vbox1.setSpacing(20);
            vbox1.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
            h1.setSpacing(10);
rat2.ratingProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                     rat.setRating(evv.getRating());
                    evv.setRating((int) rat2.getRating());

                    System.out.println("Clicked" + rat2.getRating());
                    System.out.println("    After Clicked" + (evv.getRating() + (int) rat2.getRating()));
                    evv.setRating((int) rat2.getRating());
                    
                    es.addRarting(evv);
                    rat.setRating(es.getRateAnnonces(evv));
                }

            });
            hv1.getChildren().add(nom);
            v1.getChildren().add(rat2);
            v2.getChildren().add(add);
            v2.getChildren().add(address);
            h1.getChildren().add(ty);
            h1.getChildren().add(type);
            h1.getChildren().add(dd);
            h1.getChildren().add(dated);
            h1.getChildren().add(df);
            h1.getChildren().add(datef);
            h1.getChildren().add(h);
            h1.getChildren().add(heure);
            h1.getChildren().add(photo);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt2);
            btn.getChildren().add(bt3);
            btn.getChildren().add(sig);
             btn.getChildren().add(btfb); 
            vbox1.getChildren().add(hv1);
            vbox1.getChildren().add(v1);
            vbox1.getChildren().add(v2);
            vbox1.getChildren().add(h1);
            vbox1.getChildren().add(btn);
            vbox1.getChildren().add(sep);
            btn.setAlignment(Pos.CENTER);
            nom.setFont(javafx.scene.text.Font.font("Courier", 20));
            nom.setStyle("-fx-text-fill: #20b2aa");
            address.setFont(javafx.scene.text.Font.font("Courier", 15));
            
            sig.setStyle("-fx-background-color: green; -fx-text-fill: black;");
btfb.setStyle("-fx-background-color: #4267B2; -fx-text-fill: black;");
            bt3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
            sig.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });

   sig.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (sig.getText().equals("Signaler")) {
                        es.signaler(evv, f);
                        sig.setText("Ne pas signaler");
                    } else {
                        es.passignaler(evv, f);
                        sig.setText("Signaler");
                    }
                }
            });
  btfb.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Parent homePage;
                     Annonce annonce = new Annonce();
             String accessToken = "EAAdGfq8mIJsBAHXubUoPovefXggDw35MsanyGwIUhRF7hYS6WBZBI2unjNv7YKp9RaQYAsbMb05lYEMoX9Ga9AWui8NX0hzganGLJleRBEB4ggAKzg44j6iHVG8CsHT7dljYIPlvKJ9dTZBdhyYeOZABsTSAkmmmfiaYsFkT35vgzTPiv0byg6F0YHIrTmBS7vjOAVCOAZDZD";
            AnnonceServices ls = new AnnonceServices();
          //  Annonce loc = ls.afficherLocalUn(Annonce.getId());
            Scanner s = new Scanner(System.in);
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);
            FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                    Parameter.with("message", "Annonce " + annonce.getNomAnnonce()+ "Description " + annonce.getDescription() + " à " + annonce.getNomAnnonce()),
                    Parameter.with("link", "https://www.google.com/"));
            System.out.println("fb.com/" + response.getId());

            LocalDateTime now = LocalDateTime.now();
            LocalTime lt = now.toLocalTime().plusMinutes(1);
            LocalTime nowT;
            Boolean t = false;
            Notifications notif = Notifications.create()
                    .darkStyle().title("Succés")
                    .text("Partage effectué avec succés")
                    .position(Pos.TOP_RIGHT)
                    .hideAfter(Duration.seconds(30))
                    //.graphic(new ImageView(img))
                    .onAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("It worked");
                        }
                    });

            do {
                now = LocalDateTime.now();
                nowT = now.toLocalTime();
                if (nowT.isAfter(lt)) {
                    t = true;
                }

            } while (t == false);
            if (t == true) {
                notif.showInformation();
            }
            System.out.println(accessToken);

                    }
                
            });
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Parent homePage;
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AnnonceShowFXML.fxml"));
                        AnchorPane pp = loader.load();
                        AnnonceShowFXMLController shev = loader.getController();
                        a.getChildren().setAll(pp);
                        shev.setEvenement(evv);
                        shev.showEv();

                    } catch (IOException ex) {
                        Logger.getLogger(AnnonceAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            Calendar cald = Calendar.getInstance();
            cald.setTimeInMillis(evv.getDateDebut().getTime());

            cald.add(Calendar.DAY_OF_YEAR, -1);

            bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateAnnonceFXML.fxml"));
                        AnchorPane AN = root.load();
                        UpdateAnnonceFXMLController Even = root.getController();
                        vbox1.getChildren().setAll(AN);
                        Even.setEvenement(evv);
                        Even.show();

                    } catch (IOException ex) {
                        Logger.getLogger(UpdateAnnonceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
           
       if (new Timestamp(new Date().getTime()).after(new Timestamp(cald.getTime().getTime()))) {
             
                bt2.setStyle("-fx-background-color: #4267B2; -fx-text-fill: black;");
              bt2.setText("worked");
                    bt2.setDisable(false);

            }else{
                 System.out.println(evv.getIdUser());
               bt2.setDisable(false);  
            }
            Calendar cal = Calendar.getInstance();

            cal.setTimeInMillis(evv.getDateDebut().getTime());
            cal.add(Calendar.HOUR, -72);

        
                bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Suppression d'une Annonce");
                        alert.setHeaderText("etes vous sure de  vouloir supprimer cette Annonce ☺ ");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            es.delete(evv.getId());
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("Annonce suprrimée avec succées");
                            alertSec.showAndWait();

                        }

                    }

                });
       

            li.add(evv.getNomAnnonce());
            TextFields.bindAutoCompletion(recherche, li);
            recherche.validate();

        }

    }

    private void choix(MouseEvent event) {
        type.setVisible(true);

    }

    ///////////////////////////////////////////////FILTERRRRRRRRRRRRRR/////////////////////////////////////////////////////////// 
    @FXML
    private void aff1(ActionEvent event) {

        // Annonce ee = new Annonce();
        type.setPromptText("Choisir un type");
        type.getItems().clear();

        type.setItems(typel);
        type.setOnAction((ActionEvent e) -> {

            vbox1.getChildren().clear();

            List<Annonce> ls = new ArrayList<>();
            ls = es.getAnnonceBytype(this.type.getValue());
            for (Annonce evv : ls) {

                System.out.println("   aaaaaaaaaaaaaff");
                String sd = evv.getDateDebut().toString().substring(0, 10);
                String sf = evv.getDateFin().toString().substring(0, 10);

//                es.afficherev();
               GoogleMapView mapView = new GoogleMapView();
                Label nom = new Label();
                Label address = new Label();
                Label dated = new Label();
                Label datef = new Label();
                Label type = new Label();
                Text h = new Text("Heure: ");
                Label heure = new Label(evv.getHeure().toString().substring(11, 16));
                ImageView photo = new ImageView(new Image("http://localhost/Ecosmartweb/web/uploads/annonce/" + evv.getPhoto()));
                System.out.println("http://localhost/Ecosmartweb/web/uploads/annonce/" + evv.getPhoto());
                photo.setFitHeight(200);
                photo.setFitWidth(200);

                Text Des = new Text("Description: ");
                Text add = new Text("Adresse: ");
                Text ty = new Text("type: ");
                Text dd = new Text("Début: ");
                Text df = new Text("Fin: ");
                add.setFill(Color.DARKORANGE);
                h.setFill(Color.DARKORANGE);
                ty.setFill(Color.DARKORANGE);
                dd.setFill(Color.DARKORANGE);
                df.setFill(Color.DARKORANGE);
                nom.setText(evv.getNomAnnonce());
                System.out.println(evv.getNomAnnonce());
                address.setText(evv.getAdresse());
                dated.setText(sd);
                datef.setText(sf);
                type.setText(evv.getType());
                HBox h1 = new HBox();
                HBox btn = new HBox();
                VBox v1 = new VBox();
                VBox v2 = new VBox();
                HBox hv1 = new HBox();
                Button bt1 = new Button("participer");
                if (evv.getIdUser() == f.getId()) {
                    bt1.setVisible(false);
                }
                
                JFXButton bt = new JFXButton("Afficher Détails");
                Button bt2 = new Button("modifier");
                Button bt3 = new Button("Supprimer");
                Button sig = new Button("Signaler");
                TextArea comment = new TextArea("Ecrire ici");
                final Separator sep = new Separator();
                vbox1.setSpacing(25);
                sep.setMaxWidth(Double.MAX_EXPONENT);
                h1.setSpacing(20);
                hv1.getChildren().add(nom);
                v2.getChildren().add(mapView);
                v2.getChildren().add(add);
                v2.getChildren().add(address);
                h1.getChildren().add(ty);
                h1.getChildren().add(type);
                h1.getChildren().add(dd);
                h1.getChildren().add(dated);
                h1.getChildren().add(df);
                h1.getChildren().add(datef);
                h1.getChildren().add(h);
                h1.getChildren().add(heure);
                h1.getChildren().add(photo);
                btn.getChildren().add(bt1);
                btn.getChildren().add(bt);
                btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);
                btn.getChildren().add(sig);
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                btn.setSpacing(10);

                nom.setFont(javafx.scene.text.Font.font("Courier", 20));

                nom.setStyle("-fx-text-fill: #20b2aa");
                address.setFont(javafx.scene.text.Font.font("Courier", 15));

                bt1.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                bt3.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:#ffffff;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70;");

/////////////////////////////BUTTONS///////////////////////////////////////////////////////////////
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AnnonceShowFXML.fxml"));
                            AnchorPane xc = loader.load();
                            AnnonceShowFXMLController shev = loader.getController();

                            shev.setEvenement(evv);
                            shev.showEv();
                            a.getChildren().setAll(xc);

                        } catch (IOException ex) {
                            Logger.getLogger(AnnonceAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(evv.getDateDebut().getTime());

                cal2.add(Calendar.DAY_OF_YEAR, -7);
                System.out.println(new Timestamp(cal2.getTime().getTime()));
 if (evv.getIdUser() == f.getId()) {
                bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateAnnonceFXML.fxml"));
                            AnchorPane x = root.load();
                            UpdateAnnonceFXMLController Evente = root.getController();
                            vbox1.getChildren().setAll(x);
                            Evente.setEvenement(evv);
                            Evente.show();

                        } catch (IOException ex) {
                            Logger.getLogger(UpdateAnnonceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
 }
                if (new Timestamp(new Date().getTime()).after(new Timestamp(cal2.getTime().getTime()))) {
                 

                }

                Calendar cal = Calendar.getInstance();

                cal.setTimeInMillis(evv.getDateDebut().getTime());
                cal.add(Calendar.HOUR, -72);

     
                    bt3.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                            alert.setTitle("Alert!!!!!!!!!!");
                            alert.setHeaderText("etes vous sure de bien vouloir supprimer cette annonce");
                            Optional<ButtonType> res = alert.showAndWait();

                            if (res.get() == ButtonType.OK) {

                                es.delete(evv.getId());
                                vbox1.getChildren().remove(hv1);
                                vbox1.getChildren().remove(v1);
                                vbox1.getChildren().remove(h1);
                                vbox1.getChildren().remove(btn);
                                vbox1.getChildren().remove(v2);
                                vbox1.getChildren().remove(sep);
                                Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                                alertSec.setHeaderText("Annonce suprrimé avec succées");
                                alertSec.showAndWait();

                            }

                        }

                    });
               

            }

        });
    }
    @Override
    public void mapInitialized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    void goToAj(ActionEvent e) throws IOException {

        AnchorPane lp = FXMLLoader.load(getClass().getResource("AnnonceAddFXML.fxml"));
        this.a.getChildren().setAll(lp);

    }

}
