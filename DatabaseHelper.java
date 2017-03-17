package sg.edu.nus.medipalapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static sg.edu.nus.medipalapplication.Constant.Categories_Table_Name;

/**
 * Created by Gaurav on 15-03-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedipalDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_PERSONALBIO = "CREATE TABLE " + Constant.PersonalBio_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.COLUMN_NAME + " TEXT NOT NULL, "
            + Constant.DATEOFBIRTH + " INTEGER NOT NULL , "
            + Constant.UNIQUEID + " INTEGER NOT NULL ,"
            + Constant.PERSONALADDRESS + " TEXT NOT NULL, "
            + Constant.POSTALCODE + " TEXT NOT NULL,"
            + Constant.HEIGHT + " INTEGER NOT NULL, "
            + Constant.BLOODTYPE + " TEXT NOT NULL );";

    private static final String CREATE_HEALTHBIO = "CREATE TABLE " + Constant.HealthBio_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.CONDITION + " TEXT NOT NULL, "
            + Constant.COLUMN_CREATED_TIME + " INTEGER NOT NULL, "
            + Constant.CONDITIONTYPE + " TEXT NOT NULL );";


    private static final String CREATE_CATEGORY = "CREATE TABLE " + Categories_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Categories_Table_Name + " TEXT NOT NULL, "
            + Constant.CATEGORYCODE + " TEXT NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL,"
            + Constant.REMIND + " INTEGER DEFAULT 0);";

    private static final String CREATE_MEDICINE = "CREATE TABLE " + Constant.Medicine_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.Medicine_Table_Name + " TEXT NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL, "
            + Constant.CATEGORYID + " INTEGER NOT NULL, "
            + Constant.REMINDERID + " INTEGER DEFAULT 0, "
            + Constant.QUANTITY + "INTEGER NOT NULL,"
            + Constant.DOSAGE + "INTEGER NOT NULL,"
            + Constant.DATEISSUES + "TEXT NOT NULL,"
            + Constant.THRESHOLD + "INTEGER NOT NULL);";

    private static final String CREATE_MEASUREMENT = "CREATE TABLE " + Constant.Measurement_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.SYSTOLIC + " INTEGER NOT NULL, "
            + Constant.DYASTOLIC + " INTEGER NOT NULL, "
            + Constant.PULSE + " INTEGER NOT NULL, "
            + Constant.TEMPERATURE + " INTEGER NOT NULL, "
            + Constant.WEIGHT + "INTEGER NOT NULL,"
            + Constant.MEASUREDON + "INTEGER NOT NULL);";

    private static final String CREATE_CONSUMPTIION = "CREATE TABLE " + Constant.Consumption_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.MEDICINEID + "INTEGER NOT NULL, "
            + Constant.QUANTITY + " INTEGER NOT NULL, "
            + Constant.CONSUMEDON + " INTEGER NOT NULL);";

    private static final String CREATE_APPOINTEMENT = "CREATE TABLE " + Constant.Appointment_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.FREQUENCY + " INTEGER NOT NULL,"
            + Constant.COLUMN_CREATED_TIME + " INTEGER NOT NULL,"
            + Constant.INTERVAL + " INTEGER NOT NULL);";

    private static final String CREATE_REMINDER = "CREATE TABLE " + Constant.Reminder_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.LOCATION + " TEXT NOT NULL, "
            + Constant.Appointment_Table_Name + " TEXT NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL);";

    private static final String CREATE_ICE = "CREATE TABLE " + Constant.ICE_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.COLUMN_NAME + " TEXT NOT NULL, "
            + Constant.CONTACTNO + " INTEGER NOT NULL, "
            + Constant.CONTACTTYPE + " INTEGER NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL,"
            + Constant.PREFERENCES + " INTEGER NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Execute Query to Create Tables for the app
        // db.execSQL("some sql statement to create table");
        db.execSQL(CREATE_PERSONALBIO);
        db.execSQL(CREATE_HEALTHBIO);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_MEDICINE);
        db.execSQL(CREATE_MEASUREMENT);
        db.execSQL(CREATE_CONSUMPTIION);
        db.execSQL(CREATE_REMINDER);
        db.execSQL(CREATE_APPOINTEMENT);
        db.execSQL(CREATE_ICE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_PERSONALBIO);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_HEALTHBIO);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_MEDICINE);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_MEASUREMENT);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_CONSUMPTIION);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_APPOINTEMENT);
        db.execSQL("DROP TABLE IF EXISTS" + CREATE_ICE);
    }
}





