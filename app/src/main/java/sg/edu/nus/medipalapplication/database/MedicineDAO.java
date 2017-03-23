package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.MedicalID;
import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;

/**
 * Created by Govardhan on 20/3/2017.
 */

public class MedicineDAO {
    Context c;
    SQLiteDatabase db;
    DBHelper databaseHelper;

    public MedicineDAO(Context context) {

        this.c = context;
        databaseHelper = new DBHelper(c);
    }

    public MedicineDAO openDb() {

        db = databaseHelper.getWritableDatabase();

        return this;
    }

    public void close() {

        databaseHelper.close();

    }

    public long medicineAdd(String medicinename, String medicinedescription, String medicinecatid, String medicinereminderid, String medicineremind, String medicinequantity, String medicinedosage, String medicinedataissued, String medicineconsumequantity, String medicinethreshold, String medicineexpirefactor) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.MedicineName, medicinename);
        contentValues.put(Constant.MedicineDescription, medicinedescription);
        contentValues.put(Constant.MedicineCatId, medicinecatid);
        contentValues.put(Constant.MedicineRemenderId, medicinereminderid);
        contentValues.put(Constant.MedicineRemind, medicineremind);
        contentValues.put(Constant.MedicineQuantity, medicinequantity);
        contentValues.put(Constant.MedicineDosage, medicinedosage);
        contentValues.put(Constant.MedicineDateIssued, medicinedataissued);
        contentValues.put(Constant.MedicineConsumeQuantity, medicineconsumequantity);
        contentValues.put(Constant.MedicineThreshold, medicinethreshold);
        contentValues.put(Constant.MedicineExpireFactor, medicineexpirefactor);

        return db.insert(Constant.MedicineTableName, Constant.MedicineId, contentValues);

    }

    public Cursor getAllMedicine() {

        String[] columns = {Constant.MedicineId, Constant.MedicineName, Constant.MedicineDescription, Constant.MedicineCatId, Constant.MedicineRemenderId, Constant.MedicineRemind, Constant.MedicineQuantity, Constant.MedicineDosage, Constant.MedicineDateIssued, Constant.MedicineConsumeQuantity, Constant.MedicineThreshold, Constant.MedicineExpireFactor};
        return db.query(Constant.MedicineTableName, columns, null, null, null, null, null);
    }

    public long medicineUpdate(int id, String medicinename, String medicinedescription, String medicinecatid, String medicinereminderid, String medicineremind, String medicinequantity, String medicinedosage, String medicinedataissued, String medicineconsumequantity, String medicinethreshold, String medicineexpirefactor) {
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.MedicineName, medicinename);
            contentValues.put(Constant.MedicineDescription, medicinedescription);
            contentValues.put(Constant.MedicineCatId, medicinecatid);
            contentValues.put(Constant.MedicineRemenderId, medicinereminderid);
            contentValues.put(Constant.MedicineRemind, medicineremind);
            contentValues.put(Constant.MedicineQuantity, medicinequantity);
            contentValues.put(Constant.MedicineDosage, medicinedosage);
            contentValues.put(Constant.MedicineDateIssued, medicinedataissued);
            contentValues.put(Constant.MedicineConsumeQuantity, medicineconsumequantity);
            contentValues.put(Constant.MedicineThreshold, medicinethreshold);
            contentValues.put(Constant.MedicineExpireFactor, medicineexpirefactor);

            return db.update(Constant.MedicineTableName, contentValues, Constant.MedicineId + "=?", new String[]{String.valueOf(id)});
        /*long result = sd.insert(Table_Name, null, contentValues); */

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long medicineDelete(int id) {
        try {

            return db.delete(Constant.MedicineTableName, Constant.MedicineId + "=?", new String[]{String.valueOf(id)});
        /*long result = sd.insert(Table_Name, null, contentValues); */

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Medicine> getAllSpinnerdata() {

        ArrayList categoryName = new ArrayList();
        Log.v("spinner", Integer.toString(categoryName.size()));
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM  categorytable", null);

        while (cursor.moveToNext()) {
            Log.v("name", cursor.getString(1));
            categoryName.add(cursor.getString(1));

            // editText.append(cursor.getString(1) + "" +cursor.getString(2) + "" +cursor.getString(3));
        }
        cursor.close();
        return categoryName;
    }

    public long save(MedicalID member) {
        ContentValues values = new ContentValues();
        //values.put(DataBaseHelper.MID_COLUMN, member.getMemberNumber());
        values.put(Constant.CONDITION, member.getCondition());
        values.put(Constant.STARTDATE, member.getStartdate());
        values.put(Constant.CONDITIONTYPE, member.getConditiontype());

        return db.insert(Constant.HealthBio_Table_Name, null, values);
    }

    public int delete(Integer id) {
        return db.delete(Constant.HealthBio_Table_Name, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(id)});
    }

    public ArrayList<MedicalID> getMembers() {
        ArrayList<MedicalID> members = new ArrayList<MedicalID>();

        Cursor cursor = db.query(Constant.HealthBio_Table_Name,
                new String[]{Constant.COLUMN_ID,
                        Constant.CONDITION,
                        Constant.STARTDATE,
                        Constant.CONDITIONTYPE}, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String condition = cursor.getString(1);
            String startdate = cursor.getString(2);
            String conditiontype = cursor.getString(3);

            MedicalID member = new MedicalID(condition, startdate, conditiontype, id);
            members.add(member);
        }
        return members;
    }
}