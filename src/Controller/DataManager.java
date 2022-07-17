/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;

/**
 *
 * @author davidezer
 */
public class DataManager {
    static DataManager instance;
    private Data data;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
