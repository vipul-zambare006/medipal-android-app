package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBioID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;

/**
 * Created by monalisadebnth on 19/3/17.
 */

public class addMedical extends AsyncTask<HealthBioID, Void, Long> {

    //private final WeakReference<Activity> activityWeakRef;

    HealthBioID member = null;
    private HealthBioDAO healthBioDAO;

    public addMedical(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context.getApplicationContext());
        this.healthBioDAO = new HealthBioDAO(context);
    }

    @Override
    protected Long doInBackground(HealthBioID... params) {
        long result = healthBioDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        //if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()) {
        if (result != -1)
            //Toast.makeText(context, "Member Saved", Toast.LENGTH_LONG).show();

            if (healthBioDAO != null)
                healthBioDAO.close();
        //}
    }
}