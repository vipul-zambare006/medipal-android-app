package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBioID;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class HealthBioDAO extends DBHelper {

    private static final String WHERE_ID_EQUALS = Constant.COLUMN_ID + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SQLiteDatabase database;

    public HealthBioDAO(Context context) {
        super(context);
    }

    public long save(HealthBioID member) {
        ContentValues values = new ContentValues();
        //values.put(DataBaseHelper.MID_COLUMN, member.getMemberNumber());
        values.put(Constant.CONDITION, member.getCondition());
        values.put(Constant.STARTDATE, member.getStartdate());
        values.put(Constant.CONDITIONTYPE, member.getConditiontype());

        return database.insert(Constant.HealthBio_Table_Name, null, values);
    }

    public int delete(HealthBioID member) {
        return database.delete(Constant.HealthBio_Table_Name, WHERE_ID_EQUALS,
                new String[]{member.getMedicalNumber() + ""});
    }

    //USING query() method
    public ArrayList<HealthBioID> getMembers() {
        ArrayList<HealthBioID> members = new ArrayList<HealthBioID>();

        Cursor cursor = database.query(Constant.HealthBio_Table_Name,
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

            HealthBioID member = new HealthBioID(condition, startdate, conditiontype, id);
            members.add(member);
        }
        return members;
    }

    //USING rawQuery() method
    public ArrayList<HealthBioID> getMembersRQ() {
        ArrayList<HealthBioID> members = new ArrayList<HealthBioID>();

        String sql = "SELECT " + Constant.COLUMN_ID + ","
                + Constant.CONDITION + ","
                + Constant.STARTDATE + ","
                + Constant.CONDITIONTYPE + ","
                + " FROM "
                + Constant.HealthBio_Table_Name;

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String condition = cursor.getString(1);
            String startdate = cursor.getString(2);
            String conditiontype = cursor.getString(3);

            HealthBioID member = new HealthBioID(condition, startdate, conditiontype, id);
            members.add(member);
        }
        return members;
    }

    //Retrieves a single member record with the given id
    public HealthBioID getMember(long id) {
        HealthBioID member = null;

        String sql = "SELECT * FROM " + Constant.HealthBio_Table_Name
                + " WHERE " + Constant.COLUMN_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{id + ""});

        if (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String condition = cursor.getString(1);
            String startdate = cursor.getString(2);
            String conditiontype = cursor.getString(3);

            member = new HealthBioID(condition, startdate, conditiontype, cid);
        }
        return member;
    }
}

