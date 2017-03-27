package sg.edu.nus.medipalapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Pulse;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.PulseDisplayEditUpdateActivity;

/**
 * Created by Mahesh Inna Kedage on 23/3/2017.
 */

public class PulseAdapter extends RecyclerView.Adapter<PulseAdapter.pulseHolder> {

    private ArrayList<Pulse> pulselist = new ArrayList<>();
    private Context context;

    public PulseAdapter(Context context, ArrayList<Pulse> pulselist) {
        this.pulselist = pulselist;
        this.context = context;
    }

    @Override
    public pulseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.pulseviewlayout, parent, false);
        pulseHolder recyclerViewHolders = new pulseHolder(layoutview);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(pulseHolder holder, int position) {
        holder.pulse.setText(String.valueOf(pulselist.get(position).getPulse()));
        holder.measuredOn.setText(pulselist.get(position).getMeasuredOn());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PulseDisplayEditUpdateActivity.class);
                intent.putExtra("measurementId", pulselist.get(position).getMeasurementId());
                intent.putExtra("pulse", pulselist.get(position).getPulse());
                intent.putExtra("MeasuredOn", pulselist.get(position).getMeasuredOn());
                context.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != pulselist ? pulselist.size() : 0);
    }

    class pulseHolder extends RecyclerView.ViewHolder {
        TextView pulse, measuredOn;
        View container;

        public pulseHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.pulsecardview);
            pulse = (TextView) itemView.findViewById(R.id.Textpulse);
            measuredOn = (TextView) itemView.findViewById(R.id.TextmeasuredOn); //Need to think logic to spilt the date and time to display in rycler view


        }
    }


}

