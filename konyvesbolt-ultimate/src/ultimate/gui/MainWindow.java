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
    
    public static String m_path = "C:\\Users\\lorda\\Documents\\NetBeansProjects\\konyvesbolt-ultimate\\";
    
    public MainWindow() {
        super("Könyvesbolt Ultimate");
        setSize(1150,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
        init();
        setVisible(true);
    }
    
    private void init() {
          
       TopList alma = new TopList();
       add(alma,BorderLayout.CENTER); 
       
    }
}
