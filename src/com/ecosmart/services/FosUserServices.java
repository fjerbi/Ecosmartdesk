/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.services;

import com.ecosmart.util.DataSource;
import com.ecosmart.entities.FosUser;
import com.ecosmart.entities.Profile;
import com.ecosmart.interfaces.IFosUserServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import com.ecosmart.util.EmailAttachmentSender;
import java.sql.Timestamp;
import com.ecosmart.util.BCrypt;
import com.ecosmart.util.SessionUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

/**
 *
 * @author hatem
 */
public class FosUserServices implements IFosUserServices {

//    @PersistenceUnit(unitName = "RawdaPU")
//    static private EntityManagerFactory emf;
//    static EntityManager em;
//
//    static {
//        try {
//            emf = Persistence.createEntityManagerFactory("RawdaPU");
//            em = emf.createEntityManager();
//        } catch (Exception e) {
//            System.out.println("Fatal: Unable to create entity manager factory");
//            e.printStackTrace();
//        }
//    }
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    // public int logUser;

    @Override
    public boolean Authentification(FosUser u) {
        boolean status = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getUsername());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {
                    
                        
                    status = true;
                    u = this.findById(rs.getInt("id"));
                    SessionUser.setUser(u);
                    System.out.println(u.getId());

                    
                    
                    // logUser=rs.getInt(1);  

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    

    @Override
    public FosUser getUserbyId(int id) {
        FosUser u = null;
        try {
            String req = "select * from fos_user where id=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getInt(21),
                        (Timestamp) rs.getObject(22));

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    public FosUser findById(Integer id) {
        FosUser u = null;
        try {
            String req = "select * from fos_user where id=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getInt(21),
                        (Timestamp) rs.getObject(22));

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    @Override
    public void create(FosUser u) {

        try {

            String req = "INSERT INTO `fos_user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `genre`, `nom`, `prenom`, `rue`, `zip_code`, `ville`, `pays`, `phone`, `nbsignal`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            String token = UUID.randomUUID().toString();
            String Role = u.getRoles();
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5, 0);
            st.setString(6, u.getSalt());
            st.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setString(9, token);
            st.setObject(10, u.getPasswordRequestedAt());
            st.setString(11, "a:1:{i:0;s:9:\"ROLE_USER\";}");
            st.setString(12, u.getGender());
            st.setString(13, u.getFirstname());
            st.setString(14, u.getLastname());
            st.setString(15, u.getRue());
            st.setString(16, u.getZipCode());
            st.setString(17, u.getVille());
            st.setString(18, u.getPays());
            st.setString(19, u.getPhone());
            st.setInt(20, 0);

            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Confirmation du Compte Ecosmart", "<h1> Cher utilisateur,</h1></br> <p>Nous avons l'honneur de vous accueiller parmi notre communauté. Vous devez confirmer votre compte en copiant le code ci-dessous lors de votre prochaine authentification.</p>"+token+ "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Ecosmart</h3>");

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(FosUser u) {
        try {
            String req = "UPDATE `fos_user` SET `username`=?,`username_canonical`=?,`email`=?,`email_canonical`=?,`enabled`=?,`salt`=?,`password`=?,`last_login`=?,`confirmation_token`=?,`password_requested_at`=?,`roles`=?,`genre`=?,`nom`=?,`prenom`=?,`rue`=?,`zip_code`=?,`ville`=?,`pays`=?,`phone`=?,`nbsignal`=? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5, 0);
            st.setString(6, u.getSalt());
            st.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setString(9, null);
            st.setObject(10, u.getPasswordRequestedAt());
            st.setString(11, "a:1:{i:0;s:9:\"ROLE_USER\";}");
            st.setString(12, u.getGender());
            st.setString(13, u.getFirstname());
            st.setString(14, u.getLastname());
            st.setString(15, u.getRue());
            st.setString(16, u.getZipCode());
            st.setString(17, u.getVille());
            st.setString(18, u.getPays());
            st.setString(19, u.getPhone());
            st.setInt(20, 0);
            st.setInt(21, u.getId());
            

            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(FosUser u) {

        try {
            String req = "delete from fos_user where id = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ObservableList<FosUser> findAll() {
        ObservableList<FosUser> users=FXCollections.observableArrayList();

        try {
            String req = "select * from fos_user";
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FosUser u = new FosUser(rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getInt(21))
                        ;
               
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;

    }

    public String CheckRole(FosUser u) {
        String role = null;
        if (u.getRoles().equals("a:1:{i:0;s:5:\"ADMIN\";}")) {
            role = "admin";
        } else {
            role = "user";
        }

        return role;
    }

    @Override
    public FosUser getUserByEmail(String email) {

        FosUser u = null;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getInt(21),
                        (Timestamp) rs.getObject(22));

            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;

    }

    public void SendMailAndAddTokenToUser(FosUser u) {
        try {
            String token = UUID.randomUUID().toString();
            String req = "update fos_user SET confirmation_token=?  where email=?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, token);
            st.setString(2, u.getEmail());

            st.executeUpdate();
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Code de récupération de mot de passe ", "<h1> Cher utilisateur,</h1></br> <p>Veuillez trouver ci-dessous le code pour la recupération de votre mot de passe.</p>"+token+ "</br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Ecosmart+</h3>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

    @Override
    public Boolean CheckToken(FosUser u, String token) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? and confirmation_token" + "=?  ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getEmail());
            st.setString(2, token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;

    }

    @Override
    public void ressettingpassword(FosUser u) {

        try {
            String req = "update fos_user SET password=?,confirmation_token=?  where email=?";
            PreparedStatement st = conn.prepareStatement(req);
            String password = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
            st.setString(1, password);
            st.setString(2, null);
            st.setString(3, u.getEmail());
            st.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Boolean Checkconfirmationtoken(String email, String Token) {
        boolean exisit = false;
        try {
            String req = "select * from fos_user where email=? and confirmation_token=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, Token);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                exisit = true;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

    @Override
    public void ConfirmAccount(FosUser u) {
        String req2 = "update fos_user set enabled=? , confirmation_token=? where email=?  ";

        try {
            PreparedStatement st1 = conn.prepareStatement(req2);
            st1.setInt(1, 1);
            st1.setString(2, null);
            st1.setString(3, u.getEmail());
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Compte Ecosmart confirmé ", "<h1> Cher utilisateur,</h1></br> <p>Votre compte a été activé. Nous vous souhaitons une bonne expérience sur notre plateforme.</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3> L'équipe de Ecosmart +</h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkEnabled(String username) {
        boolean exisit = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getInt(6) == 0) {
                    exisit = false;
                } else {
                    exisit = true;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

    public boolean checkProfile(Integer idUser) {
        boolean exisit = false;
        try {
            String req = "select * from profile where id_user=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idUser);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                exisit = true;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

    @Override
    public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

    @Override
    public Profile findProfileById(Integer idUser) {
        Profile p = null;
        try {
            String req = "select * from profile where id_user=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idUser);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                p = new Profile(rs.getInt("id"), rs.getString("tagline"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getString("about_me"), idUser);
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return p;
    }

}
