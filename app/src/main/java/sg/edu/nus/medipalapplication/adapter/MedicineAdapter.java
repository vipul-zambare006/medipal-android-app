package sg.edu.nus.medipalapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;
import sg.edu.nus.medipalapplication.R;
import sg.edu.nus.medipalapplication.activity.MedicineEditActivity;

/**
 * Created by Govardhan on 19/3/2017.
 */

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineHolder> {

    private ArrayList<Medicine> medicinelist;
    private Context context;

    public MedicineAdapter(Context context, ArrayList<Medicine> medicinelist) {
        this.medicinelist = medicinelist;
        this.context = context;
    }

    @Override
    public MedicineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View medicineview = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_cardview, parent, false);
        MedicineHolder medicineHolder = new MedicineHolder(medicineview);
        return medicineHolder;
    }

    @Override
    public void onBindViewHolder(MedicineHolder holder, int position) {

        holder.medicinename.setText(medicinelist.get(position).getMedicinename());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MedicineEditActivity.class);

                intent.putExtra("Id", medicinelist.get(position).getId());
                intent.putExtra("MedicineName", medicinelist.get(position).getMedicinename());
                intent.putExtra("MedicineDescription", medicinelist.get(position).getMedicinedescription());
                intent.putExtra("MedicineCatId", medicinelist.get(position).getMedicinecatid());
                intent.putExtra("MedicineReminderId", medicinelist.get(position).getMedicinereminderid());
                intent.putExtra("MedicineRemind", medicinelist.get(position).getMedicineremind());
                intent.putExtra("MedicineQuantity", medicinelist.get(position).getMedicinequantity());
                intent.putExtra("MedicineDosage", medicinelist.get(position).getMedicinedosage());
                intent.putExtra("MedicineDateIssued", medicinelist.get(position).getMedicinedataissued());
                intent.putExtra("MedicineConsumequantity", medicinelist.get(position).getMedicineconsumequantity());
                intent.putExtra("MedicineThreshold", medicinelist.get(position).getMedicinethreshold());
                intent.putExtra("MedicineExpire", medicinelist.get(position).getMedicineexpirefactor());

                context.startActivity(intent);

            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != medicinelist ? medicinelist.size() : 0);
    }

    class MedicineHolder extends RecyclerView.ViewHolder {

        TextView medicinename;
        View container;

        public MedicineHolder(View itemView) {

            super(itemView);

            container = itemView.findViewById(R.id.catergorycardview);
            medicinename = (TextView) itemView.findViewById(R.id.TextMedicineName);

        }


    }
}
