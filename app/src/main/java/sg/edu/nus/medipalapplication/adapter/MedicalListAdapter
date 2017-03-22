package sg.edu.nus.medipalapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import application.App;
import medicalFolder.MedicalID;

public class MedicalListAdapter extends ArrayAdapter<MedicalID> {

    private Context context;
    private List<MedicalID> members = new ArrayList<>();
    private int medicalID;

    public MedicalListAdapter(Context context) {
        super(context, R.layout.medical_row_layout);
        this.context = context;
        refreshMembers();
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.medical_row_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.btnRemove = (Button) convertView.findViewById(R.id.btn_remove);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MedicalID member = members.get(position);
        viewHolder.tvName.setText(member.toString());
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                App.club.removeMember(member, getContext());
                refreshMembers();
            }
        });
        return convertView;
    }

    public void refreshMembers() {
        members.clear();
        members.addAll(App.club.getMembers(this.context));
        notifyDataSetChanged();
    }

    @Override public int getCount() {
        return members.size();
    }

    static class ViewHolder {
        TextView tvName;
        Button btnRemove;
    }

}