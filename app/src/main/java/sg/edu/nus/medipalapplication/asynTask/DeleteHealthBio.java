package sg.edu.nus.medipalapplication.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthID;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class DeleteHealthBio extends AsyncTask<HealthID, Void, Integer> {

    HealthID member = null;
    private HealthBioDAO memberDAO;

    public DeleteHealthBio(Context context) {
        this.memberDAO = new HealthBioDAO(context);
    }

    @Override
    protected Integer doInBackground(HealthID... params) {
        int result = memberDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)

            if (memberDAO != null)
                memberDAO.close();
    }
}
