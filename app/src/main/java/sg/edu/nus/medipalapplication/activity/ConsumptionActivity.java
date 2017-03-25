package sg.edu.nus.medipalapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.ConsumptionAdapter;
import sg.edu.nus.medipalapplication.database.MedicineDAO;

/**
 * Created by Rach on 25/3/2017.
 */

public class ConsumptionActivity extends AppCompatActivity {

    Medicine medicine = new Medicine();
    private RecyclerView recyclerView;
    private ConsumptionAdapter consumptionAdapter;
    private ArrayList<Medicine> medicineArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view_consumption);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadConsumption(this);

        consumptionAdapter = new ConsumptionAdapter(this, medicineArrayList);
        recyclerView.setAdapter(consumptionAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.consumption_menu, menu);
        return true;
    }

    public void loadConsumption(Context context) {
        final MedicineDAO medicineDAO;
        medicineDAO = new MedicineDAO(context);
        medicineArrayList = medicine.getMedicineName(medicineDAO);

        if (!(medicineArrayList.size() < 1))
            recyclerView.setAdapter(consumptionAdapter);
    }

}
