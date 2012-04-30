/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

/**
 *
 * @author Gere
 */
public class Rendeles {
    
    private int R_ID;
    private String Varos, Utca;
    private int Hazszam, IrSzam;
    private int RendeltKonyvCim; //ebből egyértelmű a könyv címe másik tábla alapján
    private int RendeltKönyvDarabSzam;
    private int[] HozzaISBNek; //"rendelt darabszám" hosszú tömb, amiben az ISBN-ek vannak.
    private boolean Kiszallitott = false; //igaz, ha már kiszállításra került

    
    //Getters: 
    public int getHazszam() {
        return Hazszam;
    }

    public int[] getHozzaISBNek() {
        return HozzaISBNek;
    }

    public int getIrSzam() {
        return IrSzam;
    }

    public boolean isKiszallitott() {
        return Kiszallitott;
    }

    public int getR_ID() {
        return R_ID;
    }

    public int getRendeltKonyvCim() {
        return RendeltKonyvCim;
    }

    public int getRendeltKönyvDarabSzam() {
        return RendeltKönyvDarabSzam;
    }

    public String getUtca() {
        return Utca;
    }

    public String getVaros() {
        return Varos;
    }
    
    //Setters: 

    public void setHazszam(int Hazszam) {
        this.Hazszam = Hazszam;
    }

    public void setHozzaISBNek(int[] HozzaISBNek) {
        this.HozzaISBNek = HozzaISBNek;
    }

    public void setIrSzam(int IrSzam) {
        this.IrSzam = IrSzam;
    }

    public void setKiszallitott(boolean Kiszallitott) {
        this.Kiszallitott = Kiszallitott;
    }

    public void setR_ID(int R_ID) {
        this.R_ID = R_ID;
    }

    public void setRendeltKonyvCim(int RendeltKonyvCim) {
        this.RendeltKonyvCim = RendeltKonyvCim;
    }

    public void setRendeltKönyvDarabSzam(int RendeltKönyvDarabSzam) {
        this.RendeltKönyvDarabSzam = RendeltKönyvDarabSzam;
    }

    public void setUtca(String Utca) {
        this.Utca = Utca;
    }

    public void setVaros(String Varos) {
        this.Varos = Varos;
    }
    
    
    
    
    
}//class
