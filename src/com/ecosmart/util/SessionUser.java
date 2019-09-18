/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.util;

import com.ecosmart.entities.FosUser;
import com.ecosmart.entities.Profile;
import com.ecosmart.services.FosUserServices;

/**
 *
 * @author firas
 */
public class SessionUser {
    
    private static final FosUserServices fs = new FosUserServices();
    
    private static SessionUser instance = null;
    private static FosUser user = null;
    private static Profile profile = null;
    
    private SessionUser(FosUser userConnected) {
        super();
        SessionUser.user = userConnected;
        SessionUser.profile = fs.findProfileById(userConnected.getId());
        
    }
    
    private SessionUser(FosUser userConnected, Boolean b) {
        super();
        SessionUser.user = userConnected;
    }
    
    public final static SessionUser getInstance(FosUser userConnected) {

        if (SessionUser.instance == null) {
            SessionUser.instance = new SessionUser(userConnected);
        }
        return SessionUser.instance;
    }
    
    public final static SessionUser getFirstInstance(FosUser userConnected) {

        if (SessionUser.instance == null) {

            SessionUser.instance = new SessionUser(userConnected,false);
      
        }
        return SessionUser.instance;
    }

    public static FosUserServices getFs() {
        return fs;
    }

    public static SessionUser getInstance() {
        return instance;
    }

    public static FosUser getUser() {
        return user;
    }

    public static Profile getProfile() {
        return profile;
    }

    public static void setUser(FosUser user) {
        SessionUser.user = user;
    }

    public static void setProfile(Profile profile) {
        SessionUser.profile = profile;
    }
    
    

}
