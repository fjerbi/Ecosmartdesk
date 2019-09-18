/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author achraf
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton evenements;
    @FXML
    private JFXButton annonces;
    @FXML
    private JFXButton produitsrecycles;
    @FXML
    private JFXButton associations;
    @FXML
    private JFXButton reclamations;
    @FXML
    private JFXButton challenges;
    @FXML
    private JFXButton utilisateurs;
    @FXML
    private JFXButton dashbord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        associations.setOnAction(ev->{
              Parent home_page_parent;
            try {
                home_page_parent = FXMLLoader.load(getClass().getResource("Association.fxml"));
                   Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        });
        annonces.setOnAction(ev->{
              Parent home_page_parent;
            try {
                 AnchorPane p = FXMLLoader.load(getClass().getResource("AnnonceStat.fxml"));
                
                   Scene home_page_scene = new Scene(p);
        Stage app_stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        });
         utilisateurs.setOnAction(ev->{
              Parent home_page_parent;
            try {
                 AnchorPane p = FXMLLoader.load(getClass().getResource("AdminUserFXML.fxml"));
                
                   Scene home_page_scene = new Scene(p);
        Stage app_stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
        });
    }    

    @FXML
    private void clickevent(ActionEvent event) {
    }
   
}
