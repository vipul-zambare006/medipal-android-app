package sg.edu.nus.medipalapplication.fragment;

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
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.EmergencyContact;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddNOKActivity;
import sg.edu.nus.medipalapplication.adapter.NOKAdapter;
import sg.edu.nus.medipalapplication.database.EmergencyContactDAO;

/**
 * Created by Rach on 18/3/2017.
 */

public class NOKFragment extends Fragment {

    private RecyclerView recyclerView;
    private NOKAdapter adapter;
    private ArrayList<EmergencyContact> emergencyContactArrayList;
    EmergencyContact emergencyContact = new EmergencyContact();
    private EmergencyContactDAO emergencyContactDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        emergencyContactDAO=new EmergencyContactDAO(getContext());
        loadContact(emergencyContactDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View nokView = inflater.inflate(R.layout.fragment_nok, container, false);

        recyclerView = (RecyclerView) nokView.findViewById(R.id.recyle_view);

        final EmergencyContactDAO emergencyContactDAO;
        emergencyContactDAO = new EmergencyContactDAO(getContext());

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) nokView.findViewById(R.id.fab_add_nok);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadContact(emergencyContactDAO);
        adapter = new NOKAdapter(getActivity(), emergencyContactArrayList);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddNOKActivity.class);
                intent.putExtra("id", 0);
                intent.putExtra("name", 0);
                intent.putExtra("number", 0);
                intent.putExtra("desc", 0);
                intent.putExtra("preference", 0);
                intent.putExtra("action","add");

                getActivity().startActivity(intent);
            }
        });
        return nokView;
    }

    public ArrayList<EmergencyContact> loadContact(EmergencyContactDAO emergencyContactDAO) {
        emergencyContactArrayList = new ArrayList<EmergencyContact>();
        Cursor cursor = emergencyContact.getNOKContacts(emergencyContactDAO);

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

    @Override public void onResume() {
        super.onResume();
        adapter.loadNOK(emergencyContactDAO);
    }
}
