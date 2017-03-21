package sg.edu.nus.medipalapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.edu.nus.medipalapplication.R;

/**
 * Created by Rach on 18/3/2017.
 */

public class ConsumptionHistory extends ListFragment {

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consumptionhistory, container, false);

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}