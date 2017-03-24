package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class HealthBioID extends HealthBio implements Comparable<HealthBioID> {
    private int medicalID;

    public HealthBioID(String condition, String startdate, String conditiontype, int medicalNumber) {
        super (condition, startdate, conditiontype);
        this.medicalID = medicalID;
    }

    public HealthBioID(String condition, String startdate, String conditiontype) {
        super (condition, startdate, conditiontype);
    }

    public int getMedicalNumber () {
        return medicalID;
    }

    public String toString () {
        return (getCondition().toString()+" " +"\n" +getConditiontype().toString()+" "+"/n" +getStartdate());
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
        HealthBioID other = (HealthBioID) obj;
        super.equals(other);
        return medicalID == other.medicalID;
    }

    // Added so that Members can be sorted by membership number
    public int compareTo(HealthBioID other) {
        return (getMedicalNumber() - other.getMedicalNumber());
    }
}
