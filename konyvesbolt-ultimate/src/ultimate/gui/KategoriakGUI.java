/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
/**
 *
 * @author lorda
 */
public class KategoriakGUI extends JPanel{
    
    KategoriakGUI(){
    JPanel fal = new JPanel();
    fal.setLayout(new BoxLayout(fal,BoxLayout.PAGE_AXIS));
    fal.setBorder(LineBorder.createBlackLineBorder());
    fal.setPreferredSize(new Dimension(156,615));
    fal.add(new JLabel("Kategóriák:\n"));
    
    for(int i=0; i<18; i++){
        fal.add(new JLabel("                      \n"));
        fal.add(new HyperlinkLabel("\n-----------------porn\n\n"));
    }
    
    add(fal);
    }
    
}
