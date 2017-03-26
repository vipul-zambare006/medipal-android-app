package sg.edu.nus.medipalapplication.Model;

import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;

/**
 * Created by Rach on 26/3/2017.
 */

public class MedicineConsumption {
    private String medicineName;
    private Consumption consumption;
    private int consumedQty;
    //private String consumedOn
private int consumedOn;
    public MedicineConsumption(String medicineName, int consumptionQty, int consumedOn){
        this.medicineName = medicineName;
        this.consumedQty = consumptionQty;
        this.consumedOn = consumedOn;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public int getConsumedQty() {
        return consumedQty;
    }

    public int getConsumedOn() {
        return consumedOn;
    }
}
