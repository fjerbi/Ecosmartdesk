/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.interfaces;

import com.ecosmart.entities.Annonce;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public interface IAnnonceServices {

    public void create(Annonce e);

   // public List<Evenement> findAll();
    public List<Annonce> afficherev();

    public void update(Annonce e);
   public ObservableList<Annonce> afficherAnnonce();
    public ObservableList<Annonce> afficherLocal2();
  
        public Annonce afficherLocalUn(int id);
    public void delete(int id);
    public Annonce FindAnnonce(int id);
   
    //public Double MoyRat(int id);
    public boolean EventHasNote(Annonce ev);
}

