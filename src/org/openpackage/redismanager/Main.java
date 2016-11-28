/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import org.openpackage.redismanager.ui.MainFrame;

/**
 *
 * @author micfans
 */
public class Main {

    public static JFrame frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frame = new MainFrame();
            URL url = Main.class.getResource("/org/openpackage/redismanager/ui/assets/filing_cabinet.png");
            Image img = Toolkit.getDefaultToolkit().getImage(url);
            frame.setIconImage(img);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void exit() {
        System.exit(0);
    }
}
