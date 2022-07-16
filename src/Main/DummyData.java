/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

/**
 *
 * @author davidezer
 */
public class DummyData {
    private dataUser userHead;
    private dataAdmin adminHead;
    private ArrayList<dataBarang> barangHead = new ArrayList<>();
    private ArrayList<genreBarang> genreHead = new ArrayList<>();
    private dataTransaksi transaksiHead;
    private detailTransaksi detailTransaksiHead;
    
    public dataUser getUserHead(){
        return userHead;
    }
    public void setUserHead(dataUser userHead){
        this.userHead = userHead;
    }
    public dataAdmin getAdminHead(){
        return adminHead;
    }
    public void setAdminHead(dataAdmin adminHead){
        this.adminHead = adminHead;
    }
    public ArrayList<genreBarang> getGenreHead(){
        return genreHead;
    }
    public void setGenreHead(ArrayList<genreBarang> genreHead){
        this.genreHead = genreHead;
    }
    public ArrayList<dataBarang> getBarangHead(){
        return barangHead;
    }
    public void setBarangHead(ArrayList<dataBarang> barangHead){
        this.barangHead = barangHead;
    }
    public dataTransaksi getTransaksiHead(){
        return transaksiHead;
    }
    public void setTransaksiHead(dataTransaksi transaksiHead){
        this.transaksiHead = transaksiHead;
    }
    public detailTransaksi getDetailTransaksiHead(){
        return detailTransaksiHead;
    }
    public void setDetailTransaksiHead(detailTransaksi detailTransaksiHead){
        this.detailTransaksiHead = detailTransaksiHead;
    }
    
    public DummyData(){
        userHead = new dataUser(1, "David Ezer", "david@gmail.com", "david", "david");
        adminHead = new dataAdmin(1, "David Ezer", "david@gmail.com", "adminDavid", "adminDavid");
        dataBarang barangBaru = new dataBarang(1, 1, "600 Genesis", 5, 150000);
        dataBarang barangBaru1 = new dataBarang(2, 1, "1999 Genesis", 10, 1000000);
        dataBarang barangBaru2 = new dataBarang(3, 1, "50 Genesis", 15, 10000);
        dataBarang barangBaru3 = new dataBarang(4, 2, "300 Diamonds", 20, 100000);
        dataBarang barangBaru4 = new dataBarang(5, 2, "1000 Diamonds", 10, 700000);
        dataBarang barangBaru5 = new dataBarang(6, 3, "100 RP", 10, 100000);
        dataBarang barangBaru6 = new dataBarang(7, 3, "300 RP", 15, 200000);
        genreBarang genre1 = new genreBarang(1, "Genshin Impact");
        genreBarang genre2 = new genreBarang(2, "Mobile Lengds");
        genreBarang genre3 = new genreBarang(3, "League of Legends");
        barangHead.add(barangBaru);
        barangHead.add(barangBaru1);
        barangHead.add(barangBaru2);
        barangHead.add(barangBaru3);
        barangHead.add(barangBaru4);
        barangHead.add(barangBaru5);
        barangHead.add(barangBaru6);
        genreHead.add(genre1);
        genreHead.add(genre2);
        genreHead.add(genre3);
        
        detailTransaksiHead = new detailTransaksi(1, 1, "600 Genesis", 1, 150000);
        transaksiHead = new dataTransaksi(1, 1, detailTransaksiHead, 150000);
        
        dataUser userBaru1 = new dataUser(2, "Devna Lubianto", "devan@gmail.com", "devan", "devan");
        addLastUser(userBaru1);
    }
    
    public class dataUser{
        int id;
        String nama;
        String email;
        String username;
        String password;
        
        dataUser next;
        public dataUser(){
            
        }
        public dataUser(int id, String nama, String email, String username, String password){
            this.id = id;
            this.nama = nama;
            this.email = email;
            this.username = username;
            this.password = password;
            this.next = null;
        }
        
    }
    public class dataAdmin{
        int id;
        String nama;
        String email;
        String username;
        String password;
        
        dataAdmin next;
        public dataAdmin(int id, String nama, String email, String username, String password){
            this.id = id;
            this.nama = nama;
            this.email = email;
            this.username = username;
            this.password = password;
            this.next = null;
        }
    }
    public class genreBarang{
        int idGenre;
        String namaGenre;
        
        public genreBarang(int idGenre, String namaGenre){
            this.idGenre = idGenre;
            this.namaGenre = namaGenre;
        }
    }
    public class dataBarang{
        int id;
        int idGenre;
        String namaBarang;
        int stock;
        int harga;
        
        public dataBarang(int id, int idGenre, String namaBarang, int stock, int harga){
            this.id = id;
            this.idGenre = idGenre;
            this.namaBarang = namaBarang;
            this.stock = stock;
            this.harga = harga;
        }
    }
    public class dataTransaksi{
        int id;
        int idUser;
        detailTransaksi detail;
        int totalPembayaran;
        
        dataTransaksi next;
        
        public dataTransaksi(){
            
        }
        public dataTransaksi(int id, int idUser, detailTransaksi detail, int totalPembayaran){
            this.id = id;
            this.idUser = idUser;
            this.detail = detail;
            this.totalPembayaran = totalPembayaran;
            this.next = null;
        }
    }
    public class detailTransaksi{
        int idBarang;
        int idGenre;
        String namaBarang;
        int jumlahPembelian;
        int totalHarga;
        
        detailTransaksi next;
        
        public detailTransaksi(){
            
        }
        public detailTransaksi(int idBarang, int idGenre, String namaBarang, int jumlahPembelian, int totalHarga){
            this.idBarang = idBarang;
            this.idGenre = idGenre;
            this.namaBarang = namaBarang;
            this.jumlahPembelian = jumlahPembelian;
            this.totalHarga = totalHarga;
            this.next = null;
        }
    }
    public void addLastUser(dataUser baru){
        dataUser temp = userHead;
        if(userHead == null){
            userHead = baru;
            return;
        }
        while(temp.next != null)
            temp = temp.next;
        temp.next = baru;
        return;
    }
    public void addLastAdmin(dataAdmin baru){
        dataAdmin temp = adminHead;
        if(adminHead == null){
            adminHead = baru;
            return;
        }
        while(temp.next != null)
            temp = temp.next;
        temp.next = baru;
        return;
    }
    public void addLastTransaksi(dataTransaksi baru){
        dataTransaksi temp = transaksiHead;
        if(transaksiHead == null){
            transaksiHead = baru;
            return;
        }
        while(temp.next != null)
            temp = temp.next;
        temp.next = baru;
        return;
    }
    public void addLastDetailTransaksi(detailTransaksi baru){
        detailTransaksi temp = detailTransaksiHead;
        if(detailTransaksiHead == null){
            detailTransaksiHead = baru;
            return;
        }
        while(temp.next != null)
            temp = temp.next;
        temp.next = baru;
        return;
    }
    public detailTransaksi buatDetailTransaksi(){
        detailTransaksi detail = new detailTransaksi();
        return detail;
    }
    public dataTransaksi buatDataTransaksi(){
        dataTransaksi data = new dataTransaksi();
        return data;
    }
    public dataUser buatDataUser(){
        dataUser data = new dataUser();
        return data;
    }
}