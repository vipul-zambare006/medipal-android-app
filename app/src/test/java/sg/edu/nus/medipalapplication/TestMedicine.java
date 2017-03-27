package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.Medicine;

/**
 * Created by Mahesh Inna Kedage on 27/3/2017.
 */

public class TestMedicine extends TestCase {
    private Medicine medicine1 = new Medicine(1, "carpol", "for Fever", "1", 3, "Y", "25", "1", "25 Mar 2017", "10", "3", "24");
    private Medicine medicine2 = new Medicine(2, "pantacid", "for gastric", "2", 4, "Y", "30", "1", "10 Feb 2017", "20", "3", "24");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void Testmedicine() {
        assertEquals(1, medicine1.getId());
        assertEquals("carpol", medicine1.getMedicinename());
        assertEquals("for Fever", medicine1.getMedicinedescription());
        assertEquals("1", medicine1.getMedicinecatid());
        assertEquals(3, medicine1.getMedicinereminderid());
        assertEquals("Y", medicine1.getMedicineremind());
        assertEquals("25", medicine1.getMedicinequantity());
        assertEquals("1", medicine1.getMedicinedosage());
        assertEquals("25 Mar 2017", medicine1.getMedicinedataissued());
        assertEquals("10", medicine1.getMedicineconsumequantity());
        assertEquals("3", medicine1.getMedicinethreshold());
        assertEquals("24", medicine1.getMedicineexpirefactor());

        assertEquals(2, medicine2.getId());
        assertEquals("pantacid", medicine2.getMedicinename());
        assertEquals("for gastric", medicine2.getMedicinedescription());
        assertEquals("2", medicine2.getMedicinecatid());
        assertEquals(4, medicine2.getMedicinereminderid());
        assertEquals("Y", medicine2.getMedicineremind());
        assertEquals("30", medicine2.getMedicinequantity());
        assertEquals("1", medicine2.getMedicinedosage());
        assertEquals("10 Feb 2017", medicine2.getMedicinedataissued());
        assertEquals("20", medicine2.getMedicineconsumequantity());
        assertEquals("3", medicine2.getMedicinethreshold());
        assertEquals("24", medicine2.getMedicineexpirefactor());
    }

    public void TestGetId() {
        assertEquals(1, medicine1.getId());
        assertEquals(2, medicine2.getId());
    }

    public void TestGetMedicinename() {
        assertEquals("carpol", medicine1.getMedicinename());
        assertEquals("pantacid", medicine2.getMedicinename());
    }

    public void TestGetMedicinedescription() {
        assertEquals("for Fever", medicine1.getMedicinedescription());
        assertEquals("for gastric", medicine2.getMedicinedescription());
    }

    public void TestGetMedicinecatid() {
        assertEquals("1", medicine1.getMedicinecatid());
        assertEquals("2", medicine2.getMedicinecatid());

    }

    public void TestGetMedicinereminderid() {
        assertEquals(3, medicine1.getMedicinereminderid());
        assertEquals(4, medicine2.getMedicinereminderid());
    }

    public void TestGetMedicineremind() {
        assertEquals("Y", medicine1.getMedicineremind());
        assertEquals("Y", medicine2.getMedicineremind());

    }

    public void TestGetMedicinequantity() {
        assertEquals("25", medicine1.getMedicinequantity());
        assertEquals("30", medicine2.getMedicinequantity());

    }

    public void TestGetMedicinedosage() {
        assertEquals("1", medicine1.getMedicinedosage());
        assertEquals("1", medicine2.getMedicinedosage());

    }

    public void TestGetMedicinedataissued() {
        assertEquals("25 Mar 2017", medicine1.getMedicinedataissued());
        assertEquals("10 Feb 2017", medicine2.getMedicinedataissued());

    }

    public void TestGetMedicineconsumequantity() {
        assertEquals("10", medicine1.getMedicineconsumequantity());
        assertEquals("20", medicine2.getMedicineconsumequantity());

    }

    public void TestGetMedicinethreshold() {
        assertEquals("3", medicine1.getMedicinethreshold());
        assertEquals("3", medicine2.getMedicinethreshold());
    }

    public void TestGetMedicineexpirefactor() {
        assertEquals("24", medicine1.getMedicineexpirefactor());
        assertEquals("24", medicine2.getMedicineexpirefactor());
    }

    public void TestEquals() {
        assertEquals(medicine1, new Medicine(1, "carpol", "for Fever", "1", 3, "Y", "25", "1", "25 Mar 2017", "10", "3", "24"));
        assertEquals(medicine2, new Medicine(2, "pantacid", "for gastric", "2", 4, "Y", "30", "1", "10 Feb 2017", "20", "3", "24"));

        assertFalse(medicine1.equals(medicine2));
        assertFalse(medicine2.equals(medicine1));

        Medicine medicine3 = new Medicine(3, "sinarest", "for cold", "3", 5, "N", "10", "2", "15 Jan 2017", "5", "2", "24");
        assertFalse(medicine1.equals(medicine3));
        assertFalse(medicine3.equals(medicine1));

        Medicine medicine4 = new Medicine(4, "erythromycin", "for infection", "4", 6, "Y", "10", "2", "20 Dec 2016", "5", "2", "24");
        assertFalse(medicine2.equals(medicine4));
        assertFalse(medicine4.equals(medicine2));

    }
}
