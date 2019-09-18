/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.ecosmart.entities.Annonce;
import com.ecosmart.services.AnnonceServices;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class UpdateAnnonceFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
  private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ImageView img;

    private GeocodingService geocodingService;
    protected DirectionsPane directionsPane;

    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();
    GoogleMapView mapView = new GoogleMapView();

    private String newPhoto = null;

    private File selected_photo = null;
    private String oldPhoto;
    @FXML
    private JFXTextField nonevent;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField nbplace;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private TextArea description;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXComboBox<String> type;

    private Annonce eve;
    @FXML
    private JFXButton backtolist;
    ObservableList<String> typelist = FXCollections.observableArrayList("Pollution", "Probleme municipalite", "autres", "vide");
//    @FXML
//    private AnchorPane aj;

    @FXML
    private TextField lonn;
    @FXML
    private TextField latt;
    @FXML
    private AnchorPane ass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);

        mapView.addMapInializedListener(this);

        from.bindBidirectional(adresse.textProperty());

        adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    adresse.validate();
                }
            }
        });
        type.setItems(typelist);
    }

    @FXML
    private void fromOnKeyTypedEvent2(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        geocodingService.geocode(adresse.getText(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    l.clear();
                    for (int i = 0; i < results.length; i++) {
                        s = new String[results.length];
                        s[i] = results[i].getFormattedAddress();
                        System.out.println(results[i].getJSObject());
                        l.add(results[i].getFormattedAddress());

                    }

                    for (GeocodingResult result : results) {

                        TextFields.bindAutoCompletion(adresse, s);
                        lonn.setText(result.getGeometry().getLocation().getLatitude() + "");
                        latt.setText(result.getGeometry().getLocation().getLongitude() + "");

                    }

                    TextFields.bindAutoCompletion(adresse, t -> {

                        return l;

                    });

                });
    }

    public void setEvenement(Annonce eve) {
        this.eve = eve;
    }

    public void show() {
        nonevent.setText(eve.getNomAnnonce());
//type.setValue("Culturel");
        type.setItems(typelist);
        type.setValue(eve.getType());

//        prix.setText(String.valueOf(eve.getPrix()));
        adresse.setText(eve.getAdresse());
        description.setText(eve.getDescription());
     //   nbplace.setText(String.valueOf(eve.getNbPlace()));

//        
    }

    @FXML
    private void validUpdate(ActionEvent event) throws Exception {
        AnnonceServices es = new AnnonceServices();
        this.eve = es.FindAnnonce(this.eve.getId());
        System.out.println(this.eve.getId());
        //Evenement ev = new Annonce();
        this.eve.setNomAnnonce(nonevent.getText());
        this.eve.setAdresse(adresse.getText());
        this.eve.setType(type.getValue());
        //this.eve.setNbPlace(Integer.parseInt(nbplace.getText()));
       // this.eve.setPrix(Integer.parseInt(prix.getText()));
        this.eve.setDescription(description.getText());
        this.eve.setDateDebut(new Timestamp(this.datedebut.getValue().getYear() - 1900, this.datedebut.getValue().getMonthValue() - 1, this.datedebut.getValue().getDayOfMonth(), 0, 0, 0, 0));
        this.eve.setDateFin(new Timestamp(this.datefin.getValue().getYear() - 1900, this.datefin.getValue().getMonthValue() - 1, this.datefin.getValue().getDayOfMonth(), 0, 0, 0, 0));
        this.eve.setHeure(new Timestamp(0, 0, 0, this.heure.getValue().getHour(), this.heure.getValue().getMinute(), 0, 0));
        if (this.selected_photo != null) {
            this.eve.setPhoto(this.newPhoto);
        }
        System.out.println(this.eve.getPhoto());
        System.out.println(this.eve.getId());
        es.update(this.eve);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Annonce modifié");
        alert.setHeaderText("L'Annonce choisi a été modifié");
        alert.setContentText("Votre modification a été enregistrée");
        alert.showAndWait();
        alert.close();

        AnchorPane pp = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
        this.ass.getChildren().setAll(pp);

    }

@FXML
    void selectPhoto(ActionEvent event) throws Exception {
    
         File dest=new File("C:\\wamp64\\www\\ecosmartweb\\web\\uploads\\annonce");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp64\\www\\Ecosmartweb"));
        selectedFile = Fc.showOpenDialog(null);
           try {
               FileUtils.copyFileToDirectory(selectedFile, dest);
           } catch (IOException ex) {
               Logger.getLogger(AnnonceAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
         
          
           try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AnnonceAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       //     photoName.setText(file.getName());
           
           
    
        
    }


    @FXML
    private void goToAffichage2(ActionEvent e) throws IOException {
        AnchorPane pp = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
        this.ass.getChildren().setAll(pp);

    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();

    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
