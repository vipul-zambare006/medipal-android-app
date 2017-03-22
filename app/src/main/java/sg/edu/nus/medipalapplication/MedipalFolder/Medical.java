package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by monalisadebnth on 18/3/17.
 */

public class Medical {
    private String condition;
    private String startdate;
    private String conditiontype;

    public Medical() {
    }

    public Medical(String condition,
                   String startdate, String conditiontype) {
        this.condition = condition;
        this.startdate = startdate;
        this.conditiontype = conditiontype;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getConditiontype() {
        return conditiontype;
    }

    public void setConditiontype(String conditiontype) {
        this.conditiontype = conditiontype;
    }

    public boolean equals(Object medical) {
        if (medical instanceof Medical) {
            Medical m = (Medical) medical;
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
