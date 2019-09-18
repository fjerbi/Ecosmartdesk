/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author achraf
 */
public class DashbordController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger menuBTN;
    @FXML
    private ImageView profile;
    @FXML
    private Label username;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView notification;
    @FXML
    private Label mail;
   
    @FXML
    private JFXDrawer menu;
   public Label getMail() {
        return mail;
    }

    public void setMail(Label mail) {
        this.mail = mail;
    }
         public static AnchorPane rootS;

   

    public Label getUsername() {
        return username;
    }

    public void setUsername(Label username) {
        this.username = username;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rootS = root;
           try {
            VBox box = FXMLLoader.load(getClass().getResource("menu.fxml")); 
            menu.setSidePane(box);
        } catch (IOException ex) {
          
        }
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuBTN);
         transition.setRate(-1);
        menuBTN.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(menu.isOpened())
            {
                menu.close();
            }else
                menu.open();
        });
        // TODO
    } 
    
   
    
    
    

    
}
