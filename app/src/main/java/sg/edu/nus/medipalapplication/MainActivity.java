package sg.edu.nus.medipalapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.activity.AddAppointmentActivity;
import sg.edu.nus.medipalapplication.application.App;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.appointmentId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAppointmentActivity.class);
                startActivity(intent);
            }
        });
    }
}


    //       textView = new TextView(getBaseContext()).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // TODO: 3/19/2017
//                Intent intent = new Intent(MainActivity.this, AddAppointmentActivity.class);
//                startActivity(intent);
//            }
//        });
    //onAppointmentListClick
//    public void onAppointmentListClick (View v) {
//    }