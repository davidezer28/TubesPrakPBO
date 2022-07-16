/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author davidezer
 */
public class DummyDataManager {
    static DummyDataManager instance;
    private DummyData data;

    public static DummyDataManager getInstance() {
        if (instance == null) {
            instance = new DummyDataManager();
        }
        return instance;
    }
    
    public DummyData getDummyData() {
        return data;
    }

    public void setDummyData(DummyData data) {
        this.data = data;
    }
}
