package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.medipalapplication.MedipalFolder.Consumption;

/**
 * Created by Mahesh Inna Kedage on 27/3/2017.
 */

public class TestConsumption extends TestCase {
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = dateFormat.parse("03/13/2017");

    private Consumption con1 = new Consumption(1, 3, 25, date);
    private Consumption con2 = new Consumption(2, 5, 1, date);

    public TestConsumption() throws ParseException {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testConsumption() {
        assertEquals(1, con1.getId());
        assertEquals(3, con1.getMedicineID());
        assertEquals(25, con1.getQuantity());
        assertEquals("10 Mar 2017:09:30", con1.getDate());

        assertEquals(2, con2.getId());
        assertEquals(5, con2.getMedicineID());
        assertEquals(1, con2.getQuantity());
        assertEquals("10 Mar 2017:12:00", con2.getDate());
    }

    public void testGetId() {
        assertEquals(1, con1.getId());
        assertEquals(2, con2.getId());
    }

    public void testGetMedicineId() {
        assertEquals(3, con1.getMedicineID());
        assertEquals(5, con2.getMedicineID());
    }

    public void testGetQuantity() {
        assertEquals(25, con1.getQuantity());
        assertEquals(1, con2.getQuantity());
    }

    public void testGetDate() {
        assertEquals("10 Mar 2017:09:30", con1.getDate());
        assertEquals("10 Mar 2017:12:00", con2.getDate());
    }

    public void testEquals() {
        assertEquals(con1, new Consumption(1, 3, 25, date));
        assertEquals(con2, new Consumption(2, 5, 1, date));

        assertFalse(con1.equals(con2));
        assertFalse(con2.equals(con1));

        Consumption con3 = new Consumption(3, 2, 25, date);
        assertFalse(con1.equals(con3));
        assertFalse(con3.equals(con1));

        Consumption con4 = new Consumption(4, 1, 1, date);
        assertFalse(con2.equals(con4));
        assertFalse(con4.equals(con2));
    }
}
