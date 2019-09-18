/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecosmart.interfaces;

import com.ecosmart.entities.FosUser;
import com.ecosmart.entities.Profile;
import java.util.List;

/**
 *
 * @author ramyk
 */
public interface IFosUserServices {


    public void create(FosUser u);

    public List<FosUser> findAll();
    
    public Profile findProfileById(Integer idUser);

    public void update(FosUser u);

    public void delete(FosUser u);

    public boolean  Authentification(FosUser u);
    public FosUser  getUserbyId(int id);
    public String CheckRole(FosUser u );
    public FosUser getUserByEmail(String email);
    public void SendMailAndAddTokenToUser(FosUser u);
    public Boolean CheckToken(FosUser u,String token);
    public Boolean CheckIfUserExist(String email);
    public void ressettingpassword(FosUser u );

    public Boolean Checkconfirmationtoken(String email,String Token );
    public void ConfirmAccount(FosUser u);
    public boolean checkEnabled(String username);
    public Boolean CheckIfUsernameExist(String username);
}
