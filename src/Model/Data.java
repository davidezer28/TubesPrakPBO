/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DataAdmin;
import Model.DataBarang;
import Model.DataTransaksi;
import Model.DataCustomer;
import Model.DetailTransaksi;
import Model.GenreBarang;
import java.util.ArrayList;
/**
 *
 * @author davidezer
 */
public class Data {
    private DataCustomer dataUser;
    private DataAdmin dataAdmin;
    private DataCustomer userHead;
    private DataAdmin adminHead;
    private ArrayList<DataBarang> barangHead = new ArrayList<>();
    private ArrayList<GenreBarang> genreHead = new ArrayList<>();
    private DataTransaksi transaksiHead;
    private DetailTransaksi detailHead;
    
    public DataCustomer getDataUser() {
        return dataUser;
    }
    
    public void setDataUser(DataCustomer dataUser) {
        this.dataUser = dataUser;
    }
    
    public DataAdmin getDataAdmin() {
        return dataAdmin;
    }
    
    public void setDataAdmin(DataAdmin dataAdmin) {
        this.dataAdmin = dataAdmin;
    }
    
    public DataCustomer getUserHead() {
        return userHead;
    }

    public void setUserHead(DataCustomer userHead) {
        this.userHead = userHead;
    }

    public DataAdmin getAdminHead() {
        return adminHead;
    }

    public void setAdminHead(DataAdmin adminHead) {
        this.adminHead = adminHead;
    }

    public ArrayList<DataBarang> getBarangHead() {
        return barangHead;
    }

    public void setBarangHead(ArrayList<DataBarang> barangHead) {
        this.barangHead = barangHead;
    }

    public ArrayList<GenreBarang> getGenreHead() {
        return genreHead;
    }

    public void setGenreHead(ArrayList<GenreBarang> genreHead) {
        this.genreHead = genreHead;
    }

    public DataTransaksi getTransaksiHead() {
        return transaksiHead;
    }

    public void setTransaksiHead(DataTransaksi transaksiHead) {
        this.transaksiHead = transaksiHead;
    }
    
    public DetailTransaksi getDetailHead() {
        return detailHead;
    }
    
    public void setDetailHead(DetailTransaksi detailHead) {
        this.detailHead = detailHead;
    }
}