package sg.edu.nus.medipalapplication.MedipalFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by USER on 19/3/2017.
 */

public class Measurement {

    private int measurementId;
    private String measuredOn;

    public Measurement() {
    }

    public Measurement(int measurementId, String measuredOn) {
        this.measurementId = measurementId;
        this.measuredOn = measuredOn;
    }

    public int getMeasurementId() {

        return measurementId;
    }

    public String getMeasuredOn() {
        return measuredOn;
    }

    public String toString() {


        return null;
    }

    public void addMeasurement(Measurement measurement, Context context) {

    }

    public ArrayList showDisplayMeasurements(Context context) {
        ArrayList<Objects> arrayList = new ArrayList<Objects>();
        return arrayList;
    }

    public void updateMeasurement(Measurement measurement, Context context) {

    }
}
