/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.DataManager;
import Controller.Interface;
import Model.DataBarang;
import View.MenuPilihGenreAdmin;
import View.MainMenuAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class MenuTambahStock {
    JFrame frame;
    JLabel labelListBarang, labelNo, labelNama, labelHarga, labelJumlah;
    JLabel[] labelId, labelNamaBarang, labelHargaBarang;
    JTextField[] tfStock;
    JButton buttonBack, buttonNext;
    
    ArrayList<DataBarang> barangHead = DataManager.getInstance().getData().getBarangHead();
    ArrayList<DataBarang> barangGenre = new ArrayList();
   
   
    public MenuTambahStock(int idGenre){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        for(int i = 0; i < barangHead.size(); i++){
            if(barangHead.get(i).getIdGenre() == idGenre){
                barangGenre.add(barangHead.get(i));
            }
        }
        int counter = 0;
        
        labelListBarang = new JLabel("List Barang");
        labelNo = new JLabel("Nomor");
        labelNama = new JLabel("Nama Barang");
        labelHarga = new JLabel("Harga (Rp)");
        labelJumlah = new JLabel("Jumlah Pembelian");
        
        labelListBarang.setBounds(225, 5, 400, 30);
        labelNo.setBounds(50, 30, 200, 30);
        labelNama.setBounds(100, 30, 200, 30);
        labelHarga.setBounds(250, 30, 200, 30);
        labelJumlah.setBounds(400, 30, 200, 30);
        
        buttonBack = new JButton("Back");
        buttonNext = new JButton("Next");
        
        buttonBack.setBounds(20, 300, 100, 30);
        buttonNext.setBounds(450, 300, 100, 30);
        
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuPilihGenreAdmin();
            }
        });
        buttonNext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                boolean cek = false;
                ArrayList<String> barang = new ArrayList();
                int counter = 0;
                
                for(int i = 0; i < barangGenre.size(); i++){
                    if(!tfStock[i].getText().equals("")){
                        cek = true;
                        for(int j = 0; j < barangHead.size(); j++){
                            if(barangHead.get(j).getId() == barangGenre.get(i).getId()){
                                counter++;
                                String str = Integer.toString(counter) + ". " + barangHead.get(j).getNamaBarang() + " Stock Awal: " + Integer.toString(barangHead.get(j).getStock());
                                barangHead.get(j).setStock(barangHead.get(j).getStock() + Integer.parseInt(tfStock[i].getText()));
                                str = str + " Stock Akhir: " + Integer.toString(barangHead.get(j).getStock());
                                barang.add(str);
                            }
                        }
                    }
                }
                if(cek){
                    frame.setVisible(false);
                    String isi = "Stock Berhasil Ditambahkan!!\n";
                    for(int i = 0; i < barang.size(); i++){
                        isi = isi + barang.get(i) + "\n";
                    }
                    JOptionPane.showMessageDialog(null, isi);
                    new MainMenuAdmin();
                }else{
                    JOptionPane.showMessageDialog(null, "Silahkan Pilih Barang yang Ingin Dibeli!!");
                }
            }
        });
        
        labelId = new JLabel[barangGenre.size()];
        labelNamaBarang = new JLabel[barangGenre.size()];
        labelHargaBarang = new JLabel[barangGenre.size()];
        tfStock = new JTextField[barangGenre.size()+1];
        
        int y = 65;
        for(int i = 0; i < barangGenre.size(); i++){
            String[] banyak = new String[barangGenre.get(i).getStock()];
            for(int j = 0; j < barangGenre.get(i).getStock(); j++){
                banyak[j] = Integer.toString(j);
            }
            
            labelId[i] = new JLabel(Integer.toString(i+1) + ".");
            labelNamaBarang[i] = new JLabel(barangGenre.get(i).getNamaBarang());
            labelHargaBarang[i] = new JLabel(Integer.toString(barangGenre.get(i).getHarga()));
            tfStock[i] = new JTextField();
            
            labelId[i].setBounds(50, y, 100, 30);
            labelNamaBarang[i].setBounds(100, y, 200, 30);
            labelHargaBarang[i].setBounds(250, y, 100, 30);
            tfStock[i].setBounds(400, y+5, 100, 20);
            y = y + 25;
            frame.add(labelId[i]);
            frame.add(labelNamaBarang[i]);
            frame.add(labelHargaBarang[i]);
            frame.add(tfStock[i]);
        }
        
        
        frame.add(labelListBarang);
        frame.add(labelNo);
        frame.add(labelNama);
        frame.add(labelHarga);
        frame.add(labelJumlah);
        frame.add(buttonBack);
        frame.add(buttonNext);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
