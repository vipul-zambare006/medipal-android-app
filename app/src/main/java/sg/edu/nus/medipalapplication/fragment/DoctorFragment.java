package sg.edu.nus.medipalapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sg.edu.nus.medipalapplication.R;

/**
 * Created by Rach on 18/3/2017.
 */

public class DoctorFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View doctorView = inflater.inflate(R.layout.fragment_doctor, container, false);
        ListView doctorList = (ListView) doctorView.findViewById(R.id.lv_doctor_list);
        return doctorView;
    }
}