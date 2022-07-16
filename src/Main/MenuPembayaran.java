/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.DummyData.*;
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
public class MenuPembayaran {
    JFrame frame;
    JLabel labelNama, labelJumlah, labelTotal, labelTotalPembelian, labelIsiTotalPembelian;
    JLabel[] labelNamaBarang, labelJumlahPembelian, labelTotalHarga;
    JButton buttonBack, buttonBeli;
    int total = 0;
    detailTransaksi detailTransaksiHead = DummyDataManager.getInstance().getDummyData().getDetailTransaksiHead();
    public MenuPembayaran(dataUser userHead){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        int counter = 0;
        detailTransaksi temp = detailTransaksiHead;
        while(temp.next != null){
            counter++;
            temp = temp.next;
        }
        counter++;
        detailTransaksi thisTransaksi = temp;
        final int banyakBarang = counter;
        
        labelNama = new JLabel("Nama Barang");
        labelJumlah = new JLabel("Jumlah Pembelian");
        labelTotal = new JLabel("Harga");
        
        labelNama.setBounds(50, 30, 200, 30);
        labelJumlah.setBounds(150, 30, 200, 30);
        labelTotal.setBounds(300, 30, 200, 30);
        
        buttonBack = new JButton("Back");
        buttonBeli = new JButton("Beli");
        
        buttonBack.setBounds(20, 300, 100, 30);
        buttonBeli.setBounds(450, 300, 100, 30);
        
        buttonBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                new MenuPesan(userHead, thisTransaksi.idGenre);
            }
        });
        buttonBeli.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               dataTransaksi baru = DummyDataManager.getInstance().getDummyData().buatDataTransaksi();
               dataTransaksi head = DummyDataManager.getInstance().getDummyData().getTransaksiHead();
               dataTransaksi temp = head;
               while(temp.next != null){
                   temp = temp.next;
               }
               baru.id = temp.id + 1;
               baru.idUser = userHead.id;
               baru.detail = detailTransaksiHead;
               baru.totalPembayaran = total;
               baru.next = null;
               DummyDataManager.getInstance().getDummyData().addLastTransaksi(baru);
               
               detailTransaksi tempDetail = detailTransaksiHead;
               ArrayList<dataBarang> tempBarang = DummyDataManager.getInstance().getDummyData().getBarangHead();
               while(tempDetail != null){
                   for(int i = 0; i < tempBarang.size(); i++){
                       if(tempBarang.get(i).id == tempDetail.idBarang){
                           tempBarang.get(i).stock = tempBarang.get(i).stock - tempDetail.jumlahPembelian;
                       }
                   }
                   tempDetail = tempDetail.next;
               }
               JOptionPane.showMessageDialog(null, "Terima Kasih!!");
               frame.setVisible(false);
               new MainMenuUser(userHead);
           } 
        });
        
        labelNamaBarang = new JLabel[banyakBarang];
        labelJumlahPembelian = new JLabel[banyakBarang];
        labelTotalHarga = new JLabel[banyakBarang];
        
        temp = detailTransaksiHead;
        int y = 65;
        for(int i = 0; i < banyakBarang; i++){
            labelNamaBarang[i] = new JLabel(temp.namaBarang);
            labelJumlahPembelian[i] = new JLabel(Integer.toString(temp.jumlahPembelian));
            labelTotalHarga[i] = new JLabel(Integer.toString(temp.totalHarga));
            
            total = total + temp.totalHarga;
            
            labelNamaBarang[i].setBounds(50, y, 200, 30);
            labelJumlahPembelian[i].setBounds(150, y, 200, 30);
            labelTotalHarga[i].setBounds(300, y, 200, 30);
            y = y + 25;
            
            frame.add(labelNamaBarang[i]);
            frame.add(labelJumlahPembelian[i]);
            frame.add(labelTotalHarga[i]);
            temp = temp.next;
        }
        
        labelTotalPembelian = new JLabel("Total Harga");
        labelIsiTotalPembelian = new JLabel("Rp. " + Integer.toString(total));
        
        labelTotalPembelian.setBounds(50, y+25, 200, 30);
        labelIsiTotalPembelian.setBounds(300, y+25, 200, 30);
        
        frame.add(labelNama);
        frame.add(labelJumlah);
        frame.add(labelTotal);
        frame.add(buttonBack);
        frame.add(buttonBeli);
        frame.add(labelTotalPembelian);
        frame.add(labelIsiTotalPembelian);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
