/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author lorda
 */
public class Center extends JPanel{
    
    Center()
    {
        JPanel tabla = new JPanel();
        tabla.setPreferredSize(new Dimension(700,620));
        //tabla.setBorder(LineBorder.createBlackLineBorder());
        
        konyvleiras leir = new konyvleiras();
        add(tabla);
    
    }
    
    
    
    private class konyvleiras extends JPanel{
    
    konyvleiras(){
        add(new JLabel("konyvleiras"));
    }
    
    }
    
    private class kosar extends JPanel{}
}
