/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author walid
 */
public class Serviceentitie {
    private int id_service;
    private String name ;

    public Serviceentitie(int id_service, String name) {
        this.id_service = id_service;
        this.name = name;
    }

    public Serviceentitie(String name) {
        this.name = name;
    }

    public Serviceentitie(int id_service) {
        this.id_service = id_service;
    }

    public int getId_service() {
        return id_service;
    }

    public String getName() {
        return name;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
