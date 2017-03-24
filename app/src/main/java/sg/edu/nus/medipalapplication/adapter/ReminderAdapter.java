package sg.edu.nus.medipalapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Reminder;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.ReminderEdit;

/**
 * Created by Gaurav on 22-03-2017.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderHolder> {

    private ArrayList<Reminder> reminderlist;
    private Context context;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminderlist) {
        this.context = context;
        this.reminderlist = reminderlist;
    }

    @Override
    public ReminderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View reminderview = LayoutInflater.from(parent.getContext()).inflate(R.layout.remindercardview, parent, false);
        ReminderHolder reminderHolder = new ReminderHolder(reminderview);
        return reminderHolder;
    }

    @Override
    public void onBindViewHolder(ReminderHolder holder, int position) {
        holder.timePicker.setText(String.valueOf(reminderlist.get(position).getStartTime()));
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReminderEdit.class);
                intent.putExtra("Id", reminderlist.get(position).getId());
                intent.putExtra("Reminder Frequency", reminderlist.get(position).getFrequency());
                intent.putExtra("Reminder Interval", reminderlist.get(position).getInterval());
                intent.putExtra("Reminder Start Time", reminderlist.get(position).getStartTime());
                context.startActivity(intent);

            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != reminderlist ? reminderlist.size() : 0);
    }

    class ReminderHolder extends RecyclerView.ViewHolder {

        TextView timePicker;
        View container;


        public ReminderHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.remindercardview);
            timePicker = (TextView) itemView.findViewById(R.id.timePicker);
        }
    }

}

