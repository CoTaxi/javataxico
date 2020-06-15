/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ASUS
 */
    public final class UserSession {

    private static UserSession instance;

    private int id_user;
    private String mail;

    private UserSession(int id_user, String mail) {
        this.id_user = id_user;
        this.mail = mail;
    }

    public static UserSession getInstace(int id_user, String mail) {
        if(instance == null) {
            instance = new UserSession(id_user, mail);
        }
        return instance;
    }

    public int getId_user() {
        return id_user;
    }

    public String getMail() {
        return mail;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

   

    public void cleanUserSession() {
        id_user = 0;// or null
        mail = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id_user='" + id_user + '\'' +
                ", mail=" + mail +
                '}';
    }
}
