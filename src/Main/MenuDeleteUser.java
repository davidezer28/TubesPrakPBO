/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

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
    DummyData.dataUser userHead = DummyDataManager.getInstance().getDummyData().getUserHead();
    
    public MenuDeleteUser(DummyData.dataAdmin head){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        
        
        DummyData.dataUser temp = userHead;
        int banyakUser = 0;
        while(temp != null){
            banyakUser++;
            temp = temp.next;
        }
        labelNo = new JLabel("No");
        labelNama = new JLabel("Nama");
        labelUsername = new JLabel("Username");
        labelEmail = new JLabel("Email");
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
            labelIsiNo[i] = new JLabel(Integer.toString(temp.id));
            labelIsiNama[i] = new JLabel(temp.nama);
            labelIsiUsername[i] = new JLabel(temp.username);
            labelIsiEmail[i] = new JLabel(temp.email);
            buttonDelete[i] = new JButton("Delete User " + Integer.toString(temp.id));
            
            labelIsiNo[i].setBounds(25, y, 100, 30);
            labelIsiNama[i].setBounds(75, y, 200, 30);
            labelIsiUsername[i].setBounds(175, y, 200, 30);
            labelIsiEmail[i].setBounds(275, y, 200, 30);
            buttonDelete[i].setBounds(425, y+5, 125, 20);
            y = y + 25;
            
            buttonDelete[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int key = Integer.parseInt(e.getActionCommand().substring(12));
                    DummyData.dataUser tempUser = userHead;
                    DummyData.dataUser prev = DummyDataManager.getInstance().getDummyData().buatDataUser();
                    if(tempUser != null && tempUser.id == key){
                        System.out.println("masuk");
                        userHead = tempUser.next;
                    }else{
                        while(tempUser != null && tempUser.id != key){
                            prev = tempUser;
                            tempUser = tempUser.next;
                        }
                        if(tempUser == null){
                            return;
                        }
                        prev.next = tempUser.next;
                    }
                    DummyDataManager.getInstance().getDummyData().setUserHead(userHead);
                    JOptionPane.showMessageDialog(null, "Deleted!!");
                    frame.setVisible(false);
                    new MainMenuAdmin(head);
                }
            });
            
            frame.add(labelIsiNo[i]);
            frame.add(labelIsiNama[i]);
            frame.add(labelIsiUsername[i]);
            frame.add(labelIsiEmail[i]);
            frame.add(buttonDelete[i]);
            
            temp = temp.next;
        }
        
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuAdmin(head);
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
