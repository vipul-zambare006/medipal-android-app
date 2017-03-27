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
import sg.edu.nus.medipalapplication.MedipalFolder.Temperature;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddTemperatureActivity;
import sg.edu.nus.medipalapplication.adapter.TemperatureAdapter;

public class TemperatureFragment extends Fragment {

    FloatingActionButton addTemperature;
    RecyclerView temperatureRecycleView;
    TemperatureAdapter temperatureAdapter;
    ArrayList<Temperature> temperatureitem = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        addTemperature = (FloatingActionButton) view.findViewById(R.id.addTemperature);
        addTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTemperatureActivity.class);
                startActivity(intent);
            }
        });

        temperatureRecycleView = (RecyclerView) view.findViewById(R.id.temperaturerecycleviewid);
        temperatureRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        temperatureRecycleView.setItemAnimator(new DefaultItemAnimator());
        loadTemperatureOnResume();
        return view;
    }

    private void loadTemperatureOnResume() {
        Measurement showtemperature = new Temperature();
        temperatureitem = showtemperature.showDisplayMeasurements(getActivity().getApplicationContext());

        if (!(temperatureitem.size() < 1)) {

            temperatureAdapter = new TemperatureAdapter(getActivity(), temperatureitem);
            temperatureRecycleView.setAdapter(temperatureAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTemperatureOnResume();
    }

}
