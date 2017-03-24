package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBioID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class DeleteMedical extends AsyncTask<HealthBioID, Void, Integer> {

    HealthBioID member = null;
    private HealthBioDAO healthbioDAO;

    public DeleteMedical(Context context) {
        this.healthbioDAO = new HealthBioDAO(context);
    }

    @Override
    protected Integer doInBackground(HealthBioID... params) {
        int result = healthbioDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)

            if (healthbioDAO != null)
                healthbioDAO.close();
    }
}