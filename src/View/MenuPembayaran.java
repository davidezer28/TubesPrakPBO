/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataManager;
import View.MainMenuUser;
import Controller.Interface;
import Model.DataBarang;
import Model.DataTransaksi;
import Model.DataCustomer;
import Model.DetailTransaksi;
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
    DetailTransaksi detailHead = DataManager.getInstance().getData().getDetailHead();
    DataCustomer dataUser = DataManager.getInstance().getData().getDataUser();
    public MenuPembayaran(){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        int counter = 0;
        DetailTransaksi temp = detailHead;
        while(temp.getNext() != null){
            counter++;
            temp = temp.getNext();
        }
        counter++;
        DetailTransaksi thisTransaksi = temp;
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
                new MenuPesan(thisTransaksi.getIdGenre());
            }
        });
        buttonBeli.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               DataTransaksi baru = DataManager.getInstance().getData().getTransaksiHead().buatDataTransaksi();
               DataTransaksi head = DataManager.getInstance().getData().getTransaksiHead();
               DataTransaksi temp = head;
               while(temp.getNext() != null){
                   temp = temp.getNext();
               }
               baru.setId(temp.getId() + 1);
               baru.setIdUser(dataUser.getId());
               baru.setDetail(detailHead);
               baru.setTotalPembayaran(total);
               baru.setNext(null);
               DataManager.getInstance().getData().getTransaksiHead().addLastDataTransaksi(head, baru);
               
               DetailTransaksi tempDetail = detailHead;
               ArrayList<DataBarang> tempBarang = DataManager.getInstance().getData().getBarangHead();
               while(tempDetail != null){
                   for(int i = 0; i < tempBarang.size(); i++){
                       if(tempBarang.get(i).getId() == tempDetail.getIdBarang()){
                           tempBarang.get(i).setStock(tempBarang.get(i).getStock() - tempDetail.getJumlahPembelian());
                       }
                   }
                   tempDetail = tempDetail.getNext();
               }
               JOptionPane.showMessageDialog(null, "Terima Kasih!!");
               frame.setVisible(false);
               new MainMenuUser();
           } 
        });
        
        labelNamaBarang = new JLabel[banyakBarang];
        labelJumlahPembelian = new JLabel[banyakBarang];
        labelTotalHarga = new JLabel[banyakBarang];
        
        temp = detailHead;
        int y = 65;
        for(int i = 0; i < banyakBarang; i++){
            labelNamaBarang[i] = new JLabel(temp.getNamaBarang());
            labelJumlahPembelian[i] = new JLabel(Integer.toString(temp.getJumlahPembelian()));
            labelTotalHarga[i] = new JLabel(Integer.toString(temp.getTotalHarga()));
            
            total = total + temp.getTotalHarga();
            
            labelNamaBarang[i].setBounds(50, y, 200, 30);
            labelJumlahPembelian[i].setBounds(150, y, 200, 30);
            labelTotalHarga[i].setBounds(300, y, 200, 30);
            y = y + 25;
            
            frame.add(labelNamaBarang[i]);
            frame.add(labelJumlahPembelian[i]);
            frame.add(labelTotalHarga[i]);
            temp = temp.getNext();
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
