package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import sg.edu.nus.medipalapplication.database.AppointmentDAO;
import sg.edu.nus.medipalapplication.database.PersonDAO;

/**
 * Created by Gaurav on 19-03-2017.
 */

public class PersonActivity extends AppCompatActivity {

    private EditText personname,persondate,personidno,personaddress,personpostalcode,personheight,personmBloodType;
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
        loadPerson(personDAO);

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
            @Override public void onClick (View v)
            {
                if (isValid())
                {
                    save(personname.getText().toString().trim(), personidno.getText().toString().trim(), persondate.getText().toString().trim(), personaddress.getText().toString().trim(), personmBloodType.getText().toString().trim(), personpostalcode.getText().toString().trim(), personheight.getText().toString().trim());
                    Toast.makeText(getApplicationContext(), "Successfully added your details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValid()
    {
        boolean isValid = true;
        if (TextUtils.isEmpty(personname.getText().toString().trim())) {
            personname.requestFocus();
            personname.setError("Please enter name.");
            isValid = false;
        }
        if (TextUtils.isEmpty(personidno.getText().toString().trim())) {
            personidno.requestFocus();
            personname.setError("Please enter Id no.");
            isValid = false;
        }
        return isValid;
    }

    private void save(String name,String idno, String dateofbirth,String address,String bloodtype,String postalcode,String height)
    {
        PersonDAO personDAO = new PersonDAO(this);
        long result = personDAO.addPerson(name, idno, dateofbirth, address, bloodtype, postalcode, height);

        if (result > 0) {
            loadPerson(personDAO);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadPerson(PersonDAO personDAO) {
        Cursor cursor = person.getperson(personDAO);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            String dateofbirth = cursor.getString(2);
            String idNo = cursor.getString(3);
            String address = cursor.getString(4);
            String postalcode = cursor.getString(5);
            String height = cursor.getString(6);
            String bloodtype = cursor.getString(7);

            personname.setText(name);
            persondate.setText(dateofbirth);
            personidno.setText(idNo);
            personaddress.setText(address);
            personpostalcode.setText(postalcode);
            personheight.setText(height);
            personmBloodType.setText(bloodtype);
        }
    }
}