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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class MenuPesan {
    JFrame frame;
    JLabel labelListBarang, labelNo, labelNama, labelHarga, labelJumlah;
    JLabel[] labelId, labelNamaBarang, labelHargaBarang;
    JComboBox[] cbStock;
    JButton buttonBack, buttonNext;
    
    ArrayList<dataBarang> barangHead = DummyDataManager.getInstance().getDummyData().getBarangHead();
    ArrayList<dataBarang> barangGenre = new ArrayList();
   detailTransaksi tempDetail = DummyDataManager.getInstance().getDummyData().getDetailTransaksiHead();
   
   
    public MenuPesan(dataUser head, int idGenre){
        frame = new JFrame(Interface.namaApp);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        DummyDataManager.getInstance().getDummyData().setDetailTransaksiHead(null);
        for(int i = 0; i < barangHead.size(); i++){
            if(barangHead.get(i).idGenre == idGenre){
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
                new MenuGenre(head);
            }
        });
        buttonNext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                boolean cek = false;
                String namaBarang;
                int stock, harga;
                
                for(int i = 0; i < barangGenre.size(); i++){
                    if(!cbStock[i].getItemAt(cbStock[i].getSelectedIndex()).equals("0")){
                        detailTransaksi temp = DummyDataManager.getInstance().getDummyData().buatDetailTransaksi();
                        cek = true;
                        namaBarang = labelNamaBarang[i].getText();
                        stock = Integer.parseInt((String)cbStock[i].getItemAt(cbStock[i].getSelectedIndex()));
                        harga = Integer.parseInt((String)cbStock[i].getItemAt(cbStock[i].getSelectedIndex())) * Integer.parseInt(labelHargaBarang[i].getText());
                        
                        temp.idBarang = barangGenre.get(i).id;
                        temp.idGenre = idGenre;
                        temp.namaBarang = namaBarang;
                        temp.jumlahPembelian = stock;
                        temp.totalHarga = harga;
                        temp.next = null;
                        DummyDataManager.getInstance().getDummyData().addLastDetailTransaksi(temp);
                    }
                }
                if(cek){
                    frame.setVisible(false);
                    new MenuPembayaran(head);
                }else{
                    JOptionPane.showMessageDialog(null, "Silahkan Pilih Barang yang Ingin Dibeli!!");
                }
            }
        });
        
        labelId = new JLabel[barangGenre.size()];
        labelNamaBarang = new JLabel[barangGenre.size()];
        labelHargaBarang = new JLabel[barangGenre.size()];
        cbStock = new JComboBox[barangGenre.size()+1];
        
        int y = 65;
        for(int i = 0; i < barangGenre.size(); i++){
            String[] banyak = new String[barangGenre.get(i).stock+1];
            for(int j = 0; j < barangGenre.get(i).stock+1; j++){
                banyak[j] = Integer.toString(j);
            }
            
            labelId[i] = new JLabel(Integer.toString(i+1) + ".");
            labelNamaBarang[i] = new JLabel(barangGenre.get(i).namaBarang);
            labelHargaBarang[i] = new JLabel(Integer.toString(barangGenre.get(i).harga));
            cbStock[i] = new JComboBox(banyak);
            
            labelId[i].setBounds(50, y, 100, 30);
            labelNamaBarang[i].setBounds(100, y, 200, 30);
            labelHargaBarang[i].setBounds(250, y, 100, 30);
            cbStock[i].setBounds(400, y+5, 100, 20);
            y = y + 25;
            frame.add(labelId[i]);
            frame.add(labelNamaBarang[i]);
            frame.add(labelHargaBarang[i]);
            frame.add(cbStock[i]);
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
