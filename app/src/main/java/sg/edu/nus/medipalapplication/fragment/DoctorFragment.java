package sg.edu.nus.medipalapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

import sg.edu.nus.medipalapplication.MedipalFolder.EmergencyContact;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddDoctorActivity;
import sg.edu.nus.medipalapplication.activity.SaveAppointmentActivity;
import sg.edu.nus.medipalapplication.adapter.DoctorAdapter;
import sg.edu.nus.medipalapplication.database.EmergencyContactDAO;


/**
 * Created by Rach on 18/3/2017.
 */

public class DoctorFragment extends Fragment {

    EmergencyContact emergencyContact = new EmergencyContact();
   // private TextView tvEmpty;
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private ArrayList<EmergencyContact> emergencyContactArrayList;
    private EmergencyContactDAO emergencyContactDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emergencyContactDAO=new EmergencyContactDAO(getContext());
        loadDoctor(emergencyContactDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View doctorView = inflater.inflate(R.layout.fragment_doctor, container, false);

        recyclerView = (RecyclerView)doctorView.findViewById(R.id.recyle_view_doctor);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) doctorView.findViewById(R.id.fab_add_doctor);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadDoctor(emergencyContactDAO);
        adapter=new DoctorAdapter(getActivity(),emergencyContactArrayList);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddDoctorActivity.class);

                intent.putExtra("Id",0);
                intent.putExtra("name","");
                intent.putExtra("number","");
                intent.putExtra("desc","");
                intent.putExtra("pref","");
                intent.putExtra("action","add");

                startActivity(intent);

                //getActivity().startActivity(new Intent(getActivity(), AddDoctorActivity.class));
            }
        });
        return doctorView;
    }

    public ArrayList<EmergencyContact> loadDoctor(EmergencyContactDAO emergencyContactDAO) {
        emergencyContactArrayList = new ArrayList<EmergencyContact>();
        Cursor cursor = emergencyContact.getDoctorContacts(emergencyContactDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number= cursor.getString(2);
            String contactType= cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            EmergencyContact emergencyContact  = new EmergencyContact(id,name, number,contactType,description,preference);
            emergencyContactArrayList.add(emergencyContact);
        }
        return emergencyContactArrayList;
    }

    private View.OnClickListener onAddingListener(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SaveAppointmentActivity.class);

                intent.putExtra("Id",0);
                intent.putExtra("location","");
                intent.putExtra("date","");
                intent.putExtra("time","");
                intent.putExtra("description","");
                intent.putExtra("action","add");

                activity.startActivity(intent);
            }
        };
    }

    @Override public void onResume() {
        super.onResume();
        adapter.loadDoctor(emergencyContactDAO);
        //tvEmpty.setVisibility(memberListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }

}

