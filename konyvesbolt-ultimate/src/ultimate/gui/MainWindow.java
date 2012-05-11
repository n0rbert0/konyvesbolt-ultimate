/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 *
 * @author lorda
 */


public class MainWindow extends JFrame{
    
    public static String m_path = "D:\\NetBeansProjects\\konyvesbolt-ultimate\\";
    
    private JFrame window;
    
    public void startGUI() {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
     private void createAndShowGUI() {
    	window = new JFrame("Könyvesbolt Ultimate");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(1200,730);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);  
        init();
    }
     
    private void init() {
          
       TopList toplist = new TopList();
       window.add(toplist, BorderLayout.EAST); 
       Login login = new Login();
       window.add(login, BorderLayout.NORTH);
       KategoriakGUI cat = new KategoriakGUI();
       window.add(cat, BorderLayout.WEST);
    }
    
    public JFrame getWindow() {
		return window;
	}
     
}
