package sg.edu.nus.medipalapplication.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.R;

/**
 * Created by Rach on 3/25/2017.
 */

public class ConsumptionAdapter extends RecyclerView.Adapter<ConsumptionAdapter.ViewHolder> {

    private List<Medicine> medicineList;
    private Activity activity;

    public ConsumptionAdapter(Activity activity, List<Medicine> medicines) {
        this.medicineList = medicines;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_consumption, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ConsumptionAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtMedicineName.setText(medicineList.get(position).getMedicinename());
        viewHolder.txtQuantityConsumed.setText(medicineList.get(position).getMedicinedescription());

    }

    @Override
    public int getItemCount() {
        return (null != medicineList ? medicineList.size() : 0);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMedicineName;
        private TextView txtQuantityConsumed;
        private TextView txtMissedQuantity;
        private View container;

        public ViewHolder(View view) {
            super(view);

            container = view.findViewById(R.id.card_view);
            txtMedicineName = (TextView) view.findViewById(R.id.txtMedicineName);
            txtQuantityConsumed = (TextView) view.findViewById(R.id.txtQuantityConsumed);
            //txtMissedQuantity = (TextView) view.findViewById(R.id.txtMissedQuantity);
        }
    }
}
