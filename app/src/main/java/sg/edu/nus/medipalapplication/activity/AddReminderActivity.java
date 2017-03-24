package sg.edu.nus.medipalapplication.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sg.edu.nus.medipalapplication.MedipalFolder.Reminder;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.ReminderDAO;

/**
 * Created by Gaurav on 22-03-2017.
 */

public class AddReminderActivity extends AppCompatActivity {

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    ReminderDAO reminderDAO = new ReminderDAO(this);
    private EditText reminderId;
    private TimePicker timePicker;
    private EditText frequency;
    private EditText interval;
    private Button save;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Reminder");
        setContentView(R.layout.activity_add_reminder);
        findViews();

        final Calendar[] timeCalendar = new Calendar[1];

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
                timeCalendar[0] = Calendar.getInstance();
                try {
                    timeCalendar[0].setTime(timeFormatter.parse(editText.getText().toString()));
                } catch (ParseException e) {
                           /* Toast.makeText(MainActivity.this, R.string.generic_error, Toast.LENGTH_SHORT).show();*/
                }
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(AddReminderActivity.this, timeSetListener, timeCalendar[0].get(Calendar.HOUR_OF_DAY), timeCalendar[0].get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        };
        timePicker.setOnClickListener(timeClickListener);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save(frequency.getText().toString().trim(), interval.getText().toString().trim(), timeCalendar[0]);
            }
        });
    }

    private void findViews() {
        reminderId = (EditText) findViewById(R.id.reminder_msg_id);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        frequency = (EditText) findViewById(R.id.frequency);
        interval = (EditText) findViewById(R.id.interval);
        save = (Button) findViewById(R.id.btn_save);
    }

    private void save(String frequency, String interval, Calendar calender) {
        int frequencyNumber = Integer.parseInt(frequency);
        int intervalNumber = Integer.parseInt(interval);
        Date date = calender.getTime();

        Reminder reminder = new Reminder(frequencyNumber, intervalNumber, date);
        ReminderDAO reminderDAO = new ReminderDAO(this);

        boolean result = reminderDAO.addReminder(reminder);

        if (result) {

/*
            medicinename.setText("");
            medicinedescription.setText("");
            medicinecatid.setTag(spinnerValueSelected);
            medicinereminderid.setText("");
            Medicineremind.setText("");
            medicinequantity.setText("");
            medicinedosage.setText("");
            medicinedateissued.setText("");
            medicineconsumequantity.setText("");
            medicienthreshold.setText("");
            mediceineexpire.setText("");
*/

        } else {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
    }


}
