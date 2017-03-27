package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.HealthBio;


/**
 * Created by Mahesh Inna Kedage on 27/3/2017.
 */

public class TestHealthBio extends TestCase {
    private HealthBio health1 = new HealthBio(1, "psoarasis", "11 Mar 2017", "A");
    private HealthBio health2 = new HealthBio(2, "Viral fever", "05 Feb 2017", "C");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void TestHealthBio() {
        assertEquals("psoarasis", health1.getCondition());
        assertEquals("11 Mar 2017", health1.getStartdate());
        assertEquals("A", health1.getConditionType());

        assertEquals("Viral fever", health2.getCondition());
        assertEquals("05 Feb 2017", health2.getStartdate());
        assertEquals("C", health2.getConditionType());
    }

    public void TestGetCondition() {
        assertEquals("psoarasis", health1.getCondition());
        assertEquals("Viral fever", health2.getCondition());
    }

    public void TestGetStartDate() {
        assertEquals("11 Mar 2017", health1.getStartdate());
        assertEquals("05 Feb 2017", health1.getStartdate());
    }

    public void TestGetConditionType() {
        assertEquals("A", health1.getConditionType());
        assertEquals("C", health2.getConditionType());
    }

    public void TestEquals() {
        assertEquals(health1, new HealthBio(1, "psoarasis", "11 Mar 2017", "A"));
        assertEquals(health2, new HealthBio(2, "Viral fever", "05 Feb 2017", "C"));

        assertFalse(health1.equals(health2));
        assertFalse(health2.equals(health1));

        HealthBio health3 = new HealthBio(3, "chikungunya", "10 Jan 2016", "C");
        assertFalse(health1.equals(health3));
        assertFalse(health3.equals(health1));

        HealthBio health4 = new HealthBio(4, "Jaundice", "15 Jan 2015", "C");
        assertFalse(health2.equals(health4));
        assertFalse(health4.equals(health2));
    }

}
