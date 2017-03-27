package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.EmergencyContact;

/**
 * Created by Mahesh Inna Kedage on 27/3/2017.
 */

public class TestEmergencyContact extends TestCase {
    private EmergencyContact EC1 = new EmergencyContact(1, "Raghu", "83650458", "NOK", "Relation", "2");
    private EmergencyContact EC2 = new EmergencyContact(2, "emily", "83654058", "Doctor", "Doctor", "1");

    public TestEmergencyContact() {
        assertEquals(1, EC1.getId());
        assertEquals("Raghu", EC1.getName());
        assertEquals("83650458", EC1.getNumber());
        assertEquals("NOK", EC1.getContactType());
        assertEquals("Relation", EC1.getDesc());
        assertEquals("2", EC1.getPreference());

        assertEquals(2, EC2.getId());
        assertEquals("Emily", EC2.getName());
        assertEquals("83654058", EC2.getNumber());
        assertEquals("Doctor", EC2.getContactType());
        assertEquals("Doctor", EC2.getDesc());
        assertEquals("1", EC2.getPreference());
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void TestGetId() {
        assertEquals(1, EC1.getId());
        assertEquals(2, EC2.getId());
    }

    public void TestGetName() {
        assertEquals("Raghu", EC1.getName());
        assertEquals("Emily", EC2.getName());

    }

    public void TestGetNumber() {
        assertEquals("83650458", EC1.getNumber());
        assertEquals("83654058", EC2.getNumber());

    }

    public void TestGetContactType() {
        assertEquals("NOK", EC1.getContactType());
        assertEquals("Doctor", EC2.getContactType());
    }

    public void TestGetDesc() {
        assertEquals("Relation", EC1.getDesc());
        assertEquals("Doctor", EC2.getDesc());
    }

    public void TestGetPreference() {
        assertEquals("2", EC1.getPreference());
        assertEquals("1", EC2.getPreference());
    }

    public void TestEquals() {
        assertEquals(EC1, new EmergencyContact(1, "Raghu", "83650458", "NOK", "Relation", "2"));
        assertEquals(EC2, new EmergencyContact(2, "emily", "83654058", "Doctor", "Doctor", "1"));

        assertFalse(EC1.equals(EC2));
        assertFalse(EC2.equals(EC1));

        EmergencyContact EC3 = new EmergencyContact(3, "esther", "83660458", "Nurse", "Nurse", "3");
        assertFalse(EC1.equals(EC3));
        assertFalse(EC3.equals(EC1));

        EmergencyContact EC4 = new EmergencyContact(4, "jacqueline", "83634456", "Doctor", "wife", "4");
        assertFalse(EC2.equals(EC4));
        assertFalse(EC4.equals(EC2));

    }
}
