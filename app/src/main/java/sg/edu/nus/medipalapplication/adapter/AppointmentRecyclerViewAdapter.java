package sg.edu.nus.medipalapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.List;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.EditAppointmentActivity;

/**
 * Created by DELL on 3/19/2017.
 */

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.ViewHolder> {

    private List<Appointment> appointments;
    private Dictionary<Integer, Appointment> appointmentDictionary;
    private Activity activity;

    public AppointmentRecyclerViewAdapter(Activity activity, List<Appointment> appointments) {
        this.appointments = appointments;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppointmentRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        viewHolder.location.setText(appointments.get(position).getLocation());
        viewHolder.description.setText(appointments.get(position).getDescription());
        viewHolder.appointmentDateTime.setText(appointments.get(position).getAppointmentDateTime());

        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    private void setDataToView(TextView txtAptLocation, TextView txtAptDescription, TextView txtAptDateTime, int position) {
        txtAptLocation.setText(appointments.get(position).getLocation());
        txtAptDescription.setText(appointments.get(position).getDescription());
        txtAptDateTime.setText(appointments.get(position).getAppointmentDateTime());
    }

    @Override
    public int getItemCount() {
        return (null != appointments ? appointments.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,EditAppointmentActivity.class);

                intent.putExtra("Id",appointments.get(position).getId());
                intent.putExtra("location",appointments.get(position).getLocation());
                intent.putExtra("datetime",appointments.get(position).getAppointmentDateTime());
                intent.putExtra("description",appointments.get(position).getDescription());

                activity.startActivity(intent);
            }
        };
    }

    private void removeItem(Appointment appointmentsData) {

        int currPosition = appointments.indexOf(appointmentsData);
        appointments.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    /**
     * View holder to display each RecylerView item
     */
    protected class ViewHolder extends RecyclerView.ViewHolder {
        //  private ImageView imageView;
        private TextView appointmentDateTime;
        private TextView location;
        private TextView description;
        private View container;

        public ViewHolder(View view) {
            super(view);

            view.setLongClickable(true);

            container = view.findViewById(R.id.card_view);
            location = (TextView) view.findViewById(R.id.txtAptLocation);
            description = (TextView) view.findViewById(R.id.txtAptDescription);
            appointmentDateTime = (TextView) view.findViewById(R.id.txtAptDateTime);
        }
    }
}
