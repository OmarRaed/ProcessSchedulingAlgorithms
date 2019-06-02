package com.psa.client;

import com.psa.algorithms.RoundRobin;
import com.psa.model.Process;
import com.psa.util.ExcelRead;

import com.psa.util.ExcelWrite;

import com.psa.view.MainFrame;

import java.awt.Dimension;

import java.awt.Toolkit;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.List;
import java.util.Queue;

import javax.swing.UIManager;

public class MainClass {
    public static void main(String[] args) {
        
        //set application look and feel
        setSmartLookAndFeel();
        
        //start the main frame
        MainFrame mainFrame = new MainFrame() ;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
        mainFrame.setVisible(true);

    }
    
    private static void setTextureLookAndFeel(){

        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private static void setNoireLookAndFeel(){

        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private static void setSmartLookAndFeel(){

        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
