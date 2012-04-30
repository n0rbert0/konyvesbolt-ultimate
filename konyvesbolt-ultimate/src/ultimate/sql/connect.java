/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.sql;

import java.sql.*;
import java.util.StringTokenizer;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import ultimate.konyvesbolt.Konyv;

/**
 *
 * @author lorda
 */
public class connect {
    
    public connect(){
    
    Connection conn = null;
     System.out.println("SQL Test");
     try {
     DriverManager.registerDriver
     (new oracle.jdbc.driver.OracleDriver());
     }
     catch (Exception e) {
     System.out.println(e);
     System.exit(0);
     }

    try {
     conn = java.sql.DriverManager.getConnection(
"jdbc:oracle:thin:SYSTEM/root@localhost:1521:xe");
     }
     catch (Exception e) {
     System.out.println(e);
    System.exit(0);
     }

     System.out.println("Connection established");

    /* try {Statement s = conn.createStatement();
    java.sql.ResultSet r = s.executeQuery
     ("Select * from HELP");
     while(r.next()) {
           System.out.println (
           r.getString("TOPIC") );

           StringTokenizer Tok =
           new StringTokenizer(r.getString("TOPIC"),"-");
           int n=0;
           while (Tok.hasMoreElements())
               System.out.println("" + ++n + ": "+Tok.nextElement());
           }
     }
    catch (Exception e) {
           System.out.println(e);
           System.exit(0);
   }
    */
    
    }
    
    //-------------
    
    public String Proba(){
        Connection conn = null;
     System.out.println("SQL Test");
     try {
     DriverManager.registerDriver
     (new oracle.jdbc.driver.OracleDriver());
     }
     catch (Exception e) {
     System.out.println(e);
     System.exit(0);
     }

    try {
     conn = java.sql.DriverManager.getConnection(
"jdbc:oracle:thin:SYSTEM/root@localhost:1521:xe");
     }
     catch (Exception e) {
     System.out.println(e);
    System.exit(0);
     }
        Konyv k = new Konyv();
		//Adatbázis kapcsolatot reprezentáló objektum
		//Connection conn = null;
                Statement st = null;

                
		try {
			//Az adatbázis kapcsolatunkat a DriverManager segítségével hozzuk létre
			//Megadjuk hogy a jdbc milyen driveren keresztul milyen fájlt keressen
			conn = DriverManager.getConnection("jdbc:oracle:thin:root/root@localhost:1521:xe");
			st = conn.createStatement();
			//Uj Customer felvetele eseten egy PreparedStatement objektumot kerunk a kapcsolat objektumtol
			//Ez egy paramáterezhető sql utasitást vár, a paraméterek ?-kent jelennek meg (lsd. SQL_addCustomer attr.)
			ResultSet rs = st.executeQuery("select * from hozzaszolas");
			//Az egyes parametereket sorban kell megadni, pozicio alapjan, ami 1tol indul
			//Celszeru egy indexet inkrementalni, mivel ha az egyik parameter kiesik az elejerol, akkor nem kell az utana kovetkezoeket ujra szamozni... 
			
			//az ExecuteUpdate paranccsal vegrehajtjuk az utasitast
			//Ezzel azt is megmondjuk, hogy az utasitas modositja az adatbazist
			while(rs.next()){
                        
                        
                        k.setCim(rs.getString("tartalom"));
                        
                        }
                        
                        
		} catch (SQLException e) {
		} finally {
			//NAGYON FONTOS, hogy minden adatbazis objektumot finallyban le kell zarni, mivel ha ezt nem tesszuk meg akkor elofordulhat
			//hogy nyitott kapcsolatok maradnak az adatbazis fele. Az adatbazis pedig korlatozott szamban tart fennt kapcsolatokat, ezert
			//egy ido utan akar ez be is telhet!
			//Minden egyes objektumot kulon try catch agban kell megprobalni bezarni!
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return k.getCim();
	}
    
   //--------------ű
    
    

    
    
    
    
    
    
    
}//class


