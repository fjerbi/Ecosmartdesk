/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.ecosmart.util.SessionUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class FrontFXMLController implements Initializable {

    @FXML
    private AnchorPane facebook;
    @FXML
    private AnchorPane mail;
    @FXML
    private AnchorPane twitter;
    @FXML
    private AnchorPane linkedin;
    @FXML
    private AnchorPane flickr;
    @FXML
    private AnchorPane yahoo;
    @FXML
    private AnchorPane skype;
   @FXML
    private Label username;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      username.setText(SessionUser.getUser().getFirstname() + " " + SessionUser.getUser().getLastname());
    }    

    @FXML
    private void switchStage(MouseEvent event) {
    }
    
}
