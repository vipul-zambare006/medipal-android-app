package sg.edu.nus.medipalapplication.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class ListHealthBio extends AsyncTask<Void, Void, ArrayList<HealthID>> {
    //private final WeakReference<Activity> activityWeakRef;

    ArrayList<HealthID> members;
    private HealthBioDAO memberDAO;

    public ListHealthBio(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context);
        this.memberDAO = new HealthBioDAO(context);
    }

    @Override
    protected ArrayList<HealthID> doInBackground(Void... arg0) {
        ArrayList<HealthID> memberList = memberDAO.getMembers();
        return memberList;
    }

    @Override
    protected void onPostExecute(ArrayList<HealthID> memList) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
        Log.d("members", memList.toString());
        members = memList;

        if (memList == null) {
            members = new ArrayList<HealthID>();
        }

    }

    public ArrayList<HealthID> getMemberList() {
        return this.members;
    }
}
