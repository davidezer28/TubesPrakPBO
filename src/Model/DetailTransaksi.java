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
public class DetailTransaksi {
    private int idDetail;
    private int idBarang;
    private int idGenre;
    private String namaBarang;
    private int jumlahPembelian;
    private int totalHarga;
    
    private DetailTransaksi next;

    public DetailTransaksi(int idDetail, int idBarang, int idGenre, String namaBarang, int jumlahPembelian, int totalHarga) {
        this.idDetail = idDetail;
        this.idBarang = idBarang;
        this.idGenre = idGenre;
        this.namaBarang = namaBarang;
        this.jumlahPembelian = jumlahPembelian;
        this.totalHarga = totalHarga;
        this.next = null;
    }

    public DetailTransaksi() {
    }
    
    public int getIdDetail() {
        return idDetail;
    }
    
    public void setIdDetail(int idDetail){
        this.idDetail = idDetail;
    }
    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
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

    public int getJumlahPembelian() {
        return jumlahPembelian;
    }

    public void setJumlahPembelian(int jumlahPembelian) {
        this.jumlahPembelian = jumlahPembelian;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public DetailTransaksi getNext() {
        return next;
    }

    public void setNext(DetailTransaksi next) {
        this.next = next;
    }
    
    public DetailTransaksi addLastDetailTransaksi(DetailTransaksi detailHead, DetailTransaksi baru){
        DetailTransaksi temp = detailHead;
        if(detailHead == null){
            detailHead = baru;
        }else{
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = baru;
        }
        return detailHead;
    }
    
    public DetailTransaksi buatDetailTransaksi(){
        DetailTransaksi detail = new DetailTransaksi();
        return detail;
    }
}
