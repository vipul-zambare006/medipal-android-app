package sg.edu.nus.medipalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.activity.AddAppointmentActivity;
import sg.edu.nus.medipalapplication.activity.CategoryActivity;
import sg.edu.nus.medipalapplication.activity.ConsumptionTabsActivity;
import sg.edu.nus.medipalapplication.activity.ICETabLayoutActivity;
import sg.edu.nus.medipalapplication.activity.MedicineActivity;

public class MainActivity extends AppCompatActivity {

    TextView appointmentTextView;
    TextView medicineTextView;
    TextView categoryTextView;
    TextView consumptionTextView;
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

        textView = (TextView) findViewById(R.id.MedicineId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MedicineActivity.class);
                startActivity(intent);
            }
        });

        textView = (TextView) findViewById(R.id.CategoryId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        textView = (TextView) findViewById(R.id.consumptionID);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConsumptionTabsActivity.class);
                startActivity(intent);
            }
        });

        textView = (TextView) findViewById(R.id.ICEId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ICETabLayoutActivity.class);
                startActivity(intent);
            }
        });

    }
}