package sg.edu.nus.medipalapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import dao.MedicalDAO;
import medicalFolder.MedicalID;


/**
 * Created by monalisadebnth on 19/3/17.
 */

public class DeleteMedical extends AsyncTask<MedicalID, Void, Integer> {

    MedicalID member = null;
    private MedicalDAO memberDAO;

    public DeleteMedical(Context context) {
        this.memberDAO = new MedicalDAO(context);
    }

    @Override
    protected Integer doInBackground(MedicalID... params) {
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