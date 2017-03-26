package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.AppointmentDAO;
import sg.edu.nus.medipalapplication.database.Constant;

/**
 * Created by Vipul Zambare on 3/19/2017.
 */

public class SaveAppointmentActivity extends AppCompatActivity
{
    EditText editLocation,editDate,editTime,editDescription;
    Appointment appointment;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();

    private final AppointmentDAO appointmentDAO = new AppointmentDAO(this);
    private String action = "";
    private int appointmentId = 0;
    SwitchCompat appointmentReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AppointmentActivity.class));
            }
        });

        appointmentId = intent.getExtras().getInt("Id");
        final String location = intent.getExtras().getString("location");
        final String description = intent.getExtras().getString("description");
        final String date = intent.getExtras().getString("date");
        final String time = intent.getExtras().getString("time");
        action = intent.getExtras().getString("action");

        editLocation = (EditText) findViewById(R.id.txtAptLocation);
        editDescription = (EditText) findViewById(R.id.txtDescription);
        editDate = (EditText) findViewById(R.id.txtDate);
        editTime = (EditText) findViewById(R.id.txtTime);

        editLocation.setText(location);
        editDescription.setText(description);

        appointmentReminder = (SwitchCompat) findViewById(R.id.AddMedicineRemind);

        getDatePicker(date);
        View.OnClickListener timeClickListener = getTimePicker(time);
        editTime.setOnClickListener(timeClickListener);
    }

    @NonNull
    private View.OnClickListener getTimePicker(String time) {
        editTime.setText(time);
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final EditText editText = (EditText) v;
                TimePickerDialog.OnTimeSetListener timeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                                editText.setText(timeFormatter.format(calendar.getTime()));
                            }
                        };
                Calendar timeCalendar = Calendar.getInstance();
                try {
                    timeCalendar.setTime(timeFormatter.parse(editText.getText().toString()));
                } catch (ParseException e) {
                           /* Toast.makeText(MainActivity.this, R.string.generic_error, Toast.LENGTH_SHORT).show();*/
                }
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(SaveAppointmentActivity.this, timeSetListener, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        };
    }

    private void getDatePicker(String date) {
        editDate.setText(date);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                editDate.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(SaveAppointmentActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void delete(int id, AppointmentDAO appointmentDAO) {
        appointment = new Appointment();
        appointmentDAO.DeleteAppointment(id);
        //appointment.DeleteAppointmentById(id, appointmentDAO);

        Toast.makeText(getApplicationContext(), Constant.NotificationMsg_AppointmentDeleted, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),AppointmentActivity.class);
        startActivity(i);
    }

    private void update(int id, String location,String desc, String date, String time,AppointmentDAO appointmentDAO, String action)
    {
        Appointment appointment = new Appointment(id, location, desc, date, time);

        if(action != null && !action.trim().isEmpty() && action.equals("add")){
            appointmentDAO.addAppointment(appointment);

            Toast.makeText(getApplicationContext(), Constant.NotificationMsg_AppointmentAdded, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),AppointmentActivity.class);
            startActivity(i);
        }
        else
        {
            //boolean result = appointment.UpdateAppointmentById(appointment, appointmentDAO);
            boolean result =  appointmentDAO.UpdateAppointment(appointment);
            if(result)
            {
                editLocation.setText(location);
                editDescription.setText(desc);
                editDate.setText(date);
                editTime.setText(time);

                Toast.makeText(getApplicationContext(), Constant.NotificationMsg_AppointmentUpdated, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),AppointmentActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), Constant.ErrorMsg_RecordNotUpdated, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appointment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            if (isValid()) {
                update(appointmentId, editLocation.getText().toString(), editDescription.getText().toString(), editDate.getText().toString(), editTime.getText().toString(), appointmentDAO, action);
            }
        }
        if (id == R.id.action_delete) {
            delete(appointmentId,appointmentDAO);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(editLocation.getText().toString().trim())) {
            editLocation.requestFocus();
            editLocation.setError(Constant.ErrorMsg_PleaseEnterLocation);
            isValid = false;
        }

        if (TextUtils.isEmpty(editDescription.getText().toString().trim())) {
            editDescription.requestFocus();
            editDescription.setError(Constant.ErrorMsg_PleaseEnterDescription);
            isValid = false;
        }

        if (TextUtils.isEmpty(editDate.getText().toString().trim())) {
            editDate.requestFocus();
            editDate.setError(Constant.ErrorMsg_PleaseEnterDate);
            isValid = false;
        }

        if (TextUtils.isEmpty(editTime.getText().toString().trim())) {
            editTime.requestFocus();
            editTime.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }
        return isValid;
    }
}

