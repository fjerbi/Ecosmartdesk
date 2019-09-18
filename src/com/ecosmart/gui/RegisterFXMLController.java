/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXTextField;
import com.ecosmart.entities.FosUser;
import com.ecosmart.services.FosUserServices;
import com.ecosmart.util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private ComboBox<String> cbgenre;
    @FXML
    private ComboBox<String> txtpays;
    @FXML
    private JFXTextField tctusername;
    @FXML
    private JFXTextField txtnom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXTextField txtconf;
    @FXML
    private JFXTextField txtpassword;
    @FXML
    private JFXTextField txtlasname;
    @FXML
    private JFXTextField txtville;
    @FXML
    private JFXTextField txtrue;
    @FXML
    private JFXTextField txtzipcode;

    @FXML
    private Label pwverif;
    ObservableList<String> sexelist;

    Connection conn = DataSource.getInstance().getConnection();
    @FXML
    private Label confpass;
    @FXML
    private Label testmail;
    @FXML
    private Label testusername;
    @FXML
    private Label testprenom;
    @FXML
    private Label testnom;
    @FXML
    private Label testville;
    @FXML
    private Label testrue;
    @FXML
    private Label testpostal;
 
    @FXML
    private Label errorReg;

    public RegisterFXMLController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> sexe = new ArrayList<>();
        sexe.add("Femme");
        sexe.add("Homme");
        sexelist = FXCollections.observableList(sexe);
        cbgenre.setItems(sexelist);

        ObservableList<String> countries = Stream.of(Locale.getISOCountries())
                .map(locales -> new Locale("", locales))
                .map(Locale::getDisplayCountry)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        txtpays.setItems(countries);

    }

    @FXML
    private void AjouterUser(ActionEvent event) throws SQLException, IOException {
        FosUserServices fs = new FosUserServices();
        FosUser u = new FosUser();

        if ((testusername.getText().equals("Pseudo valide")) && (testmail.getText().equals("Email valide")) && (pwverif.getText().equals("Mot de passe valide"))
                && (confpass.getText().equals("Mot de passe confirmé")) && (testprenom.getText().equals("Prénom valide")) && (testnom.getText().equals("Nom valide"))
                && (testville.getText().equals("Nom de ville valide")) && (testrue.getText().equals("Format valide")) 
                ) {

            u.setUsername(tctusername.getText());
            u.setUsernameCanonical(tctusername.getText());
            u.setEmail(txtemail.getText());
            u.setEmailCanonical(txtemail.getText());
            u.setPassword(txtpassword.getText());
            u.setGender(cbgenre.getValue());
            u.setFirstname(txtnom.getText());
            u.setLastname(txtlasname.getText());
            u.setRue(txtrue.getText());
            u.setZipCode(txtzipcode.getText());
            u.setVille(txtville.getText());
            u.setPays(txtpays.getValue());
            //u.setPhone(txtphone.getText());
            fs.create(u);
            System.out.println("Ajout effectué avec succès");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        } else {
            errorReg.setText("Veuillez vérifier vos données !! ");

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
    private void pw(MouseEvent event) {

        if (!validPassword(txtpassword.getText())) {
            pwverif.setTextFill(Paint.valueOf("RED"));
            pwverif.setText("Le mot de passe doit contenir plus que 7 caractères.");
        } else if (!checkPassword(txtpassword.getText())) {
            pwverif.setTextFill(Paint.valueOf("RED"));
            pwverif.setText("Le mot de passe doit contenir des lettres en majuscule, en miniscule ainsi que des chiffres.");
        } else {
            pwverif.setTextFill(Paint.valueOf("#0000FF"));
            pwverif.setText("Mot de passe valide");
        }
    }

    public static boolean confPassword(String password, String conf) {

        if (password.equals(conf)) {

            return true;
        }

        return false;
    }

    @FXML
    private void ConfPw(MouseEvent event) {
        if (confPassword(txtpassword.getText(), txtconf.getText())) {

            confpass.setTextFill(Paint.valueOf("#0000FF"));
            confpass.setText("Mot de passe confirmé");

        } else if (!confPassword(txtpassword.getText(), txtconf.getText())) {

            confpass.setTextFill(Paint.valueOf("RED"));
            confpass.setText("Le mot de passe et la confirmation ne sont pas identiques.");

        }
    }

    public boolean checkMail(String a) {

        Boolean valide = false;
        int i, j, k;
        for (j = 1; j < a.length(); j++) {
            if (a.charAt(j) == '@') {
                if (j < a.length() - 4) {
                    for (k = j; k < a.length() - 2; k++) {
                        if (a.charAt(k) == '.') {
                            valide = true;
                        }
                    }
                }
            }
        }

        return valide;
    }

    @FXML
    private void verifMail(MouseEvent event) {
        FosUserServices fs = new FosUserServices();
        if (checkMail(txtemail.getText())) {
            if (fs.CheckIfUserExist(txtemail.getText())) {
                testmail.setTextFill(Paint.valueOf("RED"));
                testmail.setText("Utilisateur existe déjà ");
            } else {
                testmail.setTextFill(Paint.valueOf("#0000FF"));
                testmail.setText("Email valide");
            }
        } else {
            testmail.setTextFill(Paint.valueOf("RED"));
            testmail.setText("Vérifiez le format de votre adresse mail ");
        }

    }

    public static boolean checkUsername(String username) {

        if (username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b")) {
            return true;
        }

        return false;
    }

    @FXML
    private void verifUsername(MouseEvent event) {
        FosUserServices fs = new FosUserServices();
        if (checkUsername(tctusername.getText())) {
            if (fs.CheckIfUsernameExist(tctusername.getText())) {
                testusername.setTextFill(Paint.valueOf("RED"));
                testusername.setText("Pseudo existe déjà ");
            } else {
                testusername.setTextFill(Paint.valueOf("#0000FF"));
                testusername.setText("Pseudo valide");

            }

        } else {
            testusername.setTextFill(Paint.valueOf("RED"));
            testusername.setText("Vérifiez le format de votre pseudo ");
        }

    }

    @FXML
    private void checkPrenom(MouseEvent event) {
        if (!txtnom.getText().matches("[a-z A-Z]+")) {
            testprenom.setTextFill(Paint.valueOf("RED"));
            testprenom.setText("Un prénom ne doit contenir que des lettres ");
        } else {
            testprenom.setTextFill(Paint.valueOf("#0000FF"));
            testprenom.setText("Prénom valide");
        }
    }

    @FXML
    private void checkNom(MouseEvent event) {

        if (!txtlasname.getText().matches("[a-z A-Z]+")) {
            testnom.setTextFill(Paint.valueOf("RED"));
            testnom.setText("Un nom ne doit contenir que des lettres ");

        } else {
            testnom.setTextFill(Paint.valueOf("#0000FF"));
            testnom.setText("Nom valide");
        }
    }
  
    @FXML
    private void checkVille(MouseEvent event) {

        if (!txtville.getText().matches("[a-z A-Z]+")) {
            testville.setTextFill(Paint.valueOf("RED"));
            testville.setText("Un nom de ville ne doit contenir que des lettres ");

        } else {
            testville.setTextFill(Paint.valueOf("#0000FF"));
            testville.setText("Nom de ville valide");
        }
    }
/*
    @FXML
    private void checkPostal(MouseEvent event) {
        if ((!txtzipcode.getText().matches("[a-zA-Z0-9]+$")) || (txtzipcode.getText().length() > 10)) {
            testpostal.setTextFill(Paint.valueOf("RED"));
            testpostal.setText("Vérifier le format ");
        } else {
            testpostal.setTextFill(Paint.valueOf("#0000FF"));
            testpostal.setText("Format valide");
        }
    }

*/

    @FXML
    private void checkRue(MouseEvent event) {

        if (!txtrue.getText().matches("^[^ ]+( [^ ]+)*$")) {

            testrue.setTextFill(Paint.valueOf("RED"));
            testrue.setText("Vérifier le format ");
        } else {
            testrue.setTextFill(Paint.valueOf("#0000FF"));
            testrue.setText("Format valide");

        }
    }

}
