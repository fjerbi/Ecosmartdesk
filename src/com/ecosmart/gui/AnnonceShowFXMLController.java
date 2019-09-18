/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;

import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.ecosmart.entities.Annonce;
import com.ecosmart.entities.FosUser;
import com.ecosmart.services.AnnonceServices;

import com.ecosmart.util.SessionUser;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AnnonceShowFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
    
    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    //private Mapbox mapb;
    private GoogleMapView mapView = new GoogleMapView();
   
    FosUser f = SessionUser.getUser();

//    List<String>les=new ArrayList<>();
    @FXML
    private Label nom;
    @FXML
    private HBox sh;
    public static Annonce EventSelected;
    @FXML
    private HBox sh1;
    private Annonce evs;
    @FXML
    private HBox sh2;
    @FXML
    
    private AnchorPane anchp;
        private ImageView imge= new ImageView();
    @FXML
    private ImageView image;
protected GoogleMapView mapComponent;

    protected DirectionsPane directions;

    private Button btnZoomIn;
    private Button btnZoomOut;
    private Label lblZoom;
    private Label lblCenter;
    private Label lblClick;
    private ComboBox<MapTypeIdEnum> mapTypeCombo;
	GoogleMapView sample = new GoogleMapView();
	private MarkerOptions markerOptions2;
	private Marker myMarker2;
	private Button btnHideMarker;
	private Button btnDeleteMarker;
    /**
     * Initializes the controller class.
     */
    public void setEvenement(Annonce evs) {
        this.evs = evs;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        
        
    }
    
    public void showEv() {
        String sd = evs.getDateDebut().toString().substring(0, 10);
        String sf = evs.getDateFin().toString().substring(0, 10);
        AnnonceServices es = new AnnonceServices();
        //affichage image 
        ImageView photo = new ImageView(new Image("http://localhost/Ecosmartweb/web/uploads/annonce/" + evs.getPhoto()));
        photo.setFitHeight(200);
        photo.setFitWidth(454);
        
        nom.setText(evs.getNomAnnonce());
        nom.setAlignment(Pos.CENTER);
        Label dd = new Label();
        Label df = new Label();
        Label ty = new Label();
        Label des = new Label();
        //Label ph = new Label();
        Label ad = new Label();
     //   Label pr = new Label();
        Label pl = new Label();
        Text Des = new Text("Description: ");
        Text add = new Text("Adresse: ");
      //  Text nb = new Text("Places: ");
        GoogleMapView map = new GoogleMapView();
      
            imge = new ImageView();
            imge.setFitHeight(150);
            imge.setFitWidth(150);
        Text typ = new Text("type: ");

        Text dds = new Text("Début: ");
        Text dfs = new Text("Fin: ");
        add.setFill(Color.DARKCYAN.saturate());
//        nb.setFill(Color.AQUAMARINE);
        typ.setFill(Color.AQUAMARINE);
//        p.setFill(Color.AQUAMARINE);
        dds.setFill(Color.AQUAMARINE);
        dfs.setFill(Color.AQUAMARINE);
        Des.setFill(Color.DARKCYAN);
        JFXButton part = new JFXButton("Participer");
        JFXButton list = new JFXButton("Retour à la liste ");
        dd.setText(sd);
        df.setText(sf);
        ty.setText(evs.getType());     
       des.setText(evs.getDescription());
        
        Image im =new Image("http://localhost/Ecosmartweb/web/uploads/annonce/"+evs.getPhoto());
                                imge.setImage(im);
        des.setWrapText(true);
        
        ad.setText(evs.getAdresse());
        //ph.setText(evs.getPhoto());
        
        sh.getChildren().addAll(dds, dd, dfs, df, typ, ty, pl);
        sh.setSpacing(30);
        sh.setAlignment(Pos.CENTER);
        //vbox 
        VBox vb = new VBox();
        VBox vb1 = new VBox();
        Separator s1 = new Separator(Orientation.VERTICAL);
        //Insertion des children dans la fxml
        
        vb1.getChildren().addAll(add, map);
        vb.getChildren().addAll(Des, des, photo);
        
        vb.setSpacing(10);
        // sh1.getChildren().add(ad);
        sh1.getChildren().addAll(vb1, s1, vb);
        sh1.setSpacing(5);

        // sh1.setAlignment(Pos.CENTER);
        sh2.getChildren().addAll(part, list);
        
        sh2.setAlignment(Pos.CENTER);
        
        part.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        list.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        nom.setFont(javafx.scene.text.Font.font("Courier", 20));
        nom.setStyle("-fx-text-fill: #07d7aa ;-fx-font-weight: bold");
        
  

     
        
        
        
        
        list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
                FXMLLoader root = new FXMLLoader(getClass().getResource("AnnonceAffFXML.fxml"));
                try {
                    AnchorPane v = root.load();
                    anchp.getChildren().setAll(v);
                } catch (IOException ex) {
                    
                    Logger.getLogger(AnnonceAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AnnonceAffFXMLController afev = root.getController();
            }
            
        });
    }


    
    @Override
    public void mapInitialized() {
       /*
        Double lon = new Double(0);
        Double lat = new Double(0);
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();
        
        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(10)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
//        MapBox mapb= mapView.createMap(options);
        GoogleMap map = mapView.createMap(options);
        geocodingService.geocode(evs.getAdresse(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    LatLong l = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                            results[0].getGeometry().getLocation().getLongitude());
                    Marker myMarker = null;
                    markerOptions = new MarkerOptions();
                    markerOptions.position(l)
                            .title(evs.getNomAnnonce())
                            .visible(true);
                    
                    myMarker = new Marker(markerOptions);
                    InfoWindowOptions infoOptions = new InfoWindowOptions();
                    infoOptions.content(evs.getNomAnnonce())
                            .position(l);
                    System.out.println(map.getCenter());
                    
                    InfoWindow window = new InfoWindow(infoOptions);
                    window.open(map, myMarker);
                    map.addMarker(myMarker);
                });
       */
       //nconverti esm city lil format latlon mbaed nhotou
           geocodingService = new GeocodingService();
      //  MapOptions options = new MapOptions();
      geocodingService.geocode(evs.getAdresse(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    LatLong annonceLocation = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                            results[0].getGeometry().getLocation().getLongitude());
                   markerOptions = new MarkerOptions();
                    markerOptions.position(annonceLocation)
                            .title(evs.getNomAnnonce())
                            .visible(true);
           
               
                });
               
     
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
    
        mapOptions.center(new LatLong(36.858900, 10.196500))
           .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
         LatLong annonceLocation = new LatLong(36.858900, 10.196500);
        
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(annonceLocation);
                 
        Marker AnnonceMarker = new Marker(markerOptions1);
  
        
        map.addMarker( AnnonceMarker );
 
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content(evs.getDescription()+"<h2>"+evs.getNomAnnonce() +"</h2>"
                                + evs.getDateDebut()
                                + evs.getDateDebut());

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, AnnonceMarker);
    }
    
    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
