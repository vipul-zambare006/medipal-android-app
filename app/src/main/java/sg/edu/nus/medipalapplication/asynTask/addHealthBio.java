package sg.edu.nus.medipalapplication.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class addHealthBio extends AsyncTask<HealthID, Void, Long> {

    //private final WeakReference<Activity> activityWeakRef;

    HealthID member = null;
    private HealthBioDAO memberDAO;

    public addHealthBio(Context context) {
        // this.activityWeakRef = new WeakReference<Activity>(context.getApplicationContext());
        this.memberDAO = new HealthBioDAO(context);
    }

    @Override
    protected Long doInBackground(HealthID... params) {
        long result = memberDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
        if (result != -1)
            //Toast.makeText(context, "Member Saved", Toast.LENGTH_LONG).show();

            if (memberDAO != null)
                memberDAO.close();
        //}
    }
}
