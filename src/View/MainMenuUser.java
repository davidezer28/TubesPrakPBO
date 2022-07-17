/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataManager;
import Controller.DatabaseControl;
import View.MainMenuScreen;
import Controller.Interface;
import Model.DataBarang;
import Model.DataTransaksi;
import Model.DataCustomer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class MainMenuUser {
    JFrame frame;
    JLabel labelWelcome;
    JButton buttonPesan, buttonProfile, buttonTransaksi, buttonLogout;
    DataCustomer dataUser = DataManager.getInstance().getData().getDataUser();
    public MainMenuUser(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        labelWelcome = new JLabel("My Online Shop");
        labelWelcome.setBounds(225, 30, 400, 30);
        
        buttonPesan = new JButton("Beli disini ya!!");
        buttonProfile = new JButton("Liat Profile?");
        buttonTransaksi = new JButton("History Transaksi");
        buttonLogout = new JButton("Logout");
        
        buttonPesan.setBounds(200, 70, 150, 30);
        buttonProfile.setBounds(200, 110, 150, 30);
        buttonTransaksi.setBounds(200, 150, 150, 30);
        buttonLogout.setBounds(400, 20, 150, 30);
        
        buttonPesan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuGenre();
            }
        });
        buttonProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int jawab = JOptionPane.showConfirmDialog(null, ""
                        + "Nama: " + dataUser.getNama() + "\n"
                        + "Username: " + dataUser.getUsername() + "\n"
                        + "No Telp: " + dataUser.getNoTelp() + "\n"
                        + "Ingin di edit?");
                switch(jawab){
                    case JOptionPane.YES_OPTION:
                        frame.setVisible(false);
                        new MenuProfile();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });
        buttonTransaksi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuHistory();
            }
        });
        buttonLogout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int jawab = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?");
                switch(jawab){
                    case JOptionPane.YES_OPTION:
                        DatabaseControl controller = new DatabaseControl();
                        controller.updateUser(DataManager.getInstance().getData().getDataUser());
                        ArrayList<DataBarang> barang = new ArrayList<>();
                        barang = DataManager.getInstance().getData().getBarangHead();
                        for(int i = 0; i < barang.size(); i++){
                            controller.updateBarang(barang.get(i));
                        }
                        DataTransaksi transaksiHead = DataManager.getInstance().getData().getTransaksiHead();
                        DataTransaksi temp = transaksiHead;
                        DataTransaksi transaksiHead2 = controller.getAllTransaksi();
                        DataTransaksi temp2 = transaksiHead2;
                        
                        while(temp2 != null){
                            temp = temp.getNext();
                            temp2 = temp2.getNext();
                        }
                        while(temp != null){
                            controller.insertDataTransaksi(temp);
                        }
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
        frame.add(labelWelcome);
        frame.add(buttonPesan);
        frame.add(buttonProfile);
        frame.add(buttonTransaksi);
        frame.add(buttonLogout);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
