package sg.edu.nus.medipalapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Temperature;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.TemperatureDisplayEditUpdateActivity;

/**
 * Created by USER on 23/3/2017.
 */

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.temperatureHolder> {

    private ArrayList<Temperature> temperaturelist = new ArrayList<>();
    private Context context;

    public TemperatureAdapter(Context context, ArrayList<Temperature> temperaturelist) {
        this.temperaturelist = temperaturelist;
        this.context = context;
    }

    @Override
    public temperatureHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperatureviewlayout, parent, false);
        temperatureHolder recyclerViewHolders = new temperatureHolder(layoutview);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(temperatureHolder holder, int position) {
        holder.temperature.setText(String.valueOf((int) temperaturelist.get(position).getTemperature()));
        holder.measuredOn.setText(temperaturelist.get(position).getMeasuredOn());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TemperatureDisplayEditUpdateActivity.class);
                intent.putExtra("measurementId", temperaturelist.get(position).getMeasurementId());
                intent.putExtra("temperature", temperaturelist.get(position).getTemperature());
                intent.putExtra("MeasuredOn", temperaturelist.get(position).getMeasuredOn());
                context.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != temperaturelist ? temperaturelist.size() : 0);
    }

    class temperatureHolder extends RecyclerView.ViewHolder {
        TextView temperature, measuredOn;
        View container;

        public temperatureHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.temperaturecardview);
            temperature = (TextView) itemView.findViewById(R.id.Texttemperature);
            measuredOn = (TextView) itemView.findViewById(R.id.TextmeasuredOn); //Need to think logic to spilt the date and time to display in rycler view


        }
    }


}
