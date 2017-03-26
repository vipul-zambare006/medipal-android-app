package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;
import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.Model.MedicineConsumption;

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
            contantValues.put(Constant.CONSUMEDON, consumption.getDate().toString());

            db.insert(Constant.Consumption_Table_Name, null, contantValues);
            return true;
        } catch (SQLiteException e) {
            Log.i("Consumption Added", Constant.ErrorMsg_RecordNotAdded);
            return false;
        } finally {
            db.close();
        }
    }

    public ArrayList<MedicineConsumption> getConsumption() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Medicine> medicineList = new ArrayList<Medicine>();
        String getMedicineNameQuery = " SELECT mtc.Medicinename, c.QUANTITY, c.CONSUMEDON FROM medicinetablecategory mtc inner join CONSUMPTION c on mtc.Medicineid = c.MEDICINEID; ";
        Cursor cursor = db.rawQuery(getMedicineNameQuery, null);

        ArrayList<MedicineConsumption> medicineConsumptions = new ArrayList<>();

        while (cursor.moveToNext()) {
            String medicineName = cursor.getString(0);
            int quantity = cursor.getInt(1);
            int consumedOn = cursor.getInt(2);

            Medicine medicine = new Medicine(medicineName);
            MedicineConsumption medicineConsumption = new MedicineConsumption(medicineName, quantity, consumedOn);

            medicineConsumptions.add(medicineConsumption);
        }

        return medicineConsumptions;
    }
}
