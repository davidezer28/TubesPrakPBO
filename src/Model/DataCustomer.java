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
public class DataCustomer extends DataUser{
    private String noTelp;
    
    private DataCustomer next;

    public DataCustomer(int id, String nama, String username, String password) {
        super(id, nama, username, password);
    }

    public DataCustomer() {
    }
    
    public String getNoTelp(){
        return noTelp;
    }
    
    public void setNoTelp(String noTelp){
        this.noTelp = noTelp;
    }
    public DataCustomer getNext(){
        return next;
    }
    
    public void setNext(DataCustomer next){
        this.next = next;
    }
    
    public DataCustomer addLastUser(DataCustomer userHead, DataCustomer baru){
        DataCustomer temp = userHead;
        if(userHead == null){
            userHead = baru;
        }else{
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = baru;
        }
        return userHead;
    }
    
    public DataCustomer buatDataUser(){
        DataCustomer data = new DataCustomer();
        return data;
    }
}
