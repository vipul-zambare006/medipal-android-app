package sg.edu.nus.medipalapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.application.App;
import sg.edu.nus.medipalapplication.database.AppointmentDAO;

/**
 * Created by DELL on 3/19/2017.
 */

public class EditAppointmentActivity extends AppCompatActivity {
    EditText editLocation,editDate,editTime,editDescription;
    Appointment appointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        Button btnUpdate, btnDelete, btnCancel;
        final AppointmentDAO appointmentDAO;
        Intent intent = getIntent();
        //appointmentDAO = new appointmentDAO(this, "", null, 1);
        appointmentDAO = new AppointmentDAO(this);
        final int id = intent.getExtras().getInt("Id");
        final String location = intent.getExtras().getString("location");
        final String description = intent.getExtras().getString("description");
        final String date = intent.getExtras().getString("datetime");
        // final String time = intent.getExtras().getString("Time");

        editLocation = (EditText) findViewById(R.id.txtAptLocation);
        editDescription = (EditText) findViewById(R.id.txtDescription);
        editDate = (EditText) findViewById(R.id.txtDate);
        //editTime = (EditText) findViewById(R.id.txtTime);

        editLocation.setText(location);
        editDescription.setText(description);
        editDate.setText(date);
        //editTime.setText(time);

        btnUpdate = (Button) findViewById(R.id.btnUpdateAppointment);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                update(id, editLocation.getText().toString(), editDescription.getText().toString(), editDate.getText().toString(), appointmentDAO);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                delete(id, appointmentDAO);
            }
        });
    }

    private void delete(int id, AppointmentDAO appointmentDAO) {
       // App.appointment.DeleteAppointmentById(id, appointmentDAO);
        appointment.DeleteAppointmentById(id, appointmentDAO);

        Toast.makeText(getApplicationContext(), "deleted Successfully", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),AddAppointmentActivity.class);
        startActivity(i);
    }

    private View.OnClickListener btnDeleteOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : logic to delete appointment

                /*final Appointment appointmentsData = appointments.get(position);
                //Toast.makeText(context, "OnLongClick Called at position " + position, Toast.LENGTH_SHORT).show();
                removeItem(appointmentsData);*/
            }
        };
    }

    private void update(int id, String location,String desc, String datetime,AppointmentDAO appointmentDAO){

        Appointment appointment = new Appointment(id,location,desc,datetime);
        //App.appointment.UpdateAppointmentById(appointment, appointmentDAO);
        appointment.UpdateAppointmentById(appointment, appointmentDAO);
        /*if(result>0){*/

        editLocation.setText(location);
        editDescription.setText(desc);
        editDate.setText(datetime);

        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),AddAppointmentActivity.class);
        startActivity(i);

//        }else
//        {
//            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
//        }
    }
}

