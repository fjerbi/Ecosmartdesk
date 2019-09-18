/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String tagline;
    private String image;


    private Timestamp updatedAt;

    private String aboutMe;
 
    private Integer idUser;

    public Profile() {
    }
    
    public Profile(Integer idUser) {
        this.idUser = idUser;
    }
    
    public Profile(Integer id, String tagline, Timestamp updatedAt, String aboutMe) {
        this.id = id;
        this.tagline = tagline;
        this.updatedAt = updatedAt;
        this.aboutMe = aboutMe;
    }
    
    public Profile(Integer id, String tagline, String image, Timestamp updatedAt, String aboutMe, Integer idUser){
        this.id = id;
        this.idUser = idUser;
        this.tagline = tagline;
        this.image = image;
        this.updatedAt = updatedAt;
        this.aboutMe = aboutMe;
    }
    
    //Constructor for creation on add:
    public Profile(String tagline, String aboutMe, Integer idUser){
        this.idUser = idUser;
        this.tagline = tagline;
        this.aboutMe = aboutMe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecosmart.entities.Profile[ id=" + id + " ]";
    }
    
}
