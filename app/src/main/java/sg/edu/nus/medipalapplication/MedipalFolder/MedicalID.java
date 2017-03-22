package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class MedicalID extends Medical implements Comparable<MedicalID> {
    private int medicalID;

    public MedicalID (String condition, String startdate, String conditiontype, int medicalNumber) {
        super (condition, startdate, conditiontype);
        this.medicalID = medicalID;
    }

    public MedicalID (String condition, String startdate, String conditiontype) {
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
        MedicalID other = (MedicalID) obj;
        super.equals(other);
        if (medicalID != other.medicalID)
            return false;
        return true;
    }

    // Added so that Members can be sorted by membership number
    public int compareTo (MedicalID other) {
        return (getMedicalNumber() - other.getMedicalNumber());
    }
}
