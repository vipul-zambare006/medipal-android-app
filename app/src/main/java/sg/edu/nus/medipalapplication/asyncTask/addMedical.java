package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.MedicalID;
import sg.edu.nus.medipalapplication.database.MedicineDAO;

/**
 * Created by monalisadebnth on 19/3/17.
 */

public class addMedical extends AsyncTask<MedicalID, Void, Long> {

    //private final WeakReference<Activity> activityWeakRef;

    MedicalID member = null;
    private MedicineDAO memberDAO;

    public addMedical(Context context) {
        //this.activityWeakRef = new WeakReference<Activity>(context.getApplicationContext());
        this.memberDAO = new MedicineDAO(context);
    }

    @Override
    protected Long doInBackground(MedicalID... params) {
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