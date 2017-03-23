package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.MedicalID;
import sg.edu.nus.medipalapplication.database.MedicineDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class ListMedical extends AsyncTask<Void, Void, ArrayList<MedicalID>> {
    //private final WeakReference<Activity> activityWeakRef;

    ArrayList<MedicalID> members;
    private MedicineDAO memberDAO;

    public ListMedical(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context);
        this.memberDAO = new MedicineDAO(context);
    }

    @Override
    protected ArrayList<MedicalID> doInBackground(Void... arg0) {
        ArrayList<MedicalID> memberList = memberDAO.getMembers();
        return memberList;
    }

    @Override
    protected void onPostExecute(ArrayList<MedicalID> memList) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
        Log.d("members", memList.toString());
        members = memList;

        if (memList == null) {
            members = new ArrayList<MedicalID>();
        }

    }

    public ArrayList<MedicalID> getMemberList() {
        return this.members;
    }
}
