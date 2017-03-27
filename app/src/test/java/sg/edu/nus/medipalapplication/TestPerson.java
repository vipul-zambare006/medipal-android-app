package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.Person;

/**
 * Created by USER on 27/3/2017.
 */

public class TestPerson extends TestCase {
    private Person person1 = new Person(1, " Mahesh", "1", "15 sep 1985", "Normanton Park Singapore", "O+", "119002", "174");
    private Person person2 = new Person(2, "Ram", "2", "07-09-1988", "Bangalore India", "A+", "600102", "190");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void TestPerson() {
        assertEquals("1", person1.getId());
        assertEquals("Mahesh", person1.getName());
        assertEquals("1", person1.getIdno());
        assertEquals("15 sep 1985", person1.getDateofbirth());
        assertEquals("Normanton Park Singapore", person1.getAddress());
        assertEquals("O+", person1.getBloodtype());
        assertEquals("119002", person1.getPostalcode());
        assertEquals("174", person1.getHeight());

        assertEquals("2", person2.getId());
        assertEquals("Ram", person2.getName());
        assertEquals("2", person2.getIdno());
        assertEquals("07-09-1988", person2.getDateofbirth());
        assertEquals("Bangalore India", person2.getAddress());
        assertEquals("A+", person2.getBloodtype());
        assertEquals("600102", person2.getPostalcode());
        assertEquals("190", person2.getHeight());
    }

    public void TestGetId() {
        assertEquals("1", person1.getId());
        assertEquals("2", person2.getId());
    }

    public void TestGetName() {
        assertEquals("Mahesh", person1.getName());
        assertEquals("Ram", person2.getName());
    }

    public void TestGetIdNo() {
        assertEquals("1", person1.getIdno());
        assertEquals("2", person2.getIdno());
    }

    public void TestGetDateOfBirth() {
        assertEquals("15 sep 1985", person1.getDateofbirth());
        assertEquals("07-09-1988", person2.getDateofbirth());
    }

    public void TestGetAddress() {
        assertEquals("Normanton Park Singapore", person1.getAddress());
        assertEquals("Bangalore India", person2.getAddress());
    }

    public void TestGetBloodType() {
        assertEquals("O+", person1.getBloodtype());
        assertEquals("A+", person2.getBloodtype());
    }

    public void TestGetPostalCode() {
        assertEquals("119002", person1.getPostalcode());
        assertEquals("600102", person2.getPostalcode());
    }

    public void TestGetHeight() {
        assertEquals("174", person1.getHeight());
        assertEquals("190", person2.getHeight());
    }

    public void TestEquals() {
        assertEquals(person1, new Person(1, " Mahesh", "1", "15 sep 1985", "Normanton Park Singapore", "O+", "119002", "174"));
        assertEquals(person2, new Person(2, "Ram", "2", "07-09-1988", "Bangalore India", "A+", "600102", "190"));

        assertFalse(person1.equals(person2));
        assertFalse(person2.equals(person1));

        Person person3 = new Person(3, "Fernandes", "3", "14-08-2000", "Chennai India", "B+", "650010", "160");
        assertFalse(person1.equals(person3));
        assertFalse(person3.equals(person1));

        Person person4 = new Person(4, "Trisha", "4", "10-Oct-1970", "Chennai", "O-", "600100", "170");
        assertFalse(person2.equals(person4));
        assertFalse(person4.equals(person2));
    }


}
