/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;


/**
 * Copyright 2009 - 12 www.java2s.com Demo Source and Support. All rights reserved.
 * http://www.java2s.com/Tutorial/Java/0240__Swing/HyperlinkLabel.htm
 */
public class HyperlinkLabel extends JLabel{
    
  private Color underlineColor = null;

  public HyperlinkLabel(String label) {
    super(label);

    setForeground(Color.BLUE.brighter());
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    addMouseListener(new HyperlinkLabelMouseAdapter());
  }

  public class HyperlinkLabelMouseAdapter extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      System.out.println(getText());
    }
  }

}

	
   

