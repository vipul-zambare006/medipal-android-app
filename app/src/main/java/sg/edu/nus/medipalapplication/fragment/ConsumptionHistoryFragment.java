package sg.edu.nus.medipalapplication.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.adapter.MedicineAdapter;
import sg.edu.nus.medipalapplication.database.MedicineDAO;

/**
 * Created by Rach on 18/3/2017.
 */

public class ConsumptionHistoryFragment extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;
    private ArrayList<Medicine> medicineArrayList;
    Medicine medicine=new Medicine();
    private MedicineDAO medicineDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //medicineDAO=new MedicineDAO(getContext());
        //loadMedicine(medicineDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consumptionhistory, container, false);
        /*recyclerView = (RecyclerView)view.findViewById(R.id.recyle_view_consumptionhistory);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadMedicine(medicineDAO);
        medicineAdapter=new MedicineAdapter(getActivity(),medicineArrayList);
        recyclerView.setAdapter(medicineAdapter);*/

        return view;

    }

    public ArrayList<Medicine> loadMedicine(MedicineDAO medicineDAO) {
        medicineArrayList = new ArrayList<Medicine>();
        Cursor cursor = medicineDAO.getAllMedicine();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number= cursor.getString(2);
            String contactType= cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            Medicine emergencyContact  = new Medicine();
            medicineArrayList.add(emergencyContact);
        }
        return medicineArrayList;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override public void onResume() {
        super.onResume();
        //medicineAdapter.loadMedicine(medicineDAO);
        //tvEmpty.setVisibility(memberListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }

}