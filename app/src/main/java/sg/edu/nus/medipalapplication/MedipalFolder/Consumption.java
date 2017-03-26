package sg.edu.nus.medipalapplication.MedipalFolder;

import sg.edu.nus.medipalapplication.database.ConsumptionDAO;

/**
 * Created by Rach on 25/3/2017.
 */

public class Consumption {
    private int id;
    private int medicineID;
    private int quantity;
    private int date;
    //private String time;

    public Consumption() {
    }

    public Consumption(int id, int medicineID, int quantity, int date) {
        this.id = id;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.date = date;
        //this.time = time;
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

    public int getDate() {
        return date;
    }

   /**//* public String getTime() {
        return time;
    }*/

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
