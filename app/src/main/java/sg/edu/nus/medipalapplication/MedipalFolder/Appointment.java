package sg.edu.nus.medipalapplication.MedipalFolder;

import android.database.Cursor;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.database.AppointmentDAO;

/**
 * Created by Vipul Zambare on 3/19/2017.
 */

public class Appointment {

    private int id;
    private String location;
    private String description;
    private String date;
    private String time;

    public Appointment(){
    }

    public Appointment(int id, String location, String description, String date, String time) {
        this.location = location;
        this.description = description;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public void addAppointment(Appointment appointment, AppointmentDAO appointmentDAO){
        appointmentDAO.addAppointment(appointment);
    }

    public ArrayList<Appointment> getAppointments(AppointmentDAO appointmentDAO) {

        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        Cursor cursor = appointmentDAO.GetAllAppointment();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            String appointmentDate = cursor.getString(2);
            String appointmentTime = cursor.getString(3);
            String description = cursor.getString(4);

            Appointment appointment = new Appointment(id,location, description, appointmentDate,appointmentTime);
            appointmentArrayList.add(appointment);
        }
        return appointmentArrayList;
    }

    public boolean UpdateAppointmentById(Appointment appointmentToUpdate, AppointmentDAO appointmentDAO){
       boolean result = appointmentDAO.UpdateAppointment(appointmentToUpdate);
        return result;
    }

    public void DeleteAppointmentById(int appointmentId,AppointmentDAO appointmentDAO)
    {
        appointmentDAO.DeleteAppointment(appointmentId);
    }

    public String getLocation(){
        return location;
    }

    public String getDescription(){
        return description;
    }

    public String getDate(){
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
