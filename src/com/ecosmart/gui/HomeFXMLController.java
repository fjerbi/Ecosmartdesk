/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.ecosmart.util.SessionUser;
import com.ecosmart.util.data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private ImageView btn_profile;
    @FXML
    private ImageView btn_shop;
    @FXML
    private ImageView btn_event;
    @FXML
    private ImageView btn_local;
    @FXML
    private ImageView btn_user;
    @FXML
    private ImageView btn_workshop;
    @FXML
    private AnchorPane anch_workshop;
    @FXML
    private AnchorPane anch_profile;
    @FXML
    private AnchorPane anch_shop;
    @FXML
    private AnchorPane anch_event;
    @FXML
    private AnchorPane anch_local;
    @FXML
    private AnchorPane anch_user;
    @FXML
    private AnchorPane pan_profile;
    @FXML
    private AnchorPane pan_shop;
    @FXML
    private AnchorPane pan_event;
    @FXML
    private AnchorPane pan_workshop;
    @FXML
    private AnchorPane pan_local;
    @FXML
    private AnchorPane pan_user;
 @FXML
    private ImageView donate;


         

        // TODO
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getTarget() == btn_profile) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("SocialFXML.fxml"));
            pan_profile.getChildren().setAll(p);
            anch_profile.setVisible(true);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_shop) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("AjoutPanier.fxml"));
            pan_shop.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(true);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_event) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
            pan_event.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(true);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_workshop) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
            pan_workshop.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(true);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_local) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            pan_local.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(true);
            anch_user.setVisible(false);
         } else if (event.getTarget() == btn_user) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("MoncompteFXML.fxml"));
            pan_user.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(true);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane pa = new AnchorPane();
        try {
            pa = FXMLLoader.load(getClass().getResource("AnnonceAffFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pan_profile.getChildren().setAll(pa);
        anch_profile.setVisible(true);
        Tooltip.install(btn_event, new Tooltip("Annonce"));
        Tooltip.install(btn_workshop, new Tooltip("assosioatio"));
      
      
        Tooltip.install(btn_shop, new Tooltip("Boutique En Ligne"));
        Tooltip.install(btn_user, new Tooltip("Utilisateur"));
        data.ip = "localhost";
        data.name = SessionUser.getUser().getFirstname() + " " + SessionUser.getUser().getLastname();
        data.port = 10001;
        Pane p;

    }
 

}
