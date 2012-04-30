/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

import ultimate.sql.*;
import ultimate.gui.*;

/**
 *
 * @author lorda
 */


public class KonyvesboltUltimate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // MainWindow ablak = new MainWindow();
        connect con = new connect();
       con.beszuras();
       con.listazas();
     
    }
        
}

