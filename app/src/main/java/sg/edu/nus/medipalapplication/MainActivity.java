package sg.edu.nus.medipalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.activity.AddAppointmentActivity;
import sg.edu.nus.medipalapplication.activity.CategoryActivity;
import sg.edu.nus.medipalapplication.activity.MedicineActivity;

public class MainActivity extends AppCompatActivity {

    TextView appointmentTextView;
    TextView medicineTextView;
    TextView categoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentTextView = (TextView) findViewById(R.id.appointmentId);
        appointmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAppointmentActivity.class);
                startActivity(intent);
            }
        });

        medicineTextView = (TextView) findViewById(R.id.MedicineId);
        medicineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MedicineActivity.class);
                startActivity(intent);
            }
        });

        categoryTextView = (TextView) findViewById(R.id.CategoryId);
        categoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}