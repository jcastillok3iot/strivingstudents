/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.striving.beans;

import com.striving.controller.OcUsersJpaController;
import com.striving.domain.OcUsers;
import com.striving.dto.LoginDto;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import sun.misc.BASE64Encoder;

/**
 *
 * @author jcastillo
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{
    @PersistenceUnit(unitName="StrivingStudentsPU") //inject from your application server 
    EntityManagerFactory emf; 
    @Resource //inject from your application server 
    UserTransaction  utx; 
    private LoginDto loginDto;
    private OcUsers currentLoggedUser;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    /**
     * @return the loginDto
     */
    public LoginDto getLoginDto() {
        if (loginDto == null){
            loginDto = new LoginDto();
        }
        return loginDto;
    }

    /**
     * @param loginDto the loginDto to set
     */
    public void setLoginDto(LoginDto loginDto) {
        this.loginDto = loginDto;
    }
    
    
    public String actionLogin(){
        OcUsersJpaController usersCtrlr = new OcUsersJpaController(utx, emf);     
        OcUsers aUser = usersCtrlr.findOcUsersBy(getLoginDto().getUserId(), getLoginDto().getPswd());
        if (aUser!=null)
            return "dashboard.xhtml";
        return "error.xhtml";
    }
    
    public String registerUserAction(){      
       OcUsersJpaController usersCtrlr = new OcUsersJpaController(utx, emf);
        //find if the user is already registered
       if (usersCtrlr.userIdExists( this.getLoginDto().getRegEmail()) == 0 ){
           //uid is not taken we keep going
           this.createOcUserFromRegistration();
          
          
           
       }else{
         //add userid already taken message and we return null
           return null;
       }       
        return "";
    }
    
    private void createOcUserFromRegistration(){        
        OcUsers aUser = new OcUsers();
        long aLong = new Timestamp(new Date().getTime()).getTime();
        aUser.setCreated(new Date(aLong));
        aUser.setEmail(getLoginDto().getRegEmail());
        aUser.setPassword(hashPassword(getLoginDto().getRegPassword()));
       // aUser.set
    }
    
    /*
    When users registers we will create a hash password and store it so we will never know the password
    */
    private String hashPassword(String aPassword){
         String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(aPassword.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * @return the currentLoggedUser
     */
    public OcUsers getCurrentLoggedUser() {
        return currentLoggedUser;
    }

    /**
     * @param currentLoggedUser the currentLoggedUser to set
     */
    public void setCurrentLoggedUser(OcUsers currentLoggedUser) {
        this.currentLoggedUser = currentLoggedUser;
    }
    
    
}
