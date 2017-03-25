package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;

/**
 * Created by Rach on 25/3/2017.
 */

public class ConsumptionDAO extends DBHelper {

    public ConsumptionDAO(Context context) {
        super(context);
    }

    public boolean addConsumption(Consumption consumption) {
        //consumption.getDate()
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.MEDICINEID, consumption.getMedicineID());
            contantValues.put(Constant.QUANTITY, consumption.getQuantity());
            contantValues.put(Constant.CONSUMEDON, consumption.getDate());
            db.insert(Constant.Consumption_Table_Name, null, contantValues);
            return true;
        } catch (SQLiteException e) {
            Log.i("Consumption Added", Constant.ErrorMsg_RecordNotAdded);
            return false;
        } finally {
            db.close();
        }
    }

    /*public Cursor getAllConsumption(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select Medicinename from medicinetablecategory ",null);
        return cursor;
    }*/
}
