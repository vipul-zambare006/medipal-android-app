package sg.edu.nus.medipalapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import sg.edu.nus.medipalapplication.R;

/**
 * Created by Rach on 18/3/2017.
 */

public class NOKFragment extends Fragment {

    private TextView tvEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View nokView = inflater.inflate(R.layout.fragment_nok, container, false);
        ListView nokList = (ListView) nokView.findViewById(R.id.lv_nok_list);

        tvEmpty = (TextView) nokView.findViewById(R.id.tv_empty_value);


        return nokView;

    }

}
