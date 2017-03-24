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
        long startTime = intent.getLongExtra(Constant.STARTTIME, 0);
        String reminderMessage = intent.getStringExtra("Message");

        if (matcher.matchAction(action)) {
            if (CREATE.equals(action)) {
                execute(CREATE, reminderID, reminderMessage, startTime);
            }

            if (CANCEL.equals(action)) {
                execute(CANCEL, reminderID, reminderMessage, startTime);
            }
        }
    }

    private void execute(String action, int id, String msg, long startTime) {
        Intent intent;
        PendingIntent pi;
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("ID", id);
        intent.putExtra("Message", msg);

        pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (CREATE.equals(action)) {

            am.setRepeating(AlarmManager.RTC_WAKEUP, startTime, AlarmManager.INTERVAL_DAY, pi);

        } else if (CANCEL.equals(action)) {
            am.cancel(pi);
        }
    }
}
