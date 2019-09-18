/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.entities;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 *
 * @author firas
 */
public class FosUser implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
  
  
    private String username;
 
    private String usernameCanonical;

    private String email;

    private String emailCanonical;

    private boolean enabled;

    private String salt;

    private String password;
 

    private Timestamp lastLogin;
  
    private String confirmationToken;
 
    private Timestamp passwordRequestedAt;
  

    private String roles;
  
    private String genre;

    private String nom;
 
    private String prenom;

    private String rue;
 
    private String zipCode;

    private String ville;
 
    private String pays;

    private String phone;
  
    private Integer nbsignal;
 

    private Timestamp datenaiss;
   

    
   

    public FosUser() {
    }

    public FosUser(Integer id) {
        this.id = id;
    }

    public FosUser(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String genre, String nom, String prenom, String rue, String zipCode, String ville, String pays, String phone, Integer nbsignal) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.zipCode = zipCode;
        this.ville = ville;
        this.pays = pays;
        this.phone = phone;
        this.nbsignal = nbsignal;
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, Timestamp datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.datenaiss = datenaiss;
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String genre, String nom, String prenom, String rue, String zipCode, String ville, String pays, String phone, Integer nbsignal, Timestamp datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.genre = genre;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.zipCode = zipCode;
        this.ville = ville;
        this.pays = pays;
        this.phone = phone;
        this.nbsignal = nbsignal;
        this.datenaiss = datenaiss;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Timestamp getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Timestamp passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGender() {
        return genre;
    }

    public void setGender(String genre) {
        this.genre = genre;
    }

    public String getFirstname() {
        return nom;
    }

    public void setFirstname(String nom) {
        this.nom = nom;
    }

    public String getLastname() {
        return prenom;
    }

    public void setLastname(String prenom) {
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(Integer nbsignal) {
        this.nbsignal = nbsignal;
    }

    public Timestamp getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Timestamp datenaiss) {
        this.datenaiss = datenaiss;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FosUser)) {
            return false;
        }
        FosUser other = (FosUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecosmart.entities.FosUser[ id=" + id + " ]";
    }

}
