/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import com.ecosmart.entities.Annonce;
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
import com.ecosmart.services.AnnonceServices;
import com.ecosmart.util.SessionUser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AnnonceAddFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private GeocodingService geocodingService;
    protected DirectionsPane directionsPane;

    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();

    GoogleMapView mapView = new GoogleMapView();
    private String newPhoto;
    @FXML
    private JFXTextField nonevent;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private TextArea description;
    @FXML
    private JFXDatePicker datefin;
    private JFXButton photo;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> typelist = FXCollections.observableArrayList("Pollution", "Probleme municipalite", "autre", "vide");
    @FXML
    private JFXButton add;
    @FXML
    private TextField lon;
    @FXML
    private TextField lat;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane anch;
     int width = 420, height = 524;
    int ulx = 100, uly = 100, w = 80, h = 80;
    int offset;
    Rectangle rectInside;
    PixelWriter pixelWriter;
    byte[] buffer;
     private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ImageView img;

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

        AnnonceServices es = new AnnonceServices();

        type.setValue("Pollution");
     
        type.setItems(typelist);
    

    }

    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
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
                        lon.setText(result.getGeometry().getLocation().getLatitude() + "");
                        lat.setText(result.getGeometry().getLocation().getLongitude() + "");

                    }

                    TextFields.bindAutoCompletion(adresse, t -> {

                        return l;

                    });

                });
    }
    @FXML
    private void ta(MouseEvent event) {

        if (!nonevent.getText().matches("[a-z A-Z]+")) {
          
            nonevent.setText("Un nom ne doit contenir que des lettres ");

        } else {
           
            nonevent.setText("Nom valide");
        }
    }

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

    @FXML
    public void EventAdd(ActionEvent e) {
        AnnonceServices es = new AnnonceServices();
        if (nonevent.getText().isEmpty()
                || adresse.getText().isEmpty()
                || description.getText().isEmpty()
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        if (new Timestamp(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth(), 0, 0, 0, 0)
                .after(new Timestamp(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth(), 0, 0, 0, 0))) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Alerte Date");
            alert.setContentText("Veuillez verifier les dates");
            alert.showAndWait();
        } else {

            Annonce ev = new Annonce(Integer.SIZE, nonevent.getText(),
                    new Timestamp(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    new Timestamp(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    adresse.getText(),
                   description.getText(),
                    type.getValue(),
                    new Timestamp(0, 0, 0, heure.getValue().getHour(), heure.getValue().getMinute(), 0, 0),
                    this.newPhoto);
            ev.setPhoto(selectedFile.getName());
            ev.setIdUser(SessionUser.getUser().getId());
            System.out.println(SessionUser.getUser().getId());
            es.create(ev);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Annonce Added");
            alert.setContentText("Annonce successfully added");
            alert.showAndWait();

        Notifications notificationBuilder = Notifications.create().title("Annonce ajouté avec succéss ☺ ").hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showWarning();
        notificationBuilder.show();
           System.out.println("l'annonce " + ev.getNomAnnonce() + " a été ajouté avec succés");

            Parent homePage;
            try {
                AnchorPane pp = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
                this.anch.getChildren().setAll(pp);

            } catch (IOException ex) {
                Logger.getLogger(AnnonceAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
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
    private void goToAffichage(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane pp = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
        this.anch.getChildren().setAll(pp);

    }
 
    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
