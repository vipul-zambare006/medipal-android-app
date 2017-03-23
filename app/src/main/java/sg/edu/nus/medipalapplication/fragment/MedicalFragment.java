package sg.edu.nus.medipalapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddMedicalActivity;
import sg.edu.nus.medipalapplication.adapter.MedicalListAdapter;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class MedicalFragment extends Fragment {

    private MedicalListAdapter memberListAdapter;
    private TextView tvEmpty;

    public static MedicalFragment newInstance(){
        MedicalFragment fragment = new MedicalFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_medical, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView memberList = (ListView) view.findViewById(R.id.lv_medical_list);
        tvEmpty = (TextView) view.findViewById(R.id.tv_empty_value);
        memberListAdapter = new MedicalListAdapter(getActivity());
        memberList.setAdapter(memberListAdapter);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AddMedicalActivity.class));
            }
        });
    }

    @Override public void onResume() {
        super.onResume();
        memberListAdapter.refreshMembers();
        tvEmpty.setVisibility(memberListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
