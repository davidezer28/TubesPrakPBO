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
public class DataAdmin extends DataUser{
    private String email;
    
    DataAdmin next;

    public DataAdmin(int id, String nama, String username, String password) {
        super(id, nama, username, password);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public DataAdmin getNext() {
        return next;
    }

    public void setNext(DataAdmin next) {
        this.next = next;
    }
    
    public DataAdmin addLastAdmin(DataAdmin adminHead, DataAdmin baru){
        DataAdmin temp = adminHead;
        if(adminHead == null){
            adminHead = baru;
            return adminHead;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = baru;
        return adminHead;
    }
}
