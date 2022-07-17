/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 *
 * @author dense
 */
public class DatabaseControl {
    static DatabaseHandler conn = new DatabaseHandler();
    
    public static DataCustomer getAllUser(){
        DataCustomer userHead = null;
        conn.connect();
        String query = "SELECT * FROM data_user";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DataCustomer user = new DataCustomer();
                user.setId(rs.getInt("id"));
                user.setNama(rs.getString("nama"));
                user.setNoTelp(rs.getString("no_telp"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("Password"));
                userHead = user.addLastUser(userHead, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (userHead);
    }
    public static DataAdmin getAllAdmin(){
        DataAdmin adminHead = null;
        conn.connect();
        String query = "SELECT * FROM data_admin";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DataAdmin admin = new DataAdmin(0, "", "", "");
                admin.setId(rs.getInt("id"));
                admin.setNama(rs.getString("nama"));
                admin.setEmail(rs.getString("email"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("Password"));
                adminHead = admin.addLastAdmin(adminHead, admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (adminHead);
    }
    public static ArrayList<DataBarang> getAllBarang(){
        ArrayList<DataBarang> barangs = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM data_barang";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DataBarang barang = new DataBarang();
                barang.setId(rs.getInt("id"));
                barang.setIdGenre(rs.getInt("idGenre"));
                barang.setNamaBarang(rs.getString("namaBarang"));
                barang.setStock(rs.getInt("stock"));
                barang.setHarga(rs.getInt("harga"));
                barangs.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (barangs);
    }
    public static DataTransaksi getAllTransaksi(){
        DataTransaksi transaksiHead = null;
        conn.connect();
        String query = "SELECT * FROM data_transaksi";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DataTransaksi transaksi = new DataTransaksi();
                transaksi.setId(rs.getInt("id"));
                transaksi.setDetail(getDetailTransaksi(transaksi.getId()));
                transaksi.setIdUser(rs.getInt("idUser"));
                transaksi.setTotalPembayaran(rs.getInt("totalPembayaran"));
                transaksiHead = transaksi.addLastDataTransaksi(transaksiHead, transaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (transaksiHead);
    }
    public static DetailTransaksi getDetailTransaksi(int idTransaksi){
        DetailTransaksi detailHead = null;
        conn.connect();
        String query = "SELECT * FROM detail_transaksi WHERE idTransaksi = " + idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DetailTransaksi detail = new DetailTransaksi();
                detail.setIdDetail(rs.getInt("idDetail"));
                detail.setIdBarang(rs.getInt("idBarang"));
                detail.setIdGenre(rs.getInt("idGenre"));
                detail.setNamaBarang(rs.getString("namaBarang"));
                detail.setJumlahPembelian(rs.getInt("jumlahPembelian"));
                detail.setTotalHarga(rs.getInt("totalHarga"));
                detailHead = detail.addLastDetailTransaksi(detailHead, detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (detailHead);
    }
    public static ArrayList<GenreBarang> getAllGenreBarang(){
        ArrayList<GenreBarang> genres = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM genre_barang";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                GenreBarang genre = new GenreBarang();
                genre.setIdGenre(rs.getInt("idGenre"));
                genre.setNamaGenre(rs.getString("namaGenre"));
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (genres);
    }
    public static boolean updateUser(DataCustomer user){
        conn.connect();
        String query = "UPDATE data_user SET nama = '" + user.getNama() + "', no_telp = '" + user.getNoTelp() + "', username = '" + user.getUsername() + "', password = '" + user.getPassword() + "' WHERE id = " + user.getId();
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    public static boolean updateBarang(DataBarang barang){
        conn.connect();
        String query = "UPDATE data_barang SET idGenre = " + barang.getIdGenre() + ", namaBarang = '" + barang.getNamaBarang() + "', stock = " + barang.getStock() + ", harga = " + barang.getHarga() + " WHERE id = " + barang.getId();
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    public static boolean insertDataTransaksi(DataTransaksi transaksi){
        conn.connect();
        String query = "INSERT INTO data_transsaksi(idUser, totalPembayaran) VALUES(?, ?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaksi.getIdUser());
            stmt.setInt(2, transaksi.getTotalPembayaran());
            stmt.executeUpdate();
            DetailTransaksi detailHead = transaksi.getDetail();
            DetailTransaksi temp = detailHead;
            while(temp != null){
                insertDetailTransaksi(temp, transaksi.getId());
                temp = temp.getNext();
            }
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    public static boolean insertDetailTransaksi(DetailTransaksi detail, int idTransaksi){
        conn.connect();
        String query = "INSERT INTO detail_transaksi(idTransaksi, idBarang, idGenre, namaBarang, jumlahPembelian, totalHarga) VALUES(?, ?, ?, ?, ?, ?]";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, idTransaksi);
            stmt.setInt(2, detail.getIdBarang());
            stmt.setInt(3, detail.getIdGenre());
            stmt.setString(4, detail.getNamaBarang());
            stmt.setInt(5, detail.getJumlahPembelian());
            stmt.setInt(6, detail.getTotalHarga());
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
