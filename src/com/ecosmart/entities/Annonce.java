/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.entities;

import java.io.Serializable;
import java.sql.Timestamp;


public class Annonce implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
 
    private String nomAnnonce;

    private Timestamp dateDebut;

    private Timestamp dateFin;

    private String adresse;

    private double prix;

    private String description;

    private int nbPlace;
 
    private Integer nbSignal;

    private String type;
   
    private Timestamp heure;
   
    private String photo;
 
    private Integer rating;

    private Integer nbrrating;

    private Integer nbruser;
  
    private Integer idUser;


    public Annonce() {
    }

    public Annonce(Integer id) {
        this.id = id;
    }

    public Annonce(Integer id, Integer id_user, Integer nbruser, Integer nbrrating, Integer rating) {
        this.id = id;
        this.idUser = id_user;
        this.nbruser = nbruser;
        this.rating = rating;
        this.nbrrating = nbrrating;
        this.nbruser = nbruser;
    }

    public Annonce(Integer id, String nomAnnonce, Timestamp dateDebut, Timestamp dateFin, String adresse, String description, String type,
            Timestamp heure, String photo
    ) {
        this.id = id;
        this.nomAnnonce = nomAnnonce;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
     
        this.description = description;

        this.type = type;
        this.heure = heure;
        this.photo = photo;
    }

    public Annonce(Integer id, String nomAnnonce, Timestamp dateDebut, Timestamp dateFin, String adresse, double prix, int nbPlace, String type, Timestamp heure) {
        this.id = id;
        this.nomAnnonce = nomAnnonce;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.prix = prix;
        this.nbPlace = nbPlace;
        this.type = type;
        this.heure = heure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getHeure() {
        return heure;
    }

    public void setHeure(Timestamp heure) {
        this.heure = heure;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNbrrating() {
        return nbrrating;
    }

    public void setNbrrating(Integer nbrrating) {
        this.nbrrating = nbrrating;
    }

    public Integer getNbruser() {
        return nbruser;
    }

    public void setNbruser(Integer nbruser) {
        this.nbruser = nbruser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecosmart.entities.Evenement[ id=" + id + " ]";
    }

}
