package sg.edu.nus.medipalapplication.MedipalFolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.medipalapplication.database.AppointmentDAO;
import sg.edu.nus.medipalapplication.database.ReminderDAO;

/**
 * Created by Gaurav on 19-03-2017.
 */

public class Reminder {
    private int id;
    private int frequency;
    private int interval;
    private String startDateTime;

    public Reminder() {

    }

    public Reminder(int frequency, int interval, String startDateTime) {
        this.frequency = frequency;
        this.interval = interval;
        this.startDateTime = startDateTime;
    }

    public int getId() {
        return 2555;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public String getstartDateTime() {
        return startDateTime;
    }

    public long getstartDateTimeLong() {
        String startDateString = startDateTime;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        Date startDate = new Date();
        try {
            startDate = df.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate.getTime();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        return id == ((Reminder) obj).id;
    }

    public void reset() {
        id = 0;
        frequency = 0;
        interval = 0;
        startDateTime = null;
    }

//    public void addReminder(Reminder reminder, ReminderDAO reminderDAO) {
//        reminderDAO.addReminder(reminder);
//    }

/*
    public Cursor getReminder(ReminderDAO reminderDAO) {
        return reminderDAO.getReminderById();
    }
*/

    public void UpdateReminderById(Reminder reminderToUpdate, ReminderDAO reminderDAO) {
        reminderDAO.UpdateReminder(reminderToUpdate);
    }

    public void DeleteReminder(int appointmentId, AppointmentDAO appointmentDAO) {
        appointmentDAO.DeleteAppointment(appointmentId);
    }

    public ArrayList<Long> getTimeinMillisecond(int interval, int frequency, long startDateTime) {

        ArrayList<Long> reminderTimeList = new ArrayList<Long>();
        for (int i = 0; i < frequency; i++) {

            long reminderTime = (60 * 60 * 1000) * (interval * frequency) + startDateTime;
            reminderTimeList.add(reminderTime);
        }
        return reminderTimeList;
    }
}
