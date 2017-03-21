package sg.edu.nus.medipalapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.R;

/**
 * Created by Rach on 18/3/2017.
 */

public class MedicationFragment extends Fragment {

    TextView medicineText, categoryText;
    RadioGroup radioSort;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_medication, container, false);
        radioSort = (RadioGroup) view.findViewById(R.id.radioSort);
        radioSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup button, int checkedID) {
                switch (checkedID) {
                    case R.id.radioMedicine:
                        medicineText = (TextView) view.findViewById(R.id.tv_sortChangeText);
                        medicineText.setText("Sort by Medicine");
                        break;
                    case R.id.radioCategory:
                        categoryText = (TextView) view.findViewById(R.id.tv_sortChangeText);
                        medicineText.setText("Sort by Category");
                        break;
                }
            }
        });

        return view;
    }
}

