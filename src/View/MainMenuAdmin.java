/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class MainMenuAdmin {
    JFrame frame;
    JButton buttonDeleteUser, buttonTambahStock, buttonLogout;
    
    public MainMenuAdmin(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        buttonDeleteUser = new JButton("Delete User");
        buttonTambahStock = new JButton("Tambah Stock");
        buttonLogout = new JButton("Logout");
        buttonDeleteUser.setBounds(200, 70, 150, 30);
        buttonTambahStock.setBounds(200, 110, 150, 30);
        buttonLogout.setBounds(400, 20, 150, 30);
        
        buttonDeleteUser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuDeleteUser();
            }
        });
        buttonTambahStock.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuPilihGenreAdmin();
            }
        });
        buttonLogout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int jawab = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?");
                switch(jawab){
                    case JOptionPane.YES_OPTION:
                        frame.setVisible(false);
                        new MainMenuScreen();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });
        frame.add(buttonDeleteUser);
        frame.add(buttonTambahStock);
        frame.add(buttonLogout);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
