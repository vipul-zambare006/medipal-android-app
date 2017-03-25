package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by Govardhan on 19/3/2017.
 */

public class Medicine {
    private String medicinename, medicinedescription, medicinecatid, medicineremind, medicinequantity, medicinedosage, medicinedataissued, medicineconsumequantity, medicinethreshold, medicineexpirefactor;
    private int id, medicinereminderid;

    public Medicine(){};
    public Medicine(int id, String medicinename, String medicinedescription, String medicinecatid, String medicinereminderid, String medicineremind, String medicinequantity, String medicinedosage, String medicinedateissued, String medicineconsumequantity, String medicienthreshold) {

    }

    public Medicine(int id, String medicinename, String medicinedescription, String medicinecatid, int medicinereminderid, String medicineremind, String medicinequantity, String medicinedosage, String medicinedataissued, String medicineconsumequantity, String medicinethreshold, String medicineexpirefactor) {

        this.medicinename = medicinename;
        this.medicinedescription = medicinedescription;
        this.medicinecatid = medicinecatid;
        this.medicinereminderid = medicinereminderid;
        this.medicineremind = medicineremind;
        this.medicinequantity = medicinequantity;
        this.medicinedosage = medicinedosage;
        this.medicinedataissued = medicinedataissued;
        this.medicineconsumequantity = medicineconsumequantity;
        this.medicinethreshold = medicinethreshold;
        this.medicineexpirefactor = medicineexpirefactor;
        this.id = id;
    }


    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicinedescription() {
        return medicinedescription;
    }

    public void setMedicinedescription(String medicinedescription) {
        this.medicinedescription = medicinedescription;
    }

    public String getMedicinecatid() {
        return medicinecatid;
    }

    public void setMedicinecatid(String medicinecatid) {
        this.medicinecatid = medicinecatid;
    }

    public int getMedicinereminderid() {
        return medicinereminderid;
    }

    public void setMedicinereminderid(int medicinereminderid) {
        this.medicinereminderid = medicinereminderid;
    }

    public String getMedicineremind() {
        return medicineremind;
    }

    public void setMedicineremind(String medicineremind) {
        this.medicineremind = medicineremind;
    }

    public String getMedicinequantity() {
        return medicinequantity;
    }

    public void setMedicinequantity(String medicinequantity) {
        this.medicinequantity = medicinequantity;
    }

    public String getMedicinedosage() {
        return medicinedosage;
    }

    public void setMedicinedosage(String medicinedosage) {
        this.medicinedosage = medicinedosage;
    }

    public String getMedicinedataissued() {
        return medicinedataissued;
    }

    public void setMedicinedataissued(String medicinedataissued) {
        this.medicinedataissued = medicinedataissued;
    }

    public String getMedicineconsumequantity() {
        return medicineconsumequantity;
    }

    public void setMedicineconsumequantity(String medicineconsumequantity) {
        this.medicineconsumequantity = medicineconsumequantity;
    }

    public String getMedicinethreshold() {
        return medicinethreshold;
    }

    public void setMedicinethreshold(String medicinethreshold) {
        this.medicinethreshold = medicinethreshold;
    }

    public String getMedicineexpirefactor() {
        return medicineexpirefactor;
    }

    public void setMedicineexpirefactor(String medicineexpirefactor) {
        this.medicineexpirefactor = medicineexpirefactor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}