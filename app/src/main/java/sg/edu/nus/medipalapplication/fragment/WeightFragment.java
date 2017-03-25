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
import sg.edu.nus.medipalapplication.MedipalFolder.Weight;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddWeightActivity;
import sg.edu.nus.medipalapplication.adapter.WeightAdapter;

public class WeightFragment extends Fragment {

    FloatingActionButton addWeight;
    RecyclerView weightRecycleView;
    WeightAdapter weightAdapter;
    ArrayList<Weight> weightitem = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        addWeight = (FloatingActionButton) view.findViewById(R.id.addWeight);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWeightActivity.class);
                startActivity(intent);
            }
        });

        weightRecycleView = (RecyclerView) view.findViewById(R.id.weightrecycleviewid);
        weightRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        weightRecycleView.setItemAnimator(new DefaultItemAnimator());
        Measurement showweight = new Weight();
        weightitem = showweight.showDisplayMeasurements(getActivity().getApplicationContext());

        if (!(weightitem.size() < 1)) {

            weightAdapter = new WeightAdapter(getActivity(), weightitem);
            weightRecycleView.setAdapter(weightAdapter);
        }
        return view;
    }

}
