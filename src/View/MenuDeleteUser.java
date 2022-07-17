/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.DataManager;
import Controller.Interface;
import Model.DataCustomer;
import View.MainMenuAdmin;
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
public class MenuDeleteUser {
    JFrame frame;
    JLabel labelNo, labelNama, labelEmail, labelUsername;
    JLabel[] labelIsiNo, labelIsiNama, labelIsiEmail, labelIsiUsername;
    JButton buttonDeleteUser, buttonTambahStock, buttonBack;
    JButton[] buttonDelete;
    DataCustomer userHead = DataManager.getInstance().getData().getUserHead();
    
    public MenuDeleteUser(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        DataCustomer temp = userHead;
        int banyakUser = 0;
        while(temp != null){
            banyakUser++;
            temp = temp.getNext();
        }
        labelNo = new JLabel("No");
        labelNama = new JLabel("Nama");
        labelUsername = new JLabel("Username");
        labelEmail = new JLabel("No Telp");
        buttonBack = new JButton("Back");
        
        labelNo.setBounds(25, 20, 100, 30);
        labelNama.setBounds(75, 20, 200, 30);
        labelUsername.setBounds(175, 20, 200, 30);
        labelEmail.setBounds(275, 20, 200, 30);
        buttonBack.setBounds(400, 300, 150, 30);
        
        labelIsiNo = new JLabel[banyakUser];
        labelIsiNama = new JLabel[banyakUser];
        labelIsiUsername = new JLabel[banyakUser];
        labelIsiEmail = new JLabel[banyakUser];
        buttonDelete = new JButton[banyakUser];
        
        temp = userHead;
        int y = 45;
        for(int i = 0; i < banyakUser; i++){
            labelIsiNo[i] = new JLabel(Integer.toString(temp.getId()));
            labelIsiNama[i] = new JLabel(temp.getNama());
            labelIsiUsername[i] = new JLabel(temp.getUsername());
            labelIsiEmail[i] = new JLabel(temp.getNoTelp());
            buttonDelete[i] = new JButton("Delete User " + Integer.toString(temp.getId()));
            
            labelIsiNo[i].setBounds(25, y, 100, 30);
            labelIsiNama[i].setBounds(75, y, 200, 30);
            labelIsiUsername[i].setBounds(175, y, 200, 30);
            labelIsiEmail[i].setBounds(275, y, 200, 30);
            buttonDelete[i].setBounds(425, y+5, 125, 20);
            y = y + 25;
            
            buttonDelete[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int key = Integer.parseInt(e.getActionCommand().substring(12));
                    DataCustomer tempUser = userHead;
                    DataCustomer prev = DataManager.getInstance().getData().getUserHead().buatDataUser();
                    if(tempUser != null && tempUser.getId() == key){
                        System.out.println("masuk");
                        userHead = tempUser.getNext();
                    }else{
                        while(tempUser != null && tempUser.getId() != key){
                            prev = tempUser;
                            tempUser = tempUser.getNext();
                        }
                        if(tempUser == null){
                            return;
                        }
                        prev.setNext(tempUser.getNext());
                    }
                    DataManager.getInstance().getData().setUserHead(userHead);
                    JOptionPane.showMessageDialog(null, "Deleted!!");
                    frame.setVisible(false);
                    new MainMenuAdmin();
                }
            });
            
            frame.add(labelIsiNo[i]);
            frame.add(labelIsiNama[i]);
            frame.add(labelIsiUsername[i]);
            frame.add(labelIsiEmail[i]);
            frame.add(buttonDelete[i]);
            
            temp = temp.getNext();
        }
        
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuAdmin();
            }
        });
        
        frame.add(labelNo);
        frame.add(labelNama);
        frame.add(labelUsername);
        frame.add(labelEmail);
        frame.add(buttonBack);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
