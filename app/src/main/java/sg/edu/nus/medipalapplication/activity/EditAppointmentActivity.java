package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by Vipul Zambare on 3/19/2017.
 */

public class EditAppointmentActivity extends AppCompatActivity {
    EditText editLocation,editDate,editTime,editDescription;
    Appointment appointment;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        Button btnUpdate, btnDelete, btnCancel;
        final AppointmentDAO appointmentDAO;
        Intent intent = getIntent();
        appointmentDAO = new AppointmentDAO(this);

        final int id = intent.getExtras().getInt("Id");
        final String location = intent.getExtras().getString("location");
        final String description = intent.getExtras().getString("description");
        final String date = intent.getExtras().getString("date");
        final String time = intent.getExtras().getString("time");

        editLocation = (EditText) findViewById(R.id.txtAptLocation);
        editDescription = (EditText) findViewById(R.id.txtDescription);
        editDate = (EditText) findViewById(R.id.txtDate);
        editTime = (EditText) findViewById(R.id.txtTime);

        editLocation.setText(location);
        editDescription.setText(description);

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
                        new DatePickerDialog(EditAppointmentActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        editTime.setText(time);
        View.OnClickListener timeClickListener = new View.OnClickListener() {
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
                        new TimePickerDialog(EditAppointmentActivity.this, timeSetListener, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        };

        editTime.setOnClickListener(timeClickListener);

        btnUpdate = (Button) findViewById(R.id.btnUpdateAppointment);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                update(id, editLocation.getText().toString(), editDescription.getText().toString(), editDate.getText().toString(), editTime.getText().toString() , appointmentDAO);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                delete(id, appointmentDAO);
            }
        });
    }

    private void delete(int id, AppointmentDAO appointmentDAO) {
        appointment = new Appointment();
       // App.appointment.DeleteAppointmentById(id, appointmentDAO);
        appointment.DeleteAppointmentById(id, appointmentDAO);

        Toast.makeText(getApplicationContext(), "deleted Successfully", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),AddAppointmentActivity.class);
        startActivity(i);
    }

    private View.OnClickListener btnDeleteOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : logic to delete appointment

                /*final Appointment appointmentsData = appointments.get(position);
                //Toast.makeText(context, "OnLongClick Called at position " + position, Toast.LENGTH_SHORT).show();
                removeItem(appointmentsData);*/
            }
        };
    }

    private void update(int id, String location,String desc, String date, String time,AppointmentDAO appointmentDAO){

        Appointment appointment = new Appointment(id, location, desc, date, time);
        //App.appointment.UpdateAppointmentById(appointment, appointmentDAO);
        appointment.UpdateAppointmentById(appointment, appointmentDAO);
        /*if(result>0){*/

        editLocation.setText(location);
        editDescription.setText(desc);
        editDate.setText(date);
        editTime.setText(time);

        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),AddAppointmentActivity.class);
        startActivity(i);

//        }else
//        {
//            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
//        }
    }
}

