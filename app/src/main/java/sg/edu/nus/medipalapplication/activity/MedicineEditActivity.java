package sg.edu.nus.medipalapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.MedicineDAO;

public class MedicineEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText medicinename, medicinedescription, medicinereminderid, medicinequantity, medicinedosage, medicineconsumequantity, medicienthreshold, mediceineexpire;
    SwitchCompat Medicineremind;
    EditText medicinedateissued;
    Button medicineupdate, medicinedelete;
    Spinner medicinecatid;
    private String spinnerValueSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_edit);

        Intent intent = getIntent();

        final int id = intent.getExtras().getInt("Id");
        final String name = intent.getExtras().getString("MedicineName");
        final String description = intent.getExtras().getString("MedicineDescription");
        final String catid = intent.getExtras().getString("MedicineCatId");
        final String reminderid = intent.getExtras().getString("MedicineReminderId");
        final String remind = intent.getExtras().getString("MedicineRemind");
        final String quantity = intent.getExtras().getString("MedicineQuantity");
        final String dosage = intent.getExtras().getString("MedicineDosage");
        final String dateissued = intent.getExtras().getString("MedicineDateissued");
        final String consumequantity = intent.getExtras().getString("MedicineConsumequantity");
        final String threshold = intent.getExtras().getString("MedicineThreshold");
        final String expire = intent.getExtras().getString("MedicineExpire");


        medicinename = (EditText) findViewById(R.id.EditMedicineName);
        medicinedescription = (EditText) findViewById(R.id.EditMedicineDescription);
        medicinecatid = (Spinner) findViewById(R.id.EditMedicineCatId);
        // medicinereminderid = (EditText) findViewById(R.id.EditMedicineRemindId);
        medicinequantity = (EditText) findViewById(R.id.EditMedicineQuantity);
        medicinedosage = (EditText) findViewById(R.id.EditMedicineDosage);
        medicineconsumequantity = (EditText) findViewById(R.id.EditMedicineConsumeQuality);
        medicienthreshold = (EditText) findViewById(R.id.EditMedicineThreshold);
        mediceineexpire = (EditText) findViewById(R.id.EditMedicineExpire);
        Medicineremind = (SwitchCompat) findViewById(R.id.EditMedicineRemind);
        medicinedateissued = (EditText) findViewById(R.id.EditMedicineDateIssued);


        medicinecatid.setOnItemSelectedListener(this);
        loadSpinnerData(medicinecatid);

        medicinename.setText(name);
        medicinedescription.setText(description);
        medicinereminderid.setText(reminderid);
        medicinequantity.setText(remind);
        medicinedosage.setText(quantity);
        medicineconsumequantity.setText(dosage);
        medicienthreshold.setText(dateissued);
        mediceineexpire.setText(consumequantity);
        Medicineremind.setText(threshold);
        medicinedateissued.setText(expire);


        medicineupdate = (Button) findViewById(R.id.EditMedicineUpdate);
        medicineupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id, medicinename.getText().toString(), medicinedescription.getText().toString(), spinnerValueSelected, medicinereminderid.getText().toString(), Medicineremind.getText().toString(), medicinequantity.getText().toString(), medicinedosage.getText().toString(), medicinedateissued.getText().toString(), medicineconsumequantity.getText().toString(), medicienthreshold.getText().toString(), mediceineexpire.getText().toString());
            }
        });

        medicinedelete = (Button) findViewById(R.id.EditMedicineDelete);
        medicinedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete(id);
            }
        });
    }

    private void update(int id, String newmedicinename, String newmedicinedescription, String newmedicinecatid, String newmedicinereminderid, String newmedicineremind, String newmedicinequantity, String newmedicinedosage, String newmedicinedataissued, String newmedicineconsumequantity, String newmedicinethreshold, String newmedicineexpirefactor) {

        MedicineDAO medicineDatabase = new MedicineDAO(this);

        medicineDatabase.openDb();

        long result = medicineDatabase.medicineUpdate(id, newmedicinename, newmedicinedescription, newmedicinecatid, newmedicinereminderid, newmedicineremind, newmedicinequantity, newmedicinedosage, newmedicinedataissued, newmedicineconsumequantity, newmedicinethreshold, newmedicineexpirefactor);
        Log.v("ResultValues", Long.toString(result));
        if (result >= 0) {

            medicinename.setText(newmedicinename);
            medicinedescription.setText(newmedicinedescription);
            //medicinereminderid.setText(newmedicinereminderid);
            medicinequantity.setText(newmedicineremind);
            medicinedosage.setText(newmedicinequantity);
            medicineconsumequantity.setText(newmedicinedosage);
            medicienthreshold.setText(newmedicinedataissued);
            mediceineexpire.setText(newmedicineconsumequantity);
            Medicineremind.setText(newmedicinethreshold);
            medicinedateissued.setText(newmedicineexpirefactor);

            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
        }
        medicineDatabase.close();

    }

    private void delete(int id) {

        MedicineDAO medicineDatabase = new MedicineDAO(this);

        medicineDatabase.openDb();

        long result = medicineDatabase.medicineDelete(id);
        Log.v("ResultValues", Long.toString(result));
        if (result > 0) {

            this.finish();

        } else {
            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
        }
        medicineDatabase.close();

    }

    private void loadSpinnerData(Spinner medicinecatid) {

        MedicineDAO medicineDatabase = new MedicineDAO(getApplicationContext());
        ArrayList<Medicine> itemObjectList = medicineDatabase.getAllSpinnerdata();
        Log.v("result", Integer.toString(itemObjectList.size()));
        ArrayAdapter<Medicine> dataAdapter = new ArrayAdapter<Medicine>(this,
                android.R.layout.simple_spinner_item, itemObjectList);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        medicinecatid.setAdapter(dataAdapter);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        spinnerValueSelected = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "You selected: " + spinnerValueSelected,
                Toast.LENGTH_LONG).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }


}
