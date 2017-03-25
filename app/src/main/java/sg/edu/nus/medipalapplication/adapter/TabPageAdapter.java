package sg.edu.nus.medipalapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sg.edu.nus.medipalapplication.fragment.PressureFragment;
import sg.edu.nus.medipalapplication.fragment.PulseFragment;
import sg.edu.nus.medipalapplication.fragment.TemperatureFragment;
import sg.edu.nus.medipalapplication.fragment.WeightFragment;


/**
 * Created by USER on 16/3/2017.
 */

public class TabPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public TabPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PressureFragment pressure = new PressureFragment();
                return pressure;
            case 1:
                PulseFragment pulse = new PulseFragment();
                return pulse;
            case 2:
                TemperatureFragment temperature = new TemperatureFragment();
                return temperature;
            case 3:
                WeightFragment weight = new WeightFragment();
                return weight;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

