/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.ecosmart.entities.FosUser;

import com.ecosmart.services.FosUserServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AdminUserFXMLController implements Initializable {

    @FXML
    private TableView<FosUser> tbl_users = new TableView<>();
    @FXML
    private SplitPane main;
    @FXML
    private AnchorPane mainleft;
    @FXML
    private AnchorPane mainright;
    @FXML
    private Label lb_title;
    @FXML
    private Label txtNumber;

    int userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FosUserServices fs = new FosUserServices();

        this.tbl_users.prefWidthProperty().bind(main.widthProperty().divide(1.5));
        this.mainleft.prefWidthProperty().bind(main.widthProperty().divide(6));
        this.mainright.prefWidthProperty().bind(main.widthProperty().divide(2));

        TableColumn<FosUser, String> colUsername = new TableColumn("Username");
        TableColumn<FosUser, String> colEmail = new TableColumn("Email");
        TableColumn<FosUser, String> colPrenom = new TableColumn("Prénom");
        TableColumn<FosUser, String> colNom = new TableColumn("Nom");
        TableColumn<FosUser, String> colSexe = new TableColumn("Sexe");
        TableColumn<FosUser, String> colPays = new TableColumn("Pays");
        TableColumn<FosUser, String> colVille = new TableColumn("Ville");
        TableColumn<FosUser, String> colRue = new TableColumn("Rue");
        TableColumn<FosUser, String> colTel = new TableColumn("Téléphone");
        tbl_users.getColumns().addAll(colUsername, colEmail, colPrenom, colNom, colSexe, colPays, colVille, colRue, colTel);
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colSexe.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        colRue.setCellValueFactory(new PropertyValueFactory<>("rue"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tbl_users.setItems(fs.findAll());
        int number = tbl_users.getItems().size();
        txtNumber.setText(String.valueOf(number));

        tbl_users.setOnMouseClicked(e -> {
            FosUser u = tbl_users.getSelectionModel().getSelectedItem();

            userId = u.getId();
        });

    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        Image img = new Image("/ressources/img/logos/app.png", 100, 100, false, false);

        Notifications notificationBuilder = Notifications.create().title("Supprimer Utilisateur").text("Utilisateur Supprimé!! ").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showWarning();
        notificationBuilder.show();
        FosUserServices fs = new FosUserServices();
        FosUser u = new FosUser();
        u.setId(userId);
        fs.delete(u);
        tbl_users.setItems(fs.findAll());
    }
    @FXML
    private void updateUser(ActionEvent event) {
      Image img = new Image("/ressources/img/logos/app.png", 100, 100, false, false);

        Notifications notificationBuilder = Notifications.create().title("Supprimer Utilisateur").text("Utilisateur Supprimé!! ").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showWarning();
        notificationBuilder.show();
        FosUserServices fs = new FosUserServices();
        FosUser u = new FosUser();
        u.setId(userId);
        fs.update(u);
        tbl_users.setItems(fs.findAll());
    }
}
