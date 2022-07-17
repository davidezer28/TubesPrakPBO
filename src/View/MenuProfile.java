/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataManager;
import View.MainMenuUser;
import Controller.Interface;
import Model.DataCustomer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */

public class MenuProfile {
    JFrame frame;
    JLabel labelNama, labelUsername, labelEmail, labelPassword, labelPassword2;
    JTextField tfNama, tfUsername, tfEmail;
    JPasswordField tfPassword, tfPassword2;
    JButton buttonUbah, buttonBatal;
    DataCustomer dataUser = DataManager.getInstance().getData().getDataUser();
    
    public MenuProfile(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        labelNama = new JLabel("Nama");
        labelUsername = new JLabel("Username");
        labelEmail = new JLabel("No Telp");
        labelPassword = new JLabel("Password");
        labelPassword2 = new JLabel("Retype Password");
        
        labelNama.setBounds(80, 30, 200, 30);
        labelUsername.setBounds(80, 80, 200, 30);
        labelEmail.setBounds(80, 130, 200, 30);
        labelPassword.setBounds(80, 180, 200, 30);
        labelPassword2.setBounds(80, 230, 200, 30);
        
        tfNama = new JTextField(dataUser.getNama());
        tfUsername = new JTextField(dataUser.getUsername());
        tfEmail = new JTextField(dataUser.getNoTelp());
        tfPassword = new JPasswordField(dataUser.getPassword());
        tfPassword2 = new JPasswordField(dataUser.getPassword());
        
        tfNama.setBounds(300, 30, 200, 30);
        tfUsername.setBounds(300, 80, 200, 30);
        tfEmail.setBounds(300, 130, 200, 30);
        tfPassword.setBounds(300, 180, 200, 30);
        tfPassword2.setBounds(300, 230, 200, 30);
        
        buttonUbah = new JButton("Ubah");
        buttonBatal = new JButton("Batal");
        
        buttonUbah.setBounds(200, 275, 100, 30);
        buttonBatal.setBounds(325, 275, 100, 30);
        
        buttonUbah.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int jawab = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah?");
                
                switch(jawab){
                    case JOptionPane.YES_OPTION:
                        if(tfPassword.getText().equals(tfPassword2.getText())){
                            dataUser.setNama(tfNama.getText());
                            dataUser.setUsername(tfUsername.getText());
                            dataUser.setNoTelp(tfEmail.getText());
                            dataUser.setPassword(tfPassword.getText());
                            
                            DataManager.getInstance().getData().setDataUser(dataUser);
                            JOptionPane.showMessageDialog(null, "Profile sukses diganti!!");
                            frame.setVisible(false);
                            new MainMenuUser();
                        }else{
                            JOptionPane.showMessageDialog(null, "Password tidak sama!!");
                        }
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });
        buttonBatal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuUser();
            }
        });
        
        frame.add(labelNama);
        frame.add(labelUsername);
        frame.add(labelEmail);
        frame.add(labelPassword);
        frame.add(labelPassword2);
        frame.add(tfNama);
        frame.add(tfUsername);
        frame.add(tfEmail);
        frame.add(tfPassword);
        frame.add(tfPassword2);
        frame.add(buttonUbah);
        frame.add(buttonBatal);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
