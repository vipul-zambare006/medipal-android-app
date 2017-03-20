package sg.edu.nus.medipalapplication.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Category;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.CategoryAdapter;
import sg.edu.nus.medipalapplication.database.CategoryDAO;

/**
 * Created by Govardhan on 20/3/2017.
 */

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryrecyclerView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        CategoryDAO categoryDatabase = new CategoryDAO(this);

        categoryrecyclerView = (RecyclerView) findViewById(R.id.CategoryRecycleview);
        categoryrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryrecyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Category> categoryItems = categoryDatabase.getAllCategory();

        categoryAdapter = new CategoryAdapter(this, categoryItems);
        categoryrecyclerView.setAdapter(categoryAdapter);

        loadCategory();
    }

    public void loadCategory() {
        categoryItem = new ArrayList<Category>();

        CategoryDAO categoryDatabase = new CategoryDAO(this);

        categoryDatabase.openDb();

        Cursor cursor = categoryDatabase.AllCategory();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String categorycode = cursor.getString(1);
            String categoryname = cursor.getString(2);
            String categorydescription = cursor.getString(3);

            Category itemObject = new Category(id, categorycode, categoryname, categorydescription);

            categoryItem.add(itemObject);

        }

        if (!(categoryItem.size() < 1)) {
            categoryrecyclerView.setAdapter(categoryAdapter);
        }
    }
}
