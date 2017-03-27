package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.Reminder;

/**
 * Created by USER on 27/3/2017.
 */

public class TestReminder extends TestCase {
    private Reminder rem1 = new Reminder(1, 3, 4, "13 Mar 2017");
    private Reminder rem2 = new Reminder(2, 2, 8, "13 Mar 2017");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void TestReminder() {
        assertEquals(1, rem1.getId());
        assertEquals(3, rem1.getFrequency());
        assertEquals(4, rem1.getInterval());
        assertEquals("13 Mar 2017", rem1.getstartDateTime());

        assertEquals(2, rem2.getId());
        assertEquals(2, rem2.getFrequency());
        assertEquals(8, rem2.getInterval());
        assertEquals("13 Mar 2017", rem2.getstartDateTime());
    }

    public void TestGetId() {
        assertEquals(1, rem1.getId());
        assertEquals(2, rem2.getId());
    }

    public void TestGetFrequency() {
        assertEquals(3, rem1.getFrequency());
        assertEquals(2, rem2.getFrequency());
    }

    public void TestGetInterval() {
        assertEquals(4, rem1.getInterval());
        assertEquals(8, rem2.getInterval());
    }

    public void TestGetstartDateTime() {
        assertEquals("13 Mar 2017", rem1.getstartDateTime());
        assertEquals("13 Mar 2017", rem2.getstartDateTime());
    }

    public void TestEquals() {
        assertEquals(rem1, new Reminder(1, 3, 4, "13 Mar 2017"));
        assertEquals(rem2, new Reminder(2, 2, 8, "13 Mar 2017"));

        assertFalse(rem1.equals(rem2));
        assertFalse(rem2.equals(rem1));

        Reminder rem3 = new Reminder(3, 3, 4, "10 Feb 2017");
        assertFalse(rem1.equals(rem3));
        assertFalse(rem3.equals(rem1));

        Reminder rem4 = new Reminder(4, 2, 8, "15 Feb 2017");
        assertFalse(rem2.equals(rem4));
        assertFalse(rem4.equals(rem2));
    }
}
