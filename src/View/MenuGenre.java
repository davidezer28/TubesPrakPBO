/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.DataManager;
import View.MainMenuUser;
import Controller.Interface;
import Model.DataCustomer;
import Model.GenreBarang;
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
public class MenuGenre {
    JFrame frame;
    JLabel labelWelcome;
    JButton buttonBack;
    JButton[] buttonGenre;
    DataCustomer dataUser = DataManager.getInstance().getData().getDataUser();
    public MenuGenre(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        labelWelcome = new JLabel("Pilih Game");
        labelWelcome.setBounds(225, 30, 400, 30);
        
        ArrayList<GenreBarang> genreHead = DataManager.getInstance().getData().getGenreHead();
        buttonGenre = new JButton[genreHead.size()];
        int x = 200;
        int y = 70;
        for(int i = 0; i < genreHead.size(); i++){
            GenreBarang genrePilihan = genreHead.get(i);
            buttonGenre[i] = new JButton(genreHead.get(i).getNamaGenre());
            buttonGenre[i].setBounds(x, y, 150, 30);
            buttonGenre[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.setVisible(false);
                    new MenuPesan(genrePilihan.getIdGenre());
                }
            });
            frame.add(buttonGenre[i]);
            y += 40;
        }
        
        buttonBack = new JButton("Back");
        buttonBack.setBounds(400, 20, 150, 30);
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuUser();
            }
        });
        frame.add(labelWelcome);
        frame.add(buttonBack);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
