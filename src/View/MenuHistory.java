/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataManager;
import View.MainMenuUser;
import Controller.Interface;
import Model.DataTransaksi;
import Model.DataCustomer;
import Model.DetailTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class MenuHistory {
    JFrame frame;
    JLabel[] labelNo, labelNama, labelJumlah, labelHarga, labelTotal, labelPembelian, labelTotalHarga;
    JLabel[][] labelId, labelNamaBarang, labelJumlahPembelian, labelHargaBarang;
    JButton buttonBack;
    
    DataCustomer dataUser = DataManager.getInstance().getData().getDataUser();
    public MenuHistory(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        DataTransaksi transaksiHead = DataManager.getInstance().getData().getTransaksiHead();
        DataTransaksi temp = transaksiHead;
        DetailTransaksi tempDetail = new DetailTransaksi();
        int banyakTransaksi = 0;
        
        while(temp != null){
            if(temp.getIdUser() == dataUser.getId()){
                banyakTransaksi++;
            }
            temp = temp.getNext();
        }
        int[] banyakBarang = new int[banyakTransaksi];
        for(int i = 0; i < banyakBarang.length; i++){
            banyakBarang[i] = 0;
        }
        int counter = 0;
        temp = transaksiHead;
        while(temp != null){
            if(temp.getIdUser() == dataUser.getId()){
                tempDetail = temp.getDetail();
                while(tempDetail != null){
                    banyakBarang[counter] = banyakBarang[counter] + 1;
                    tempDetail = tempDetail.getNext();
                }
                counter++;
            }
            temp = temp.getNext();
        }
        labelNo = new JLabel[banyakTransaksi];
        labelNama = new JLabel[banyakTransaksi];
        labelJumlah = new JLabel[banyakTransaksi];
        labelHarga = new JLabel[banyakTransaksi];
        labelPembelian = new JLabel[banyakTransaksi];
        labelTotal = new JLabel[banyakTransaksi];
        
        for(int i = 0; i < banyakTransaksi; i++){
            labelId = new JLabel[banyakTransaksi][banyakBarang[i]+1];
            labelNamaBarang = new JLabel[banyakTransaksi][banyakBarang[i]+1];
            labelJumlahPembelian = new JLabel[banyakTransaksi][banyakBarang[i]+1];
            labelHargaBarang = new JLabel[banyakTransaksi][banyakBarang[i]+1];
        }
        
        labelTotalHarga = new JLabel[banyakTransaksi];
        
        temp = transaksiHead;
        int y = 20, counter1 = 0;
        
        while(temp != null){
            if(temp.getIdUser() == dataUser.getId()){
                labelPembelian[counter1] = new JLabel("Pembelian ke-" + Integer.toString(counter1+1));
                labelNo[counter1] = new JLabel("Nomor");
                labelNama[counter1] = new JLabel("Nama Barang");
                labelJumlah[counter1] = new JLabel("Jumlah Pembelian");
                labelHarga[counter1] = new JLabel("Harga");
            
                labelPembelian[counter1].setBounds(50, y, 100, 30);
                y = y + 25;
                labelNo[counter1].setBounds(50, y, 100, 30);
                labelNama[counter1].setBounds(100, y, 200, 30);
                labelJumlah[counter1].setBounds(250, y, 200, 30);
                labelHarga[counter1].setBounds(400, y, 100, 30);
                y = y + 25;
            
                frame.add(labelPembelian[counter1]);
                frame.add(labelNo[counter1]);
                frame.add(labelNama[counter1]);
                frame.add(labelJumlah[counter1]);
                frame.add(labelHarga[counter1]);
                
                tempDetail = temp.getDetail();
                int counter2 = 0;
                while(tempDetail != null){
                    labelId[counter1][counter2] = new JLabel(Integer.toString(counter2+1));
                    labelNamaBarang[counter1][counter2] = new JLabel(tempDetail.getNamaBarang());
                    labelJumlahPembelian[counter1][counter2] = new JLabel(Integer.toString(tempDetail.getJumlahPembelian()));
                    labelHargaBarang[counter1][counter2] = new JLabel("Rp. " + Integer.toString(tempDetail.getTotalHarga()));
                
                    labelId[counter1][counter2].setBounds(50, y, 100, 30);
                    labelNamaBarang[counter1][counter2].setBounds(100, y, 200, 30);
                    labelJumlahPembelian[counter1][counter2].setBounds(250, y, 100, 30);
                    labelHargaBarang[counter1][counter2].setBounds(400, y, 100, 30);
                    y = y + 25;
                
                    frame.add(labelId[counter1][counter2]);
                    frame.add(labelNamaBarang[counter1][counter2]);
                    frame.add(labelJumlahPembelian[counter1][counter2]);
                    frame.add(labelHargaBarang[counter1][counter2]);
                    tempDetail = tempDetail.getNext();
                    counter2++;
                }
                labelTotal[counter1] = new JLabel("Total Harga: ");
                labelTotalHarga[counter1] = new JLabel("Rp. " + Integer.toString(temp.getTotalPembayaran()));
            
                labelTotal[counter1].setBounds(250, y, 100, 30);
                labelTotalHarga[counter1].setBounds(400, y, 100, 30);
                y = y + 25;
            
                frame.add(labelTotal[counter1]);
                frame.add(labelTotalHarga[counter1]);
                
                counter1++;
                
            }
            temp = temp.getNext();
        }
        
        buttonBack = new JButton("Back");
        buttonBack.setBounds(20, 300, 100, 30);
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuUser();
            }
        });
        
        frame.add(buttonBack);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
