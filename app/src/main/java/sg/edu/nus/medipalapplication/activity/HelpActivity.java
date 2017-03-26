package sg.edu.nus.medipalapplication.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.HelpPagerAdapter;

/**
 * Created by monalisadebnth on 26/3/17.
 */

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_fragment_tab);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new HelpPagerAdapter(this));
    }
}
