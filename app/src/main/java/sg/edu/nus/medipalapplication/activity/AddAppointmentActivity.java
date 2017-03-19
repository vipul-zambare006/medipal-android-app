package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.AppointmentRecyclerViewAdapter;
import sg.edu.nus.medipalapplication.database.AppointmentDAO;

/**
 * Created by DELL on 3/19/2017.
 */

public class AddAppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentRecyclerViewAdapter adapter;
    private ArrayList<Appointment> appointmentArrayList;
    private FloatingActionButton fab;
    EditText location, description, appointmentDate, appointmentTime;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    Appointment appointment = new Appointment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_main);
        final AppointmentDAO appointmentDAO;
        appointmentDAO = new AppointmentDAO(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadAppointment(appointmentDAO);
        adapter = new AppointmentRecyclerViewAdapter(this, appointmentArrayList);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(onAddingListener(appointmentDAO));
    }

    public void loadAppointment(AppointmentDAO appointmentDAO) {
        appointmentArrayList = new ArrayList<Appointment>();
        Cursor cursor = appointment.getAppointments(appointmentDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            String appointmentDateTime = cursor.getString(2);
            String description = cursor.getString(3);

            Appointment appointment = new Appointment(id,location, description, appointmentDateTime);
            appointmentArrayList.add(appointment);
        }
        if (!(appointmentArrayList.size() < 1)) {

            recyclerView.setAdapter(adapter);
        }
    }

    private View.OnClickListener onAddingListener(final AppointmentDAO appointmentDAO) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AddAppointmentActivity.this);
                int id = 1;
                dialog.setContentView(R.layout.dialog_appointment_add); //layout for dialog
                dialog.setTitle("Add new appointment");
                dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                location = (EditText)dialog.findViewById(R.id.txtAptLocation);
                description = (EditText)dialog.findViewById(R.id.txtDescription);
                appointmentDate = (EditText)dialog.findViewById(R.id.txtDate);
                appointmentTime = (EditText)dialog.findViewById(R.id.txtTime);

                appointmentDate.setText(dateFormatter.format(selectedDate.getTime()));
                appointmentDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog.OnDateSetListener onDateSetListener =
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(year, monthOfYear, dayOfMonth);
                                        selectedDate = calendar;
                                        appointmentDate.setText(dateFormatter.format(calendar.getTime()));
                                    }
                                };
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(AddAppointmentActivity.this, onDateSetListener,
                                        currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                        currentCal.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                    }
                });

                appointmentTime.setText(timeFormatter.format(currentCal.getTime()));

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
                                new TimePickerDialog(AddAppointmentActivity.this, timeSetListener, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
                        timePickerDialog.show();
                    }
                };

                appointmentTime.setOnClickListener(timeClickListener);

                View  btnAdd = dialog.findViewById(R.id.btnAddAppointment);
                View btnCancel = dialog.findViewById(R.id.btnCancel);

                btnAdd.setOnClickListener(onConfirmListener(id,location, description, appointmentDate, appointmentTime,dialog, appointmentDAO));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }

    private View.OnClickListener onConfirmListener(final int id,final EditText location, final EditText description, final EditText appointmentDate, final EditText appointmentTime, final Dialog dialog, final AppointmentDAO appointmentDAO) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid())
                {
                    String apDate = appointmentDate.getText().toString().trim();
                    String apTime = appointmentTime.getText().toString().trim();
                    String apDateTime = apDate + apTime;

                    Appointment appointment = new Appointment(id, location.getText().toString().trim(), description.getText().toString().trim(), apDateTime);

                    //App.appointment.addAppointment(appointment, appointmentDAO);
                    appointment.addAppointment(appointment, appointmentDAO);
                    appointmentArrayList.add(appointment);
                    Toast.makeText(AddAppointmentActivity.this, "Sucessfully added appointment", Toast.LENGTH_SHORT).show();
                }

                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(location.getText().toString().trim())) {
            location.setError("please add location");
            isValid = false;
        }
        if (TextUtils.isEmpty(description.getText().toString().trim())) {
            description.setError("Please add Description");
            isValid = false;
        }
        if (TextUtils.isEmpty(appointmentDate.getText().toString().trim())) {
            appointmentDate.setError("please add date");
            isValid = false;
        }
        if (TextUtils.isEmpty(appointmentTime.getText().toString().trim())) {
            appointmentTime.setError("please add time");
            isValid = false;
        }
        return isValid;
    }

}
