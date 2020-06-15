/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mahdi
 */
public class fidelite {
    private String points;
    private String code;

    public fidelite(String points, String code) {
        this.points = points;
        this.code = code;
    }

    public String getPoints() {
        return points;
    }

    public String getCode() {
        return code;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public fidelite() {
    }
    
}
