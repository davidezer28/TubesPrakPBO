/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dense
 */
public class DataBarang {
    private int id;
    private int idGenre;
    private String namaBarang;
    private int stock;
    private int harga;

    public DataBarang(int id, int idGenre, String namaBarang, int stock, int harga) {
        this.id = id;
        this.idGenre = idGenre;
        this.namaBarang = namaBarang;
        this.stock = stock;
        this.harga = harga;
    }

    public DataBarang() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
}
