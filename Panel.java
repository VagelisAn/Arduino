/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panel extends JFrame implements ActionListener {
        //initialize the panel objects buttons,labels e.g
    JPanel row1 = new JPanel();
    JPanel row2 = new JPanel();
    JPanel row3 = new JPanel();
    JPanel row4 = new JPanel();
    JPanel row5 = new JPanel();
    JPanel row6 = new JPanel();
    JPanel row7 = new JPanel();
    JPanel row8 = new JPanel();
    JPanel row9 = new JPanel();
    JPanel row10 = new JPanel();
    JLabel analog = new JLabel("Analog", JLabel.RIGHT);
    JTextField pedio1 = new JTextField("OFF");
    JTextField pedio2 = new JTextField("OFF");
    JTextField pedio3 = new JTextField("OFF");
    JTextField pedio4 = new JTextField("OFF");
    JTextField pedio5 = new JTextField("OFF");
    JTextField pedio6 = new JTextField("OFF");
    JTextField pedio7 = new JTextField("OFF");
    JTextField pedio8 = new JTextField("OFF");
    JTextField pedio9 = new JTextField("OFF");
    JTextField analog1 = new JTextField(40);
    JButton pin1 = new JButton("Pin1");
    JButton pin2 = new JButton("Pin2");
    JButton pin3 = new JButton("Pin3");
    JButton pin4 = new JButton("Pin4");
    JButton pin5 = new JButton("Pin5");
    JButton pin6 = new JButton("Pin6");
    JButton pin7 = new JButton("Pin7");
    JButton pin8 = new JButton("Pin8");
    JButton pin9 = new JButton("Pin9");
    JButton anal1 = new JButton("Analog1");
    int counter1 = 1, counter2 = 1, counter3=1; // counters to count the click when is ON and OFF the led

    public Panel() { // constractor for panel
        super("UDP Control");
        /// initialize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 250);
        setVisible(true);
        Container pane = getContentPane();
        GridLayout layout = new GridLayout(9, 2);
        pane.setLayout(layout);
        // set the layout and put the buttons , labels e.g
        FlowLayout layout1 = new FlowLayout();
        row1.setLayout(layout1);
        row1.add(pin1);
        row1.add(pedio1);
        pane.add(row1);
        FlowLayout layout2 = new FlowLayout();
        row2.setLayout(layout2);
        row2.add(pin2);
        row2.add(pedio2);
        pane.add(row2);
        FlowLayout layout3 = new FlowLayout();
        row3.setLayout(layout3);
        row3.add(pin3);
        row3.add(pedio3);
        pane.add(row3);
        FlowLayout layout4 = new FlowLayout();
        row4.setLayout(layout4);
        row4.add(pin4);
        row4.add(pedio4);
        pane.add(row4);
        FlowLayout layout5 = new FlowLayout();
        row5.setLayout(layout5);
        row5.add(pin5);
        row5.add(pedio5);
        pane.add(row5);
        FlowLayout layout6 = new FlowLayout();
        row6.setLayout(layout6);
        row6.add(pin6);
        row6.add(pedio6);
        pane.add(row6);
        FlowLayout layout7 = new FlowLayout();
        row7.setLayout(layout7);
        row7.add(pin7);
        row7.add(pedio7);
        pane.add(row7);
        FlowLayout layout8 = new FlowLayout();
        row8.setLayout(layout8);
        row8.add(pin8);
        row8.add(pedio8);
        pane.add(row8);
        FlowLayout layout9 = new FlowLayout();
        row9.setLayout(layout9);
        row9.add(pin9);
        row9.add(pedio9);
        pane.add(row9);
        FlowLayout layout10 = new FlowLayout();
        row10.setLayout(layout10);
        row10.add(analog);
        row10.add(analog1);
        row10.add(anal1);
        pane.add(row10);

        pedio1.setBackground(Color.red); // color the OFF labels with red color
        pedio2.setBackground(Color.red);
        pedio3.setBackground(Color.red);
        pedio4.setBackground(Color.red);
        pedio5.setBackground(Color.red);
        pedio6.setBackground(Color.red);
        pedio7.setBackground(Color.red);
        pedio8.setBackground(Color.red);
        pedio9.setBackground(Color.red);
        pin1.addActionListener(this); // give actions in the pin buttons
        pin2.addActionListener(this);
        pin3.addActionListener(this);
        pin4.addActionListener(this);
        pin5.addActionListener(this);
        pin6.addActionListener(this);
        pin7.addActionListener(this);
        pin8.addActionListener(this);
        pin9.addActionListener(this);
        anal1.addActionListener(this);
        setContentPane(pane);
        pack();
    }

    public void actionPerformed(ActionEvent evt) {

        try {
            DatagramSocket client = new DatagramSocket(); // make the socket for udp
            DatagramPacket sendPacket,receivePacket; // packet one for send and one for receive
            InetAddress IPAddress = InetAddress.getByName("You ip"); // se the ip of the arduino ethernet or wifi
            byte[] sendData = new byte[1024]; 
            byte[] receiveData = new byte[1024];

            if (evt.getSource() == pin1) { // when click pin1 button
                counter1++;
                if (counter1 % 2 == 0) { // if is second click the on the light
                    pedio1.setText("ON"); // change text from OFF to ON
                    pedio1.setBackground(Color.green); // chacge the color from red to green 
                    sendData = "ON1".getBytes(); // make bytes the messege which send in arduino
                    //make the packet set you port of the arduino e.g
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, yourport);
                    // send packet
                    client.send(sendPacket);
                } else {
                    // if is the second click then off the led
                    pedio1.setText("OFF"); // set OFF
                    pedio1.setBackground(Color.red); // change color to red again
                    sendData = "OFF1".getBytes();// make bytes the messege which send in arduino
                    //make the packet set you port of the arduino e.g
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, yourport);
                    // send packet
                    client.send(sendPacket);
                } 
            } ///and other pin have the same logic
            if (evt.getSource() == pin2) {
                counter2++;
                if (counter2 % 2 == 0) {
                    pedio2.setText("ON");
                    pedio2.setBackground(Color.green);
                    sendData = "ON2".getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, your port);
                    client.send(sendPacket);
                } else {
                    pedio2.setText("OFF");
                    pedio2.setBackground(Color.red);
                    sendData = "OFF2".getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, your port);
                    client.send(sendPacket);
                }
            }
             if (evt.getSource() == pin3) {
                counter3++;
                if (counter3 % 2 == 0) {
                    pedio2.setText("ON");
                    pedio3.setBackground(Color.green);
                    sendData = "ON3".getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, your port);
                    client.send(sendPacket);
                } else {
                    pedio3.setText("OFF");
                    pedio3.setBackground(Color.red);
                    sendData = "OFF3".getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, your port);
                    client.send(sendPacket);
                }
            }
            ///// put and other pinss...........
            //analog now
            if (evt.getSource() == anal1) {
                    /// when click on analog button send a message temp to receive after from
                    /// arduino the temperature and the himity
                    sendData = "Temp".getBytes(); // the send packet is same as the pins buttons
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, your port);
                    client.send(sendPacket);
                    
                    // after the send packet the arduino send the packet which have the temp and him
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    client.receive(receivePacket); // receive packet
      
                    String modifiedSentence = new String(receivePacket.getData()); // get the string from the byte packet
                    
                    analog1.setText(modifiedSentence); // set in analog textField the recieve String
               
            }
        } catch (SocketException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
