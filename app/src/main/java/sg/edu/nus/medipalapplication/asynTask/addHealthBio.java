package sg.edu.nus.medipalapplication.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class addHealthBio
{
    private HealthBioDAO memberDAO;

    public addHealthBio(Context context) {
        // this.activityWeakRef = new WeakReference<Activity>(context.getApplicationContext());
        this.memberDAO = new HealthBioDAO(context);
    }
}
