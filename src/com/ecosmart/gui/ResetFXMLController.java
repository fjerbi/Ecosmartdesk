/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXTextField;
import com.ecosmart.entities.FosUser;
import static com.ecosmart.gui.RegisterFXMLController.checkPassword;
import static com.ecosmart.gui.RegisterFXMLController.validPassword;
import com.ecosmart.services.FosUserServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ResetFXMLController implements Initializable {

    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXTextField tfpassword;
    @FXML
    private Label testcode;
    @FXML
    private Label testmail;
    @FXML
    private Label testpassword;
    @FXML
    private JFXTextField tfcode;
    @FXML
    private JFXTextField tfmail2;
    @FXML
    private Label testemail1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void verifMail(MouseEvent event) {
        FosUserServices fs = new FosUserServices();

        if (fs.CheckIfUserExist(tfemail.getText())) {
            testemail1.setTextFill(Paint.valueOf("#0000FF"));
            testemail1.setText("Email valide");
        } else {
            testemail1.setTextFill(Paint.valueOf("RED"));
            testemail1.setText("Email non existant");
        }

    }

    @FXML
    private void verfiCode(MouseEvent event) {
        FosUserServices fs = new FosUserServices();
        if (fs.Checkconfirmationtoken(tfmail2.getText(), tfcode.getText())) {
            testcode.setTextFill(Paint.valueOf("#0000FF"));
            testcode.setText("Code valide");
        } else {
            testcode.setTextFill(Paint.valueOf("RED"));
            testcode.setText("Code non valide");

        }
    }

    public static boolean validPassword(String password) {
        if (password.length() > 7) {
            return true;

        }
        return false;
    }

    public static boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            } else if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            if (hasCap && hasLow && hasNum) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void verifPassword(MouseEvent event) {
        if (!validPassword(tfpassword.getText())) {
            testpassword.setTextFill(Paint.valueOf("RED"));
            testpassword.setText("Le mot de passe doit contenir plus que 7 caractères.");
        } else if (!checkPassword(tfpassword.getText())) {
            testpassword.setTextFill(Paint.valueOf("RED"));
            testpassword.setText("Le mot de passe doit contenir des lettres en majuscule, en miniscule ainsi que des chiffres.");
        } else {
            testpassword.setTextFill(Paint.valueOf("#0000FF"));
            testpassword.setText("Mot de passe valide");
        }
    }

    @FXML
    private void UpdatePassword(ActionEvent event) throws IOException {
        FosUserServices fs = new FosUserServices();
        FosUser u = new FosUser();
        u.setEmail(tfmail2.getText());
        u.setPassword(tfpassword.getText());

        if ((testcode.getText().equals("Code valide")) && (testpassword.getText().equals("Mot de passe valide")) && (testmail.getText().equals("Email valide"))) {

            fs.ressettingpassword(u);

            Parent home_page_parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } else {
            System.out.println("erreur");
        }
    }

    @FXML
    private void verifMailb(MouseEvent event) {

        FosUserServices fs = new FosUserServices();

        if (fs.CheckIfUserExist(tfmail2.getText())) {
            testmail.setTextFill(Paint.valueOf("#0000FF"));
            testmail.setText("Email valide");
        } else {
            testmail.setTextFill(Paint.valueOf("RED"));
            testmail.setText("Email non existant");
        }
    }

    @FXML
    private void genererAction(ActionEvent event) {
        FosUserServices fs = new FosUserServices();
        FosUser u = new FosUser();
        u.setEmail(tfemail.getText());
        if (testemail1.getText().equals("Email valide")) {
            fs.SendMailAndAddTokenToUser(u);
            testemail1.setTextFill(Paint.valueOf("#0000FF"));
            testemail1.setText("Mail envoyé");

        } else {
            testemail1.setTextFill(Paint.valueOf("RED"));
            testemail1.setText("Vérifiez le mail");
        }
    }

}
