package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class HealthID extends HealthBio implements Comparable<HealthID> {
    private int medicalID;

    public HealthID(String condition, String startdate, String conditiontype, int memberNum) {
        super(condition, startdate, conditiontype);
        this.medicalID = medicalID;
    }

    public HealthID(String condition, String startdate, String conditiontype) {
        super(condition, startdate, conditiontype);
    }

    public HealthID(int memberNum) {
        super(memberNum);
    }


    public int getMedicalNumber() {
        return medicalID;
    }

    public String toString() {
        return (getCondition().toString() + " " + "\n" + getConditiontype().toString() + " " + "/n" + getStartdate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + medicalID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        HealthID other = (HealthID) obj;
        super.equals(other);
        return medicalID == other.medicalID;
    }

    // Added so that Members can be sorted by membership number
    public int compareTo(HealthID other) {
        return (getMedicalNumber() - other.getMedicalNumber());
    }
}
