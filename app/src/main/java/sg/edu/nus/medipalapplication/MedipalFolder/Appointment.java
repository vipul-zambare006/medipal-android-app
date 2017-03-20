package sg.edu.nus.medipalapplication.MedipalFolder;

import android.database.Cursor;

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

    public Cursor getAppointments(AppointmentDAO appointmentDAO) {
        return appointmentDAO.GetAllAppointment();
    }

    public void UpdateAppointmentById(Appointment appointmentToUpdate, AppointmentDAO appointmentDAO){
        appointmentDAO.UpdateAppointment(appointmentToUpdate);
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
