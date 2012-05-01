
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

import ultimate.sql.*;
import ultimate.gui.*;
import java.io.*;

/**
 *
 * @author lorda
 */


public class KonyvesboltUltimate {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
      BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      DAO con = new DAO();
      // MainWindow ablak = new MainWindow();
      
      int menu = 0;
      String param[];
              
      System.out.println("Mit szeretnel tesztelni?\n" + 
              "1 - Hozzaszolas\n2 - Konyv\n3 - Felhaznalo");
      menu = Integer.parseInt(br.readLine());
      
      // ---- konzolos "tesztkornyezet"
      
      switch(menu){
      
          case 1:
            
            //A hozzaszolasnal meg nincs megvalositva, hogy melyik 
            //konyvhoz tartozik.
              
            param = new String[1];
            
            System.out.println("Hozzaszolas:");
            param[0] = br.readLine();
            
            con.testHozzaszolas(param[0]);
              
            break;  
              
          case 2:
            param = new String[5];
            
            System.out.println("cim:");  
            param[0] = br.readLine();
            System.out.println("Ar:");
            param[1] = br.readLine();
            System.out.println("db:");
            param[2] = br.readLine();
            System.out.println("Mufaj:");
            param[3] = br.readLine();
            System.out.println("Szerzo:");
            param[4] = br.readLine();
            
            con.testKonyv(param[0], Integer.parseInt(param[1]),
                    Integer.parseInt(param[2]),param[3],param[4]);
            break;
              
          case 3:
            param = new String[9];
              
            System.out.println("f_nev:");
            param[0] = br.readLine();
            System.out.println("pass:");
            param[1] = br.readLine();
            System.out.println("email:");
            param[2] = br.readLine();
            System.out.println("teljesnev:");
            param[3] = br.readLine();
            System.out.println("jog:");
            param[4] = br.readLine();
            System.out.println("irszam:");
            param[5] = br.readLine();
            System.out.println("varos:");
            param[6] = br.readLine();
            System.out.println("utca:");
            param[7] = br.readLine();
            System.out.println("hazszam:");
            param[8] = br.readLine();
            
            con.testFelhasznalo(param[0], param[1], param[2],
                    param[3], Integer.parseInt(param[4]), 
                    Integer.parseInt(param[5]),param[6],
                    param[7], Integer.parseInt(param[8]));
              break;
              
           default: 
                System.out.println("Rossz menu");
                break;
         }

      }
        
}

