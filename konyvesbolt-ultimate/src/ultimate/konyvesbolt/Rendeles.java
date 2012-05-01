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
    
    private int r_id;
    private int f_id;
    private int isbn;
    private int db;
    private String datum;
    private int ar;
    
    private int irSzam;
    private String varos;
    private String utca;
    private int hazszam;
    
    private boolean kiszalitva = false;

    public int getAr() {
        return ar;
    }

    public String getDatum() {
        return datum;
    }

    public int getDb() {
        return db;
    }

    public int getF_id() {
        return f_id;
    }

    public int getHazszam() {
        return hazszam;
    }

    public int getIrSzam() {
        return irSzam;
    }

    public int getIsbn() {
        return isbn;
    }

    public boolean isKiszalitva() {
        return kiszalitva;
    }

    public int getR_id() {
        return r_id;
    }

    public String getUtca() {
        return utca;
    }

    public String getVaros() {
        return varos;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public void setHazszam(int hazszam) {
        this.hazszam = hazszam;
    }

    public void setIrSzam(int irSzam) {
        this.irSzam = irSzam;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setKiszalitva(boolean kiszalitva) {
        this.kiszalitva = kiszalitva;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    @Override
    public String toString() {
        return " {R_ID=" + r_id + ", F_ID=" + f_id + ", ISBN=" + isbn +
               ", DB=" + db + ", DATUM=" + datum + ", AR=" + ar + ", IRSZAM=" +
               irSzam + ", VAROS=" + varos + ", UTCA=" + utca + ", HAZSZAM="
               + hazszam + ", KISZALITVA=" + kiszalitva + '}';
    }
    
}//class
