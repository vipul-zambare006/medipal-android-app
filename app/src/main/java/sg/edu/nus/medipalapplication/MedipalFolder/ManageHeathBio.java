package sg.edu.nus.medipalapplication.MedipalFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sg.edu.nus.medipalapplication.asyncTask.ListMedical;
import sg.edu.nus.medipalapplication.asyncTask.addMedical;


/**
 * Created by monalisadebnth on 18/3/17.
 */

public class ManageHeathBio {
    private int numContacts;
    private ArrayList<HealthBioID> members;

    private addMedical taskMedicalAdd;
    private ListMedical taskMedicalList;

    public ManageHeathBio() {
        numContacts = 0;
        members = new ArrayList<HealthBioID>();
    }

    public HealthBioID getMember(int memberNum) {
        Iterator<HealthBioID> i = members.iterator();
        while (i.hasNext()) {
            HealthBioID m = i.next();
            if (m.getMedicalNumber() == memberNum) {
                return m;
            }
        }
        return null;
    }

    public List<HealthBioID> getMembers(Context context) {
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
            members = new ArrayList<HealthBioID>();
        }
        // SQLite - End

        return new ArrayList<HealthBioID>(members);
    }

    public HealthBioID addMember(String condition, String startdate, String conditiontype) {
        numContacts++;
        HealthBioID m = new HealthBioID(condition, startdate, conditiontype);
        members.add(m);
        return m;
    }

    // SQLite
    public HealthBioID addMember(String condition, String startdate,
                                 String conditiontype, Context context) {
        HealthBioID m = new HealthBioID(condition, startdate, conditiontype);

        taskMedicalAdd = new addMedical(context);
        taskMedicalAdd.execute(m);
        return m;
    }

    public void removeMember(int memberNum) {
        HealthBioID m = getMember(memberNum);
        if (m != null) {
            members.remove(m);
        }
    }

    public void showContacts() {
        Iterator<HealthBioID> i = members.iterator();
        while (i.hasNext()) {
            i.next().show();
        }
    }
/*    public void show () {
        System.out.println ("Current Contacts:");
        showContacts ();
        System.out.println ();
        System.out.println ("Doctors:");
        //showFacilities ();
    }*/
}
