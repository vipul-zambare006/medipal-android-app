package sg.edu.nus.medipalapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.edu.nus.medipalapplication.R;


/**
 * Created by Rach on 18/3/2017.
 */

public class EmergencyFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View emergencyView = inflater.inflate(R.layout.fragment_emergency, container, false);
        return emergencyView;
    }
}