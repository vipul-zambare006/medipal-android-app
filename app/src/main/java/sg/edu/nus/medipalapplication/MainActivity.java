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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView categoryimage;
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mview = getLayoutInflater().inflate(R.layout.content_main,null);

        categoryimage = (ImageView) findViewById(R.id.category);
        categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
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

//        if (id == R.id.nav_person) {
//            startActivity(new Intent(getApplicationContext(), PersonActivity.class));
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
