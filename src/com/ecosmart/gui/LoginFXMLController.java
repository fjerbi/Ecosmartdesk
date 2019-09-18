/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.ecosmart.entities.FosUser;
import com.ecosmart.services.FosUserServices;
import com.ecosmart.util.DataSource;
import com.ecosmart.util.SessionUser;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class LoginFXMLController implements Initializable {
    
    @FXML
    private JFXTextField txf_username;
    @FXML
    private JFXPasswordField txf_password;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXButton btn_signup;
    @FXML
    private JFXButton btn_reset;

    public void loginAction(ActionEvent event) {
        String username = txf_username.getText().toString();
        String password = txf_password.getText().toString();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }

    @FXML
    private void handle(MouseEvent event) {
    }

    @FXML
    private void showUserAdd(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("RegisterFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void AuthentificationAction(ActionEvent event) throws IOException {

        FosUserServices us = new FosUserServices();
        FosUser u = new FosUser();

        u.setUsername(txf_username.getText());
        u.setPassword(txf_password.getText());

        if (us.Authentification(u)) {

            if (txf_username.getText().equals("admin")) {

                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkEnabled(txf_username.getText())) {
                if (us.checkProfile(SessionUser.getUser().getId())) {
                    SessionUser.getInstance(SessionUser.getUser());
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("FrontFXML.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide(); //optional
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                    app_stage.setOnCloseRequest(e -> {
                    });
                } else {
                    SessionUser.getFirstInstance(SessionUser.getUser());
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("FrontFXML.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide(); //optional
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                }

            } else if (!us.checkEnabled(txf_username.getText())) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("ConfirmAccountFXML.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez vérifier vos champs!");
            alert.showAndWait();
            System.out.println("no");
        }

    }

    @FXML
    private void ResetAction(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("ResetFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
