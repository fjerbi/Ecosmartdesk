/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.gui;

import com.jfoenix.controls.JFXButton;
import com.ecosmart.services.AnnonceServices;
import com.ecosmart.util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AnnonceStatController implements Initializable {

    @FXML
    private ScrollPane scrollStat;
    @FXML
    private PieChart pie1;
    AnnonceServices es = new AnnonceServices();
    private PieChart pie2;
    @FXML
    private AnchorPane anchv;
  @FXML
    private Label lab1;
    @FXML
    private Pane h1;
    private ProgressBar progress1;
    private Label tot1;
    private Label pourcentage;
    private Label pr1;
      AnnonceServices an = new AnnonceServices();
    @FXML
    private Button signabtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadchart();
       
    }

    public AnnonceStatController() {
        Connection conn = DataSource.getInstance().getConnection();
    }

    private void loadchart() {

        TYPEE();

    }

    public void TYPEE() {

        pie1.getData().clear();
        System.out.println("++++++++++++" + es.moyEvnType());
        pie1.setData(es.moyEvnType());
        pie1.setAnimated(true);
     
     
        pie1.setVisible(true);
        pie1.setTitle("Annonce PAR ZONE");
  for(final PieChart.Data data : pie1.getData()){
          
           Label lbl = new Label();
           lbl.setText(data.getPieValue()+"");
           h1.getChildren().add(lbl);
           System.out.println(data.getPieValue());
       }
        System.out.println(pie1);
    }

     
@FXML
    void signabtnaction(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("Annoncesignal.fxml"));
        this.anchv.getChildren().setAll(p);

    }
    
    
void gotoAd(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("AfficheBackFXML.fxml"));
        this.anchv.getChildren().setAll(p);

    }
     public void Prog1() {
        double x = an.nbType1();
        double m = an.nbAnnonce();
        tot1.setText("Nombre Total : " + ((Double) m).toString());
        double prc = (x / m) * 100;
        float k = (float) Math.round(prc * 100) / 100;
        pourcentage.setText((String.valueOf(k)) + "% Signal");
        float j = k / 100;
        progress1.setProgress(j);
        pr1.setText("% au total");
        System.out.println("pourcentage ====>" + j);

    }
}
