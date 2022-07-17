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
public class DataTransaksi {
    private int id;
    private int idUser;
    private DetailTransaksi detail;
    private int totalPembayaran;
    
    private DataTransaksi next;

    public DataTransaksi(int id, int idUser, DetailTransaksi detail, int totalPembayaran) {
        this.id = id;
        this.idUser = idUser;
        this.detail = detail;
        this.totalPembayaran = totalPembayaran;
        this.next = null;
    }

    public DataTransaksi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public DetailTransaksi getDetail() {
        return detail;
    }

    public void setDetail(DetailTransaksi detail) {
        this.detail = detail;
    }

    public int getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(int totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public DataTransaksi getNext() {
        return next;
    }

    public void setNext(DataTransaksi next) {
        this.next = next;
    }
    
    public DataTransaksi addLastDataTransaksi(DataTransaksi transaksiHead, DataTransaksi baru){
        DataTransaksi temp = transaksiHead;
        if(transaksiHead == null){
            transaksiHead = baru;
            return transaksiHead;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = baru;
        return transaksiHead;
    }
    public DataTransaksi buatDataTransaksi(){
        DataTransaksi data = new DataTransaksi();
        return data;
    }
}
