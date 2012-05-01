/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

import java.util.Date;

/**
 *
 * @author Peták Norbert
 */
public class Hozzaszolas {
    
    private int h_id;
    private String tartalom;
    private Konyv konyv;
    private Felhasznalo felhasznalo;
    private String date;

    public String getDate() {
        return date;
    }

    public int getH_id() {
        return h_id;
    }

    public String getTartalom() {
        return tartalom;
    }

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public Konyv getKonyv() {
        return konyv;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public void setKonyv(Konyv konyv) {
        this.konyv = konyv;
    }

    @Override
    public String toString() {
        return " {H_ID=" + h_id + ", TARTALOM=" + tartalom + ", IDO=" + date + "}";
    }

}
