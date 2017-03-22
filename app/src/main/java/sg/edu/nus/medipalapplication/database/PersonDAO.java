package sg.edu.nus.medipalapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import sg.edu.nus.medipalapplication.MedipalFolder.Person;

/**
 * Created by Gaurav on 19-03-2017.
 */

public class PersonDAO extends DBHelper {
    public PersonDAO(Context context) {
        super(context);
    }

    public boolean addPerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.COLUMN_NAME,person.getName());
        contantValues.put(Constant.DATEOFBIRTH,person.getDateofbirth());
        contantValues.put(Constant.UNIQUEID,person.getIdno());
        contantValues.put(Constant.PERSONALADDRESS,person.getAddress());
        contantValues.put(Constant.POSTALCODE,person.getPostalcode());
        contantValues.put(Constant.HEIGHT,person.getHeight());
        contantValues.put(Constant.BLOODTYPE,person.getBloodtype());

        // Database query to Insert data to table.

        db.insert(Constant.PersonalBio_Table_Name, null, contantValues);
        db.close();
        return true;
    }

    public boolean UpdatePerson(Person person)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.COLUMN_NAME,person.getName());
        contantValues.put(Constant.DATEOFBIRTH,person.getDateofbirth());
        contantValues.put(Constant.UNIQUEID,person.getIdno());
        contantValues.put(Constant.PERSONALADDRESS,person.getAddress());
        contantValues.put(Constant.POSTALCODE,person.getPostalcode());
        contantValues.put(Constant.HEIGHT,person.getHeight());
        contantValues.put(Constant.BLOODTYPE,person.getBloodtype());

        db.update(Constant.PersonalBio_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(person.getId())});
        db.close();
        return true;
    }
    public Cursor GetPerson(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Person",null);
        return cursor;
    }

}