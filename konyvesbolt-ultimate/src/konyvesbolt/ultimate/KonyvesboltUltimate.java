/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package konyvesbolt.ultimate;

/**
 *
 * @author lorda
 */
import java.sql.*;
import java.util.StringTokenizer;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class KonyvesboltUltimate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
"jdbc:oracle:thin:root/root@localhost:1521:xe");
     }
     catch (Exception e) {
     System.out.println(e);
    System.exit(0);
     }

     System.out.println("Connection established");

     try {Statement s = conn.createStatement();
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
               
    }
}
