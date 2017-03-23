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

import sg.edu.nus.medipalapplication.activity.MedicalLayout;
import sg.edu.nus.medipalapplication.activity.PersonActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView categoryimage,appoinmentimage,medicalText,persontext;
    TextView medicine;
    View mview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        medicalText = (ImageView) findViewById(R.id.HEALTH);
        medicalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicalLayout.class);
                startActivity(intent);
            }
        });


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

//        categoryimage = (ImageView) findViewById(R.id.Category);
//        categoryimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//        appoinmentimage = (ImageView) findViewById(R.id.Appointment);
//        categoryimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

       if (id == R.id.nav_person)
          startActivity(new Intent(getApplicationContext(), PersonActivity.class));
//        } else if (id == R.id.nav_medical) {
//            startActivity(new Intent(getApplicationContext(), MedicalActivity.class));
//        } else if (id == R.id.nav_ICE) {
//            startActivity(new Intent(getApplicationContext(), ICEActivity.class));
//        } else if (id == R.id.nav_category) {
//            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
//        } else if (id == R.id.nav_medicine) {
//            startActivity(new Intent(getApplicationContext(), MedicineActivity.class));
//        } else if (id == R.id.nav_measurement) {
//            startActivity(new Intent(getApplicationContext(), MeasurementActivity.class));
//        } else if (id == R.id.nav_appointment) {
//            startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));
//        } else if (id == R.id.nav_reminder) {
//            startActivity(new Intent(getApplicationContext(), ReminderActivity.class));
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
