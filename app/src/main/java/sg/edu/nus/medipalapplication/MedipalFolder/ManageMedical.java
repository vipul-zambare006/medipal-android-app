package sg.edu.nus.medipalapplication.MedipalFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;


import asynTask.DeleteMedical;
import asynTask.ListMedical;
import asynTask.addMedical;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class ManageMedical {
    private int numContacts;
    private ArrayList<MedicalID> members;

    private addMedical taskMedicalAdd;
    private ListMedical taskMedicalList;
    private DeleteMedical taskMedicalDelete;

    public ManageMedical() {
        numContacts = 0;
        members = new ArrayList<MedicalID>();
    }

    public MedicalID getMember(int memberNum) {
        Iterator<MedicalID> i = members.iterator();
        while (i.hasNext()) {
            MedicalID m = i.next();
            if (m.getMedicalNumber() == memberNum) {
                return m;
            }
        }
        return null;
    }

    public List<MedicalID> getMembers(Context context) {
        // SQLite - Start
        taskMedicalList = new ListMedical(context);
        taskMedicalList.execute((Void) null);
        try {
            members = taskMedicalList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (members == null) {
            members = new ArrayList<MedicalID>();
        }
        // SQLite - End

        return new ArrayList<MedicalID>(members);
    }

    public MedicalID addMember(String condition, String startdate, String conditiontype) {
        numContacts++;
        MedicalID m = new MedicalID(condition, startdate, conditiontype);
        members.add(m);
        return m;
    }

    // SQLite
    public MedicalID addMember (String condition, String startdate,
                                String conditiontype, Context context) {
        MedicalID m = new MedicalID (condition, startdate, conditiontype);

        taskMedicalAdd = new addMedical(context);
        taskMedicalAdd.execute(m);
        return m;
    }

    public void removeMember ( MedicalID member, Context context) {
        //MedicalID m = getMember (member);
        if (member != null) {

            taskMedicalDelete = new DeleteMedical(context);
            taskMedicalDelete.execute(member);
            members.remove (member);
        }
    }

    public void showContacts () {
        Iterator<MedicalID> i = members.iterator ();
        while (i.hasNext ()) {
            i.next().show ();
        }
    }

}


