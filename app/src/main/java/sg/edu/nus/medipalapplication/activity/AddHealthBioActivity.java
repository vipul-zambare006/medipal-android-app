package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBio;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.Constant;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 25/3/17.
 */

public class AddHealthBioActivity extends AppCompatActivity {

    private final HealthBioDAO healthBioDAO = new HealthBioDAO(this);
    EditText editCondition, editStartDate, editConditionType;
    Button btn_save;
    HealthBio healthBio;
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private String action = "";
    private int healthId = 0;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_healthbio);

        Intent intent = getIntent();

        healthId = intent.getExtras().getInt("Id");
        final String condition = intent.getExtras().getString("condition");
        final String startdate = intent.getExtras().getString("startdate");
        final String conditiontype = intent.getExtras().getString("conditiontype");
        action = intent.getExtras().getString("action");

        editCondition = (EditText) findViewById(R.id.et_condition);
        editStartDate = (EditText) findViewById(R.id.et_start_date);
        editConditionType = (EditText) findViewById(R.id.et_condition_type);

        editCondition.setText(condition);
        editStartDate.setText(startdate);
        editConditionType.setText(conditiontype);

        getDatePicker(startdate);
    }

    private void getDatePicker(String date) {
        editStartDate.setText(date);
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                editStartDate.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(AddHealthBioActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void delete(int id, HealthBioDAO appointmentDAO) {
        healthBio = new HealthBio();
        healthBio.DeleteAppointmentById(id, appointmentDAO);

        Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioDeleted, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
        startActivity(i);
    }

    private void update(int id, String condition, String startdate, String conditiontype, HealthBioDAO healthBioDAO, String action) {

        HealthBio healthBio = new HealthBio(id, condition, startdate, conditiontype);

        if (action != null && !action.trim().isEmpty() && action.equals("add")) {
            healthBio.addHealthBio(healthBio, healthBioDAO);

            Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioAdded, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
            startActivity(i);
        }
        else {
            boolean result = healthBio.UpdateHealthBioById(healthBio, healthBioDAO);

            if (result) {
                editCondition.setText(condition);
                editStartDate.setText(startdate);

                Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioUpdated, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), Constant.ErrorMsg_RecordNotUpdated, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.healthbio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_healthBio) {
            if (isValid()) {
                update(healthId, editCondition.getText().toString(), editStartDate.getText().toString(), editConditionType.getText().toString(), healthBioDAO, action);
                finish();
            }
        }
        if (id == R.id.action_delete) {
            delete(healthId, healthBioDAO);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(editCondition.getText().toString().trim())) {
            editCondition.requestFocus();
            editCondition.setError(Constant.ErrorMsg_PleaseEnterLocation);
            isValid = false;
        }

        if (TextUtils.isEmpty(editStartDate.getText().toString().trim())) {
            editStartDate.requestFocus();
            editStartDate.setError(Constant.ErrorMsg_PleaseEnterDescription);
            isValid = false;
        }

        if (TextUtils.isEmpty(editConditionType.getText().toString().trim())) {
            editConditionType.requestFocus();
            editConditionType.setError(Constant.ErrorMsg_PleaseEnterDate);
            isValid = false;
        }
        return isValid;
    }
}