package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.Person;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.PersonDAO;

/**
 * Created by Gaurav on 19-03-2017.
 */

public class PersonActivity extends AppCompatActivity {

    private EditText personname,persondate,personidno,personaddress,personpostalcode,personheight,personmBloodType;
    private ArrayList<Person> personArrayList;
    Person person = new Person();

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_main);

        personname = (EditText) findViewById(R.id.et_name);
        persondate = (EditText) findViewById(R.id.et_dob);
        personidno = (EditText) findViewById(R.id.et_idno);
        personaddress = (EditText) findViewById(R.id.et_address);
        personpostalcode = (EditText) findViewById(R.id.et_postal);
        personmBloodType= (EditText) findViewById(R.id.bloodtype_spinner);
        personheight = (EditText) findViewById(R.id.et_height);

        final PersonDAO personDAO = new PersonDAO(this);

        // Get the String Values for them

//        String person_name = name.getText().toString();
//        String person_address = address.getText().toString();
//        String person_idno = idno.getText().toString();
//        String person_postalcode = postalcode.getText().toString();
//        String person_height = height.getText().toString();
//        String bloodType= mBloodType.getText().toString().trim();

        persondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                persondate.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(PersonActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });



        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener()

        {
            @Override public void onClick (View v){
                if (isValid()) {
                    Toast.makeText(PersonActivity.this, "Saved Succesfully!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(personname.getText().toString().trim())) {
           // name.setError(getString(R.string.first_name_validation_msg));
            personname.setError("Please enter name.");
            isValid = false;
        }
        if (TextUtils.isEmpty(personidno.getText().toString().trim())) {
            //idno.setError(getString(R.string.id_no_validation_msg));
            personname.setError("Please enter Id no.");
            isValid = false;
        }
        return isValid;
    }

    private void save(String name,String idno, String dateofbirth,String address,String bloodtype,String postalcode,String height) {

        PersonDAO personDAO = new PersonDAO(this);

       // personDAO.openDb();

        long result = personDAO.addPerson(name, idno, dateofbirth, address, bloodtype, postalcode, height);

        if (result > 0) {

            personname.setText("");
            persondate.setText("");
            personidno.setText("");
            personaddress.setText("");
            personpostalcode.setText("");
            personheight.setText("");
            personmBloodType.setText("");


        } else {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
        //medicineDatabase.close();
    }

    public void loadPerson(PersonDAO personDAO) {
        personArrayList = new ArrayList<Person>();
        Cursor cursor = person.getperson(personDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String idno = cursor.getString(2);
            String dateofbirth = cursor.getString(3);
            String address = cursor.getString(4);
            String bloodtype = cursor.getString(5);
            String postalcode = cursor.getString(6);
            String height = cursor.getString(7);


            Person person = new Person(id,name,idno,dateofbirth,address,bloodtype,postalcode,height);
            personArrayList.add(person);
        }

    }
}
