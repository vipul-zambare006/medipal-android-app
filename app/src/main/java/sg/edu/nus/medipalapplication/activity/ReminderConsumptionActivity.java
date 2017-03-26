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
import java.util.Date;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.ConsumptionDAO;

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
    //  private String action = "";
    private int id = 1;
    private int medicineID = 1;
    private int quantity;
    //// Begin here
    private String medicineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_consumption);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        medicineName = intent.getExtras().getString("MedicineName");
        //quantity = Integer.parseInt(intent.getExtras().getString("Quantiy"));


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        editMedicineName = (EditText) findViewById(R.id.medicine_name);
        editMedicineName.setText(medicineName);
        editQuantity = (EditText) findViewById(quantity);

        //TODO  need to get the quantity on the following screen
        //quantity= Integer.parseInt(editQuantity.getText().toString());


//        editDate = (EditText) findViewById(R.id.txtDate);
//        editTime = (EditText) findViewById(R.id.txtTime);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                addConsumption(id, medicineID, quantity, calendar.getTime());
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


    private void addConsumption(int id, int medicineID, int quantity, Date dateTime) {
        Consumption consumption = new Consumption(id, medicineID, quantity, dateTime);

        consumption.addConsumption(consumption, consumptionDAO);

        Toast.makeText(getApplicationContext(), "Consumption Added ", Toast.LENGTH_SHORT).show();
        //TODO   need to replace MainActivity with the Consumption Screen
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }

}
