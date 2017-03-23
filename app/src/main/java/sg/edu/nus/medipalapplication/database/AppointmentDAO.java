package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;

/**
 * Created by Vipul Zambare on 3/19/2017.
 */

public class AppointmentDAO extends DBHelper {
    public AppointmentDAO(Context context) {
        super(context);
    }

    public boolean addAppointment(Appointment appointment){
        SQLiteDatabase db=this.getWritableDatabase();
        try
        {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.LOCATION,appointment.getLocation());
            contantValues.put(Constant.DESCRIPTION, appointment.getDescription());
            contantValues.put(Constant.APPOINTMENTDATE,appointment.getDate());
            contantValues.put(Constant.APPOINTMENTTIME,appointment.getTime());
            db.insert(Constant.Appointment_Table_Name, null, contantValues);
            return true;
        }
        catch (SQLiteException e)
        {
            Log.i("Appointment insert", Constant.ErrorMsg_RecordNotAdded);
            return false;
        }
        finally {
            db.close();
        }
    }

    public boolean UpdateAppointment(Appointment appointmentToUpdate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.LOCATION, appointmentToUpdate.getLocation());
            contantValues.put(Constant.DESCRIPTION, appointmentToUpdate.getDescription());
            contantValues.put(Constant.APPOINTMENTDATE, appointmentToUpdate.getDate());
            contantValues.put(Constant.APPOINTMENTTIME, appointmentToUpdate.getTime());
            db.update(Constant.Appointment_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(appointmentToUpdate.getId())});
            return true;
        }
        catch(SQLiteException ex)
        {
            Log.i("Appointment update",Constant.ErrorMsg_RecordNotUpdated);
            return false;
        }
        finally {
            db.close();
        }
    }

    public Integer DeleteAppointment(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Constant.Appointment_Table_Name, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(id)});
    }

    public Cursor GetAllAppointment(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(Constant.AppointmentSelectQuery,null);
        return cursor;
    }
}
