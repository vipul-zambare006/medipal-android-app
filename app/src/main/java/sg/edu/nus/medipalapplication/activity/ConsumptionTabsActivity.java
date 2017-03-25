package sg.edu.nus.medipalapplication.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.ViewPagerAdapter;
import sg.edu.nus.medipalapplication.fragment.ConsumptionHistoryFragment;
import sg.edu.nus.medipalapplication.fragment.MedicationFragment;

/**
 * Created by Rach on 18/3/2017.
 */

public class ConsumptionTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_tablayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("MEDICATION");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_local_hospital, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("CONSUMPTION HISTORY");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_history_white_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MedicationFragment(), "ONE");
        adapter.addFrag(new ConsumptionHistoryFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }

}
