package sg.edu.nus.medipalapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sg.edu.nus.medipalapplication.fragment.DoctorFragment;
import sg.edu.nus.medipalapplication.fragment.EmergencyFragment;
import sg.edu.nus.medipalapplication.fragment.NOKFragment;


/**
 * Created by Rach on 18/3/2017.
 */

public class ICEPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public ICEPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NOKFragment nokFragment = new NOKFragment();
                return nokFragment;
            case 1:
                DoctorFragment doctorFragment = new DoctorFragment();
                return doctorFragment;
            default:
                EmergencyFragment bookingFragment = new EmergencyFragment();
                return bookingFragment;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
