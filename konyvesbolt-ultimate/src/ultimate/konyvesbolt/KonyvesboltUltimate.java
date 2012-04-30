<<<<<<< HEAD
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

=======
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
        MainWindow ablak = new MainWindow();
        connect con = new connect();
       System.out.println(con.Proba());
       con.Proba();
    }
        
}

>>>>>>> 0272e6410d702be03c5f367f9e3326ed91bc79cb
