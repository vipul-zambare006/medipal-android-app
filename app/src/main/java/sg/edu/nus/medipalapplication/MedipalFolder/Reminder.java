package sg.edu.nus.medipalapplication.MedipalFolder;

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
    private Date startTime;

    public Reminder() {

    }

    public Reminder(int frequency, int interval, Date startTime) {
        this.frequency = frequency;
        this.interval = interval;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public long getStartTime() {
        return startTime.getTime();
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
        startTime = null;
    }

    public void addReminder(Reminder reminder, ReminderDAO reminderDAO) {
        reminderDAO.addReminder(reminder);
    }
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

    public ArrayList<Long> getTimeinMillisecond(int interval, int frequency, Date startTime) {

        ArrayList<Long> reminderTimeList = new ArrayList<Long>();
        for (int i = 0; i < frequency; i++) {

            long reminderTime = (60 * 60 * 1000) * (interval * frequency) + startTime.getTime();
            reminderTimeList.add(reminderTime);
        }
        return reminderTimeList;
    }
}
