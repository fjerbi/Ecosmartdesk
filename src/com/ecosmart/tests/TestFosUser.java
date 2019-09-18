/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.tests;

import com.ecosmart.entities.FosUser;
import com.ecosmart.services.FosUserServices;
import java.sql.Date;

/**
 *
 * @author hatem
 */
public class TestFosUser {

            //FosUserServices e = new FosUserServices("Hatem", "Hatem","hatem.abrouz@esprit.tn","hatem.abrouz@esprit.tn",
    //1,"","pass",2018-02-28 00:57:36,"","homme","hatem","ab","20 mars",1074,"ben arous","tunisie",29659174,0,1994-09-15);
/*FosUser fu = new FosUser(Integer.SIZE, "hatem",
     null, null, null, true, null, null, null,
     null, null, null, null, null, null, null,
     Integer.SIZE, null, null, null,
     Integer.SIZE, null);*/
    public static void main(String[] args) {
        FosUserServices es = new FosUserServices();
        
        System.out.println("Avant delete ****************************");
        es.findAll().forEach(System.out::println);
    }

}
