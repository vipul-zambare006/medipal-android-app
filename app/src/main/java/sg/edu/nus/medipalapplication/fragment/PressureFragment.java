package sg.edu.nus.medipalapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Measurement;
import sg.edu.nus.medipalapplication.MedipalFolder.Pressure;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddPressureActivity;
import sg.edu.nus.medipalapplication.adapter.PressureAdapter;

public class PressureFragment extends Fragment {

    FloatingActionButton addPressure;
    RecyclerView pressureRecycleView;
    PressureAdapter pressureAdapter;
    ArrayList<Pressure> pressureitem = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pressure, container, false);

        addPressure = (FloatingActionButton) view.findViewById(R.id.addPressure);
        addPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPressureActivity.class);
                startActivity(intent);
            }
        });

        pressureRecycleView = (RecyclerView) view.findViewById(R.id.pressurerecycleviewid);
        pressureRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pressureRecycleView.setItemAnimator(new DefaultItemAnimator());

        loadPressureOnResume();
        return view;
    }

    private void loadPressureOnResume() {
        Measurement showpressure = new Pressure();
        pressureitem = showpressure.showDisplayMeasurements(getActivity().getApplicationContext());

        if (!(pressureitem.size() < 1)) {

            pressureAdapter = new PressureAdapter(getActivity(), pressureitem);
            pressureRecycleView.setAdapter(pressureAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPressureOnResume();
    }
}
