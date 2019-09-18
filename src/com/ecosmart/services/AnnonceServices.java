/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.services;

import com.ecosmart.util.DataSource;
import com.ecosmart.entities.Annonce;
import com.ecosmart.entities.FosUser;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import com.ecosmart.interfaces.IAnnonceServices;

/**
 *
 * @author asus
 */
public class AnnonceServices implements IAnnonceServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(Annonce e) {
        try {
            String req = "INSERT INTO annonce (nomAnnonce,dateDebut,dateFin,adresse,description,type,heure,photo,id_user) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, e.getNomAnnonce());
            st.setObject(2, e.getDateDebut());
            st.setObject(3, e.getDateFin());
            st.setString(4, e.getAdresse());         
            st.setString(5, e.getDescription());          
            st.setString(6, e.getType());
            st.setObject(7, e.getHeure());
            st.setString(8, e.getPhoto());
            st.setInt(9, e.getIdUser());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public List<Annonce> afficherev() {

        List<Annonce> le = new ArrayList<>();
        try {
            String select = "SELECT * from annonce ";
            Statement statement1 = conn.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Annonce e = new Annonce(result.getInt("id"));
                e.setNomAnnonce(result.getString("nomAnnonce"));
                 e.setIdUser(result.getInt("id_user"));
                e.setDateDebut(result.getTimestamp("dateDebut"));
                e.setDateFin(result.getTimestamp("dateFin"));
                e.setAdresse(result.getString("adresse"));
           
                e.setDescription(result.getString("description"));

                e.setType(result.getString("type"));
                e.setPhoto(result.getString("photo"));
                e.setHeure(result.getTimestamp("heure"));
                e.setRating(result.getInt("rating"));
                e.setNbSignal(result.getInt("nbSignal"));
                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }

    public void addRarting(Annonce e) {
        try {
//            String req=("INSERT INTO `annonce`(`id`, `id_user`, `rating`, `nbrrating`, `nbruser`) VALUES (?,?,?,?,?)");
            String req = ("update `annonce` SET `rating` =? WHERE id = '" + e.getId() + "' ");

            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, e.getRating());

//                      
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
    @Override
    public void update(Annonce e) {
        try {
            String req = "UPDATE annonce SET nomAnnonce = ?, dateDebut= ?, dateFin=?, adresse=?, description=?, type=?, `heure`=?,`photo`=? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, e.getNomAnnonce());
            st.setTimestamp(2, e.getDateDebut());
            st.setTimestamp(3, e.getDateFin());
            st.setString(4, e.getAdresse());
            st.setString(6, e.getDescription());
            st.setString(7, e.getType());
            st.setObject(8, e.getHeure());
            st.setString(9, e.getPhoto());
            System.out.println(e.getId());
            st.setInt(10, e.getId());

            st.executeUpdate();
            System.out.println("annonce updated ");
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `annonce` WHERE `annonce`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            //  st.setString(2, e.getNomAnnonce());

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    long participants;



    @Override
    public boolean EventHasNote(Annonce ev) {
        try {
            String req = "SELECT * FROM annonce WHERE id = '" + ev.getId() + "'";
            stmt = conn.prepareStatement(req);
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de evaluation" + e);
        }
        return false;
    }
//changerpie
    public ObservableList<PieChart.Data> moyEvnType() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT adresse,COUNT(id) FROM `annonce` group by adresse");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
            for (PieChart.Data data : observableList) {
                System.out.println("test ::" + data);

            }
            return observableList;

        } catch (SQLException ex) {
           // Logger.getLogger(ParticipantsEventsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<PieChart.Data> moyAnnRating() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        Annonce e = new Annonce();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT nomAnnonce,count(nbrrating) FROM `annonce` GROUP by nomAnnonce");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }

            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
            for (PieChart.Data data : observableList) {
                System.out.println("test ::" + data);

            }
            return observableList;

        } catch (SQLException ex) {
//            Logger.getLogger(ParticipantsEventsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public float getRateAnnonces(Annonce e) {

        float moy = 0.0f;

        try {
            PreparedStatement st = conn.prepareStatement("SELECT AVG(rating) FROM `annonce` where id = '" + e.getId() + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                moy = rs.getFloat(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;

    }

    public ArrayList<Annonce> getAnnonceBytype(String tes) {
        ArrayList<Annonce> lsttype = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from annonce where type LIKE '" + tes + "' ;");

            while (rs.next()) {
                Annonce ev = new Annonce();
                
                ev.setId(rs.getInt("id"));
                ev.setPhoto(rs.getString("photo"));
                ev.setType(rs.getString("type"));
//                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomAnnonce(rs.getString("nomAnnonce"));
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
           
                ev.setHeure(rs.getTimestamp("heure"));

                lsttype.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsttype;
    }

    public ArrayList<Annonce> getAnnonceByaddresse(String adresse) {
        ArrayList<Annonce> lstadd = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from annonce where e.getAdresse() =" + adresse);

            while (rs.next()) {
                Annonce ev = new Annonce();

                ev.setId(rs.getInt("id"));
                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomAnnonce("nom");
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
            

                lstadd.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstadd;
    }

    public ArrayList<Annonce> getAnnonceByName(String nom) {
        ArrayList<Annonce> lstn = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from annonce where e.getNom_evenement =" + nom);

            while (rs.next()) {
                Annonce ev = new Annonce();

                ev.setId(rs.getInt("id"));
                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomAnnonce("nom_evenement");
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
           

                lstn.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstn;
    }

    @Override
    public Annonce FindAnnonce(int id) {
        Annonce ev = null;

        try {
            String select = "SELECT * FROM annonce WHERE  id = '" + id + "' ";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                ev = new Annonce();
                ev.setId(id);
                ev.setNomAnnonce(result.getString("nomAnnonce"));
                ev.setDateDebut(result.getTimestamp("dateDebut"));
                ev.setDateFin(result.getTimestamp("dateFin"));
                ev.setDescription(result.getString("description"));
                ev.setType(result.getString("type"));
      
                ev.setPhoto(result.getNString("photo"));

                AnnonceServices es = new AnnonceServices();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return ev;
    }

    
public ArrayList<Annonce> FindBySignal() {
        
        ArrayList<Annonce> sigev = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `annonce` WHERE nbSignal >= 7 " );

            while (rs.next()) {
Annonce e = new Annonce();
                e.setId(rs.getInt("id"));
               
                e.setNomAnnonce(rs.getString("nomAnnonce"));
                
               e.setNbSignal(rs.getInt("nbSignal"));

                sigev.add(e);

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sigev;
    }

    @Override
    public ObservableList<Annonce> afficherAnnonce() {
         ObservableList<Annonce> AnnonceList = FXCollections.observableArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from annonce");
            while (rs.next()) {
                AnnonceList.add(new Annonce(rs.getInt("id"), rs.getString("nomAnnonce"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateFin"), rs.getString("adresse"), rs.getString("description"), rs.getString("type"), rs.getTimestamp("heure"), rs.getString("photo")));
   

     
            
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return AnnonceList;
    }
 public ObservableList<Annonce> rechercheBack(String s) {
        ObservableList<Annonce> AnnonceList = FXCollections.observableArrayList();
        try {
            String req = "select * from annonce where id LIKE '%" + s + "%' " ;
            PreparedStatement stmt = conn.prepareStatement(req);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 AnnonceList.add(new Annonce(rs.getInt("id"), rs.getString("nomAnnonce"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateFin"), rs.getString("adresse"), rs.getString("description"), rs.getString("type"), rs.getTimestamp("heure"), rs.getString("photo")));
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AnnonceList;
    }

    @Override
    public ObservableList<Annonce> afficherLocal2() {
    ObservableList<Annonce> AnnonceList = FXCollections.observableArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from local");
            while (rs.next()) {
                AnnonceList.add(new Annonce(rs.getInt("id"), rs.getString("nomAnnonce"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateFin"), rs.getString("adresse"), rs.getString("description"), rs.getString("type"), rs.getTimestamp("heure"), rs.getString("photo")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return AnnonceList;
    }

    @Override
    public Annonce afficherLocalUn(int id) {
 Annonce l = null;
        try {
            String req = "select * from annonce where id=?";
            PreparedStatement stmt = conn.prepareStatement(req);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                l =  new Annonce(rs.getInt("id"), rs.getString("nomAnnonce"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateFin"), rs.getString("adresse"), rs.getString("description"), rs.getString("type"), rs.getTimestamp("heure"), rs.getString("photo"));
          
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l;

    }
       public void signaler(Annonce e, FosUser u) {

        String req = "INSERT INTO annonce_like (id_user,id_annonce) VALUES (?,?)";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(req);
            st.setInt(1, u.getId());
            st.setInt(2, e.getId());
            
            st.executeUpdate();
            System.out.println("liker");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void passignaler(Annonce w, FosUser u) {

        try {
            String req = "DELETE FROM annonce_like WHERE id_annonce = ? and  id_user = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, w.getId());
            st.setInt(2, u.getId());
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public double nbAnnoncev2() {
        double res = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as vall from annonce ");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                res = rs.getInt("vall");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;

    }
  public double nbType2() {
        double result = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as val from annonce WHERE type='Probleme municipalite'");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("val");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }
public double nbAnnonce() {
        double res = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as vall from annonce");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                res = rs.getInt("vall");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;

    }

    public double nbType1() {
        double result = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as val from annonce WHERE type='pollution'");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("val");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }
    
    
}
