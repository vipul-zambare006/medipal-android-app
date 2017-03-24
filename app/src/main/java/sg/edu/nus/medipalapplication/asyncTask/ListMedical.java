package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBioID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class ListMedical extends AsyncTask<Void, Void, ArrayList<HealthBioID>> {
    //private final WeakReference<Activity> activityWeakRef;

    ArrayList<HealthBioID> members;
    private HealthBioDAO healthBioDAO;

    public ListMedical(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context);
        this.healthBioDAO = new HealthBioDAO(context);
    }

    @Override
    protected ArrayList<HealthBioID> doInBackground(Void... arg0) {
        ArrayList<HealthBioID> memberList = healthBioDAO.getMembers();
        return memberList;
    }

    @Override
    protected void onPostExecute(ArrayList<HealthBioID> memList) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
        Log.d("members", memList.toString());
        members = memList;

        if (memList == null) {
            members = new ArrayList<HealthBioID>();
        }

    }

    public ArrayList<HealthBioID> getMemberList() {
        return this.members;
    }
}
