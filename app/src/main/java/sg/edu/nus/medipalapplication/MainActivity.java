package sg.edu.nus.medipalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.activity.AppointmentActivity;
import sg.edu.nus.medipalapplication.activity.CategoryActivity;
import sg.edu.nus.medipalapplication.activity.ConsumptionTabsActivity;
import sg.edu.nus.medipalapplication.activity.ICETabLayoutActivity;
import sg.edu.nus.medipalapplication.activity.MedicalLayout;
import sg.edu.nus.medipalapplication.activity.MedicineActivity;
import sg.edu.nus.medipalapplication.activity.PersonActivity;

//import sg.edu.nus.medipalapplication.activity.MedicalLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView categoryimage, appoinmentimage, iceimage, healthbioimage, medicineimage, measurementimage, reminderimage, consumptionimage;
    TextView medicine;
    View mview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
//            private View.OnClickListener medicalListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this, MedicalLayout.class);
//            startActivity(intent);
//            Log.w("Done EmMedicalActivity", "");
//
//        }
//    };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mview = getLayoutInflater().inflate(R.layout.content_main,null);

        healthbioimage = (ImageView) findViewById(R.id.HEALTH);
        healthbioimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicalLayout.class);
                startActivity(intent);
            }
        });

        iceimage = (ImageView) findViewById(R.id.ICE);
        iceimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ICETabLayoutActivity.class);
                startActivity(intent);
            }
        });

        consumptionimage =(ImageView) findViewById(R.id.Consumption);
        consumptionimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsumptionTabsActivity.class);
                startActivity(intent);
            }
        });


        categoryimage = (ImageView) findViewById(R.id.Category);
        categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);


            }
        });

        medicineimage = (ImageView) findViewById(R.id.Medicine);
        medicineimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        appoinmentimage = (ImageView) findViewById(R.id.Appointment);
        appoinmentimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                startActivity(intent);


            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_person) {
          startActivity(new Intent(getApplicationContext(), PersonActivity.class));
        }
        if (id == R.id.nav_medical) {
            startActivity(new Intent(getApplicationContext(), Medicine.class));
        }
        if (id == R.id.nav_ICE) {
            startActivity(new Intent(getApplicationContext(), ICETabLayoutActivity.class));
        }
        if (id == R.id.nav_category) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        }
        if (id == R.id.nav_medicine) {
            startActivity(new Intent(getApplicationContext(), MedicineActivity.class));
        }
//       if (id == R.id.nav_measurement) {
//            startActivity(new Intent(getApplicationContext(), MeasurementActivity.class));
//        }
        if (id == R.id.nav_appointment) {
            startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));
        }
//       if (id == R.id.nav_reminder) {
//            startActivity(new Intent(getApplicationContext(), ReminderActivity.class));
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
