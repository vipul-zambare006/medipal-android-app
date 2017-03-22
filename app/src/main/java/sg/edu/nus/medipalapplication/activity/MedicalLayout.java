package sg.edu.nus.medipalapplication.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import fragment.MedicalFragment;

public class MedicalLayout extends AppCompatActivity {

    private FrameLayout mContainer;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContainer = (FrameLayout) findViewById(R.id.container);
        setFragment();
    }

    private void setFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(mContainer.getId(), MedicalFragment.newInstance(),"Medical fragment").commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override protected void onStop() {
        //App.clubStore.saveAll(this);
        super.onStop();
    }
}