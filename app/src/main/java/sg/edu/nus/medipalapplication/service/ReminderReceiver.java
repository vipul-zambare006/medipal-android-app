package sg.edu.nus.medipalapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.R;

/**
 * Created by Gaurav on 22-03-2017.
 */

public class ReminderReceiver extends BroadcastReceiver {
    public ReminderReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("Reminder")
                .setContentText(intent.getStringExtra("Message"))
                .setSound(sound)
                .setContentIntent(pendingIntent)
                .addAction(0, "Attention you have one new notification", pendingIntent)
                .setSmallIcon(R.drawable.ic_medication)
                .build();

        notificationManager.notify(intent.getIntExtra("ID", 0), notification);
    }

}
