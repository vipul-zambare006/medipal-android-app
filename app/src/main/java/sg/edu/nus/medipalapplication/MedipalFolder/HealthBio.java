package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class HealthBio {
    private String condition;
    private String startdate;
    private String conditiontype;
    private int memberNum;

    public HealthBio(String condition, String startdate, String conditiontype) {
        this.condition = condition;
        this.startdate = startdate;
        this.conditiontype = conditiontype;
    }

    public HealthBio(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getCondition() {
        return condition;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getConditiontype() {
        return conditiontype;
    }

    public boolean equals(Object medical) {
        if (medical instanceof HealthBio) {
            HealthBio m = (HealthBio) medical;
            if (this.getCondition().equals(m.getCondition())
                    && this.getConditiontype().equals(m.getConditiontype()))
                return true;
        }
        return false;
    }

    public void show () {
        System.out.println (this);
    }
}
