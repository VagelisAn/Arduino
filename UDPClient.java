/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

import java.io.*;
import java.net.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
       try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Panel().setVisible(true);
            }
        });
       
       
   }
}
