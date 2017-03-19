package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;

/**
 * Created by DELL on 3/19/2017.
 */

public class AppointmentDAO extends DBHelper {
    public AppointmentDAO(Context context) {
        super(context);
    }

    public boolean addAppointment(Appointment appointment){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.LOCATION,appointment.getLocation());
        contantValues.put(Constant.DESCRIPTION, appointment.getDescription());
        contantValues.put(Constant.APPOINTMENTDATETIME,appointment.getAppointmentDateTime());
        db.insert(Constant.Appointment_Table_Name, null, contantValues);
        db.close();
        return true;
    }

    public boolean UpdateAppointment(Appointment appointmentToUpdate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.LOCATION,appointmentToUpdate.getLocation());
        contantValues.put(Constant.DESCRIPTION, appointmentToUpdate.getDescription());
        contantValues.put(Constant.APPOINTMENTDATETIME,appointmentToUpdate.getAppointmentDateTime());
        db.update(Constant.Appointment_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(appointmentToUpdate.getId())});
        db.close();
        return true;
    }

    public Integer DeleteAppointment(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        //return db.delete(Constant.Appointment_Table_Name,"id = ?",new String[]{Integer.toString(id)});
        return db.delete(Constant.Appointment_Table_Name, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(id)});
    }

    public Cursor GetAppointmentById(int appointmentId){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from Appointment where _id = " + appointmentId + "", null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db,Constant.Appointment_Table_Name);
        return numRows;
    }

    public Cursor GetAllAppointment(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Appointment",null);
        return cursor;
    }
}
