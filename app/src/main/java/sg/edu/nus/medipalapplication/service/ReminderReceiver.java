package sg.edu.nus.medipalapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.ReminderConsumptionActivity;
import sg.edu.nus.medipalapplication.database.Constant;

/**
 * Created by Gaurav on 22-03-2017.
 */

public class ReminderReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent popupIntent = new Intent(context, ReminderConsumptionActivity.class);
        String medicineName = intent.getExtras().getString(Constant.MedicineName);
        String quantity = intent.getExtras().getString(Constant.MedicineConsumeQuantity);

        //Set values of popup for the next screen
        popupIntent.putExtra(Constant.MedicineName, medicineName);
        popupIntent.putExtra(Constant.MedicineConsumeQuantity, quantity);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, intent.getIntExtra("ID", 10), popupIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("Medicine " + medicineName)
                .setContentText(intent.getStringExtra("Message "))
                .setSound(sound)
                .setContentIntent(pendingIntent)
                .addAction(0, "Attention you have one new notification", pendingIntent)
                .setSmallIcon(R.drawable.ic_medication)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(intent.getIntExtra("ID", 20), notification);
    }

}
