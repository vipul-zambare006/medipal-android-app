package sg.edu.nus.medipalapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.database.CategoryDAO;

/**
 * Created by Govardhan on 20/3/2017.
 */

public class CategoryEditActivity extends AppCompatActivity {

    Spinner spinner;
    EditText editcode, editcategory, editdescription;
    Button categotyupdate;
    SwitchCompat categoryreminder;
    View mview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_edit);

        mview=getLayoutInflater().inflate(R.layout.activity_category, null);

        Intent intent = getIntent();

        final int id = intent.getExtras().getInt("Id");
        final String code = intent.getExtras().getString("Code");
        final String name = intent.getExtras().getString("Category");
        final String description = intent.getExtras().getString("Description");
        final String reminder = intent.getExtras().getString("Reminder");


        editcode = (EditText) findViewById(R.id.EditCode);
        editcategory = (EditText) findViewById(R.id.EditCategory);
        editdescription = (EditText) findViewById(R.id.EditDesription);

        categoryreminder = (SwitchCompat) findViewById(R.id.AddCategoryRemind);
        categoryreminder.setOnCheckedChangeListener(onCheckedChanged());

        if (editcategory.getText().toString().trim().equals("CHRONIC")) {
            categoryreminder.setClickable(true);
        } else if (editcategory.getText().toString().trim().equals("CHRONIC")) {
            categoryreminder.setClickable(true);
        }

        editcode.setText(code);
        editcategory.setText(name);
        editdescription.setText(description);
        categoryreminder.setText(reminder);

        categotyupdate = (Button) findViewById(R.id.Categoryupdatebutton);
        categotyupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update(id, editcode.getText().toString(), editcategory.getText().toString(), editdescription.getText().toString(),categoryreminder.getText().toString());

            }
        });

    }

    private void update(int id, String newcategotycode, String newcategoryname, String newcategorydescription,String newcategoryreminder) {

        CategoryDAO categoryDatabase = new CategoryDAO(this);

        categoryDatabase.openDb();

        long result = categoryDatabase.categoryUpdate(id, newcategotycode, newcategoryname, newcategorydescription,newcategoryreminder);
        Log.v("ResultValues", Long.toString(result));
        if (result > 0) {

            editcode.setText(newcategotycode);
            editcategory.setText(newcategoryname);
            editdescription.setText(newcategorydescription);

            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
        }
        categoryDatabase.close();
    }
    private CompoundButton.OnCheckedChangeListener onCheckedChanged() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                switch (buttonView.getId()) {
                    case R.id.AddCategoryRemind:
                        setButtonState(isChecked);
                        break;
                }
            }
        };
    }

    private void setButtonState(boolean state) {


        if (state) {
            mview.setEnabled(true);
            //Toast.makeText(CategoryEditActivity.this, "Button enabled!", Toast.LENGTH_SHORT).show();
        } else {
            mview.setEnabled(false);
            //Toast.makeText(CategoryEditActivity.this, "Button disabled!", Toast.LENGTH_SHORT).show();
        }
    }

}