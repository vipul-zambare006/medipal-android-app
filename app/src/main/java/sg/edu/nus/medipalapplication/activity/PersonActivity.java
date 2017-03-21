package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.PersonDAO;

/**
 * Created by Gaurav on 19-03-2017.
 */

public class PersonActivity extends AppCompatActivity {

    private EditText name,date,idno,address,postalcode,height,mBloodType;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_main);

        name = (EditText) findViewById(R.id.et_name);
        date = (EditText) findViewById(R.id.et_dob);
        idno = (EditText) findViewById(R.id.et_idno);
        address = (EditText) findViewById(R.id.et_address);
        postalcode = (EditText) findViewById(R.id.et_postal);
        mBloodType= (EditText) findViewById(R.id.bloodtype_spinner);
        height = (EditText) findViewById(R.id.et_height);

        final PersonDAO personDAO = new PersonDAO(this);

        // Get the String Values for them

        String person_name = name.getText().toString();
        String person_address = address.getText().toString();
        String person_idno = idno.getText().toString();
        String person_postalcode = postalcode.getText().toString();
        String person_height = height.getText().toString();
        String bloodType= mBloodType.getText().toString().trim();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                date.setText(dateFormatter.format(calendar.getTime()));
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
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
           // name.setError(getString(R.string.first_name_validation_msg));
            name.setError("Please enter name.");
            isValid = false;
        }
        if (TextUtils.isEmpty(idno.getText().toString().trim())) {
            //idno.setError(getString(R.string.id_no_validation_msg));
            name.setError("Please enter Id no.");
            isValid = false;
        }
        return isValid;
    }
}
