package sg.edu.nus.medipalapplication.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.ConsumptionDAO;

import static sg.edu.nus.medipalapplication.R.id.quantity;

/**
 * Created by Rach on 25/3/2017.
 */

public class ReminderConsumptionActivity extends AppCompatActivity {

    private final ConsumptionDAO consumptionDAO = new ConsumptionDAO(this);
    EditText editMedicineName, editQuantity, editTime, editDate;
    Button btn_ok, btn_cancel;
    Consumption consumption;
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private String action = "";
    private int id = 1;
    private int medicineID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_consumption);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        //medicineID = intent.getExtras().getInt("Id");
        /*final int quantity = intent.getExtras().getInt("quantity");
        final String date = intent.getExtras().getString("date");
        final String time = intent.getExtras().getString("time");
        action = intent.getExtras().getString("action");*/

        editMedicineName = (EditText) findViewById(R.id.medicine_name);
        editQuantity = (EditText) findViewById(quantity);
        editDate = (EditText) findViewById(R.id.txtDate);
        editTime = (EditText) findViewById(R.id.txtTime);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        //editMedicineName.setText(location);

        //getDatePicker(date);
        //View.OnClickListener timeClickListener = getTimePicker(time);
        //editTime.setOnClickListener(timeClickListener);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConsumption(id, medicineID, quantity, "25/3/2017", "7:28");
            }
        });


    }

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
                        new TimePickerDialog(ReminderConsumptionActivity.this, timeSetListener, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
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
                        new DatePickerDialog(ReminderConsumptionActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }


    private void addConsumption(int id, int medicineID, int quantity, String date, String time) {
        Consumption consumption = new Consumption(id, medicineID, quantity, date, time);
        if (action != null && !action.trim().isEmpty() && action.equals("add")) {
            consumption.addConsumption(consumption, consumptionDAO);

            Toast.makeText(getApplicationContext(), "Consumption Added ", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

}
