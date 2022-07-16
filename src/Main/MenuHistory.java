/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.DummyData.*;
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
    public MenuHistory(dataUser head){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        dataTransaksi transaksiHead = DummyDataManager.getInstance().getDummyData().getTransaksiHead();
        dataTransaksi temp = transaksiHead;
        detailTransaksi tempDetail = DummyDataManager.getInstance().getDummyData().buatDetailTransaksi();
        int banyakTransaksi = 0;
        
        while(temp != null){
            if(temp.idUser == head.id){
                banyakTransaksi++;
            }
            temp = temp.next;
        }
        int[] banyakBarang = new int[banyakTransaksi];
        for(int i = 0; i < banyakBarang.length; i++){
            banyakBarang[i] = 0;
        }
        int counter = 0;
        temp = transaksiHead;
        while(temp != null){
            if(temp.idUser == head.id){
                tempDetail = temp.detail;
                while(tempDetail != null){
                    banyakBarang[counter] = banyakBarang[counter] + 1;
                    tempDetail = tempDetail.next;
                }
                counter++;
            }
            temp = temp.next;
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
            if(temp.idUser == head.id){
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
                
                tempDetail = temp.detail;
                for(int j = 0; j < banyakBarang[counter1]; j++){
                    labelId[counter1][j] = new JLabel(Integer.toString(tempDetail.idBarang));
                    labelNamaBarang[counter1][j] = new JLabel(tempDetail.namaBarang);
                    labelJumlahPembelian[counter1][j] = new JLabel(Integer.toString(tempDetail.jumlahPembelian));
                    labelHargaBarang[counter1][j] = new JLabel("Rp. " + Integer.toString(tempDetail.totalHarga));
                
                    labelId[counter1][j].setBounds(50, y, 100, 30);
                    labelNamaBarang[counter1][j].setBounds(100, y, 200, 30);
                    labelJumlahPembelian[counter1][j].setBounds(250, y, 100, 30);
                    labelHargaBarang[counter1][j].setBounds(400, y, 100, 30);
                    y = y + 25;
                
                    frame.add(labelId[counter1][j]);
                    frame.add(labelNamaBarang[counter1][j]);
                    frame.add(labelJumlahPembelian[counter1][j]);
                    frame.add(labelHargaBarang[counter1][j]);
                    tempDetail = tempDetail.next;
                }
                labelTotal[counter1] = new JLabel("Total Harga: ");
                labelTotalHarga[counter1] = new JLabel("Rp. " + Integer.toString(temp.totalPembayaran));
            
                labelTotal[counter1].setBounds(250, y, 100, 30);
                labelTotalHarga[counter1].setBounds(400, y, 100, 30);
                y = y + 25;
            
                frame.add(labelTotal[counter1]);
                frame.add(labelTotalHarga[counter1]);
                
                counter1++;
                
            }
            temp = temp.next;
        }
        
        buttonBack = new JButton("Back");
        buttonBack.setBounds(20, 300, 100, 30);
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MainMenuUser(head);
            }
        });
        
        frame.add(buttonBack);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
