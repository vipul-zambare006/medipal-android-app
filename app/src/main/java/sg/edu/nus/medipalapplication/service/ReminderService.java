package sg.edu.nus.medipalapplication.service;

/**
 * Created by Gaurav on 22-03-2017.
 */

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sg.edu.nus.medipalapplication.database.Constant;

public class ReminderService extends IntentService {

    public static final String CREATE = "CREATE";
    public static final String CANCEL = "CANCEL";
    private static final String TAG = "ReminderService";
    private IntentFilter matcher;

    public ReminderService() {
        super(TAG);
        matcher = new IntentFilter();
        matcher.addAction(CREATE);
        matcher.addAction(CANCEL);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        int reminderID = intent.getIntExtra(Constant.COLUMN_ID, 0);
        String reminderTime = intent.getStringExtra(Constant.REMINDERDATETIME);
        int frequency = intent.getIntExtra(Constant.FREQUENCY, 0);
        int interval = intent.getIntExtra(Constant.INTERVAL, 0);
        String medicineName = intent.getStringExtra(Constant.MedicineName);
        String consumeQty = intent.getStringExtra(Constant.MedicineConsumeQuantity);
        String reminderMessage = intent.getStringExtra("Message");

        if (matcher.matchAction(action)) {
            if (CREATE.equals(action)) {
                execute(CREATE, reminderID, reminderMessage, reminderTime, frequency, interval, medicineName, consumeQty);
            }

            if (CANCEL.equals(action)) {
                execute(CANCEL, reminderID, reminderMessage, reminderTime, frequency, interval, medicineName, consumeQty);
            }
        }
    }

    private void execute(String action, int reminderID, String msg, String reminderTime, int frequency, int interval, String mediName, String consumeQty) {
        Intent intent;
        PendingIntent pi;
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date reminderDateTime = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            reminderDateTime = df.parse(reminderTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reminderDateTime);

        int input = (int) (new Date()).getTime();
        intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("ID", input);
        intent.putExtra("Message", msg);
        intent.putExtra(Constant.MedicineName, mediName);
        intent.putExtra(Constant.MedicineConsumeQuantity, consumeQty);

        pi = PendingIntent.getBroadcast(this, input, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.d("service remindid", String.valueOf(input));

        if (CREATE.equals(action)) {

            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            //
           /* for (int i= 1; i<frequency;i++){
                //calendar.add(Calendar.HOUR_OF_DAY, interval);
                calendar.add(Calendar.MINUTE,interval);
               // am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pi);
            }*/

        } else if (CANCEL.equals(action)) {
            am.cancel(pi);
            pi.cancel();
        }
    }
}
