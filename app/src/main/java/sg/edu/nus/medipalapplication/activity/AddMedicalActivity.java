/*
package sg.edu.nus.medipalapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sg.edu.nus.medipalapplication.R;

//import application.App;

public class AddMedicalActivity extends AppCompatActivity {

    //public static final String CATEGORY = "0";
    //public static final String ALLERGY = "1";

    private EditText condition, startdate;
    private String[] mConditionTypeArray;
    private Spinner mConditiontypeSpinner;
    private String spinnerString;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedicalbio);

        condition = (EditText) findViewById(R.id.et_condition);
        startdate = (EditText) findViewById(R.id.et_start_date);

        mConditiontypeSpinner = (Spinner) findViewById(R.id.condition_type_spinner);
        mConditionTypeArray = getResources().getStringArray(R.array.condition_type);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mConditionTypeArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mConditiontypeSpinner.setAdapter(dataAdapter);

        mConditiontypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (isValid()) {
                    App.club.addMember(condition.getText().toString().trim(),
                            startdate.getText().toString().trim(), spinnerString, getApplicationContext());


                    Toast.makeText(AddMedicalActivity.this, getString(R.string.save_successsful),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(condition.getText().toString().trim())) {
            condition.setError(getString(R.string.first_name_validation_msg));
            isValid = false;
        }
        if (TextUtils.isEmpty(startdate.getText().toString().trim())) {
            startdate.setError(getString(R.string.second_name_validation_msg));
            isValid = false;
        }

        return isValid;
    }
}
*/
