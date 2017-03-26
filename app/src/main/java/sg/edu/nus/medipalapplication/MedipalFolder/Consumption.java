package sg.edu.nus.medipalapplication.MedipalFolder;

import java.util.Date;

import sg.edu.nus.medipalapplication.database.ConsumptionDAO;

/**
 * Created by Rach on 25/3/2017.
 */

public class Consumption {
    private int id;
    private int medicineID;
    private int quantity;
    private Date dateTime;

    public Consumption() {
    }

    public Consumption(int id, int medicineID, int quantity, Date dateTime) {
        this.id = id;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return dateTime;
    }


    public void addConsumption(Consumption consumption, ConsumptionDAO consumptionDAO) {
        consumptionDAO.addConsumption(consumption);
    }

    /*public ArrayList<Consumption> getAppointments(ConsumptionDAO consumptionDAO) {

        ArrayList<Consumption> consumptionArrayList = new ArrayList<>();
        Cursor cursor = consumptionDAO.GetAllAppointment();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            String appointmentDate = cursor.getString(2);
            String appointmentTime = cursor.getString(3);
            String description = cursor.getString(4);

            Consumption consumption = new Consumption(id,location, description, appointmentDate,appointmentTime);
            consumptionArrayList.add(consumption);
        }
        return consumptionArrayList;
    }*/


}
