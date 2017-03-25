package sg.edu.nus.medipalapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.EmergencyContact;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.AddNOKActivity;
import sg.edu.nus.medipalapplication.database.EmergencyContactDAO;

/**
 * Created by Rach on 24/3/2017.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private ArrayList<EmergencyContact> emergencyContactList;
    private Activity activity;

    public DoctorAdapter(Activity activity, ArrayList<EmergencyContact> emergencyContactList) {
        this.emergencyContactList = emergencyContactList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_view_nok, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.ViewHolder viewHolder, int position) {

        viewHolder.name.setText(emergencyContactList.get(position).getName());
        viewHolder.number.setText(String.valueOf(emergencyContactList.get(position).getNumber()));
        viewHolder.description.setText(emergencyContactList.get(position).getDesc());

        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public int getItemCount() {
        return (null != emergencyContactList ? emergencyContactList.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddNOKActivity.class);

                intent.putExtra("Id", emergencyContactList.get(position).getId());
                intent.putExtra("name", emergencyContactList.get(position).getName());
                intent.putExtra("number", emergencyContactList.get(position).getNumber());
                intent.putExtra("description", emergencyContactList.get(position).getDesc());
                intent.putExtra("preference", emergencyContactList.get(position).getPreference());

                activity.startActivity(intent);
            }
        };
    }


    public void loadDoctor(EmergencyContactDAO emergencyContactDAO) {

        emergencyContactList = new ArrayList<EmergencyContact>();
        EmergencyContact emergencyContact = new EmergencyContact();
        Cursor cursor = emergencyContact.getDoctorContacts(emergencyContactDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String contactType = cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            emergencyContact = new EmergencyContact(id, name, number, contactType, description, preference);
            emergencyContactList.add(emergencyContact);
        }


        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;
        private TextView description;
        private View container;

        public ViewHolder(View view) {
            super(view);

            view.setLongClickable(true);

            container = view.findViewById(R.id.card_view_nok);

            name = (TextView) view.findViewById(R.id.tv_name);
            number = (TextView) view.findViewById(R.id.tv_number);
            description = (TextView) view.findViewById(R.id.tv_description);
        }
    }

//    public void loadDoctor(EmergencyContactDAO emergencyContactDAO) {
//
//        emergencyContactList = new ArrayList<EmergencyContact>();
//        EmergencyContact emergencyContact = new EmergencyContact();
//        Cursor cursor = emergencyContact.getDoctorContacts(emergencyContactDAO);
//
//        while (cursor.moveToNext()) {
//
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String number = cursor.getString(2);
//            String contactType = cursor.getString(3);
//            String description = cursor.getString(4);
//            String preference = cursor.getString(5);
//
//
//            emergencyContact = new EmergencyContact(id, name, number, contactType, description, preference);
//            emergencyContactList.add(emergencyContact);
//        }
//        notifyDataSetChanged();
//    }


}

