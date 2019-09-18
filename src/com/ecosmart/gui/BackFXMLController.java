/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class BackFXMLController implements Initializable {

    @FXML
    private ImageView btn_profile;
    private ImageView btn_shop;
    @FXML
    private ImageView btn_event;
    private ImageView btn_association;
    private ImageView btn_local;
    @FXML
    private ImageView btn_user;
    @FXML
    private AnchorPane anch_profile;
    @FXML
    private AnchorPane pan_profile;
    @FXML
    private AnchorPane anch_shop;
    @FXML
    private AnchorPane pan_shop;
    @FXML
    private AnchorPane anch_event;
    @FXML
    private AnchorPane pan_event;
    @FXML
    private AnchorPane anch_workshop;
    @FXML
    private AnchorPane pan_workshop;
    @FXML
    private AnchorPane anch_local;
    @FXML
    private AnchorPane pan_local;
    @FXML
    private AnchorPane anch_user;
    private AnchorPane pan_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(btn_event, new Tooltip("Gestion des Annonces"));
        Tooltip.install(btn_association, new Tooltip("Gestion des Association"));
        Tooltip.install(btn_profile, new Tooltip("Gestion du Réseau Social"));
;
        Tooltip.install(btn_shop, new Tooltip("Gestion de la Boutique En Ligne"));
        Tooltip.install(btn_user, new Tooltip("Gestion des Utilisateurs"));
    }

    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getTarget() == btn_profile) {
            ScrollPane p = FXMLLoader.load(getClass().getResource("SocialBackFXML.fxml"));
            pan_profile.getChildren().setAll(p);
            anch_profile.setVisible(true);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_shop) {
            anch_profile.setVisible(false);
            anch_shop.setVisible(true);
            anch_event.setVisible(false);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_event) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("AnnonceStat.fxml"));
            pan_event.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(true);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        } else if (event.getTarget() == btn_association) {
            ScrollPane p = FXMLLoader.load(getClass().getResource("StatistiquesAssociation.fxml"));
            pan_workshop.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(false);
            anch_workshop.setVisible(true);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
        
        } else if (event.getTarget() == btn_user) {
           AnchorPane p = FXMLLoader.load(getClass().getResource("AdminUserFXML.fxml"));
           pan_event.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(true);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);
           /* pan_user.getChildren().setAll(p);
            anch_profile.setVisible(false);
            anch_shop.setVisible(false);
            anch_event.setVisible(true);
            anch_workshop.setVisible(false);
            anch_local.setVisible(false);
            anch_user.setVisible(false);*/
            System.err.println("xxx");
        }
    }

}
