package sg.edu.nus.medipalapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sg.edu.nus.medipalapplication.service.ReminderService;

/**
 * Created by Gaurav on 23-03-2017.
 */

public class ReminderSetter extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ReminderService.class);
        service.setAction(ReminderService.CREATE);
        context.startService(service);
    }
}
