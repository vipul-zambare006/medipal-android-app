package sg.edu.nus.medipalapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_edit);

        Intent intent = getIntent();

        final int id = intent.getExtras().getInt("Id");
        final String code = intent.getExtras().getString("Code");
        final String name = intent.getExtras().getString("Category");
        final String description = intent.getExtras().getString("Description");


        editcode = (EditText) findViewById(R.id.EditCode);
        editcategory = (EditText) findViewById(R.id.EditCategory);
        editdescription = (EditText) findViewById(R.id.EditDesription);

        editcode.setText(code);
        editcategory.setText(name);
        editdescription.setText(description);

        categotyupdate = (Button) findViewById(R.id.Categoryupdatebutton);
        categotyupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update(id, editcode.getText().toString(), editcategory.getText().toString(), editdescription.getText().toString());

            }
        });

    }

    private void update(int id, String newcategotycode, String newcategoryname, String newcategorydescription) {

        CategoryDAO categoryDatabase = new CategoryDAO(this);

        categoryDatabase.openDb();

        long result = categoryDatabase.categoryUpdate(id, newcategotycode, newcategoryname, newcategorydescription);
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
}