/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.DataManager;
import Model.Data;
import Controller.DatabaseControl;
import Model.DataAdmin;
import Model.DataCustomer;
import View.MainMenuScreen;

/**
 *
 * @author davidezer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Data data = new Data();
        DatabaseControl controller = new DatabaseControl();
        data.setUserHead(controller.getAllUser());
        data.setAdminHead(controller.getAllAdmin());
        data.setBarangHead(controller.getAllBarang());
        data.setGenreHead(controller.getAllGenreBarang());
        data.setTransaksiHead(controller.getAllTransaksi());
        
        DataManager.getInstance().setData(data);
        new MainMenuScreen();
    }
    
}