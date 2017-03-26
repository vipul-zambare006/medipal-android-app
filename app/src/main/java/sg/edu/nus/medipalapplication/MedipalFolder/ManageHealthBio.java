package sg.edu.nus.medipalapplication.MedipalFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sg.edu.nus.medipalapplication.asynTask.DeleteHealthBio;
import sg.edu.nus.medipalapplication.asynTask.ListHealthBio;
import sg.edu.nus.medipalapplication.asynTask.addHealthBio;
import sg.edu.nus.medipalapplication.database.HealthBioDAO;


/**
 * Created by monalisadebnth on 18/3/17.
 */

public class ManageHealthBio {
    private int numhealth;
    private ArrayList<HealthID> members;

    private addHealthBio taskHealthBioAdd;
    private ListHealthBio taskHealthBioList;
    private DeleteHealthBio taskHealthBioDelete;


    public ManageHealthBio() {
        numhealth = 0;
        members = new ArrayList<HealthID>();
    }

    public HealthID getMember(int memberNum) {
        Iterator<HealthID> i = members.iterator();
        while (i.hasNext()) {
            HealthID m = i.next();
            if (m.getMedicalNumber() == memberNum) {
                return m;
            }
        }
        return null;
    }

    public List<HealthID> getMembers() {
        return new ArrayList<HealthID>(members);
    }


    public HealthID addMember(String condition, String startdate, String conditiontype, int memberNum) {
        numhealth++;
        HealthID m = new HealthID(condition, startdate, conditiontype, numhealth);
        members.add(m);
        return m;
    }

    // SQLite Add HealthID Bio
    public HealthID addMember(String condition, String startdate,
                              String conditiontype, Context context) {
        HealthID m = new HealthID(condition, startdate, conditiontype);

        taskHealthBioAdd = new addHealthBio(context);
        //taskHealthBioAdd.execute(m);
        return m;
    }

    public void removeMember(int memberNum) {
        HealthID m = getMember(memberNum);
        if (m != null) {
            members.remove(m);
        }
    }

    // SQLite Remove HealthID Bio
    public HealthID removeMember(int memberNum, Context context) {
        HealthID m = new HealthID(memberNum);

        taskHealthBioDelete = new DeleteHealthBio(context);
        taskHealthBioDelete.execute(m);
        return m;
    }

    public void showMembers() {
        Iterator<HealthID> i = members.iterator();
        while (i.hasNext()) {
            i.next().show();
        }
    }

    public List<HealthID> getMembers(Context context)
    {
        HealthBioDAO healthBioDAO = new HealthBioDAO(context);
        return healthBioDAO.getMembers();
    }
}
