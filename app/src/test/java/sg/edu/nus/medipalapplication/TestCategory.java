package sg.edu.nus.medipalapplication;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.medipalapplication.MedipalFolder.Category;

/**
 * Created by MaheshIK on 27/3/2017.
 */

public class TestCategory extends TestCase {
    private Category cat1 = new Category(1, "CHR", "Chronic", "hypertension", "Y");
    private Category cat2 = new Category(2, "INC", "Incidental", "cold", "Y");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testCategory() {
        assertEquals(1, cat1.getId());
        assertEquals("CHR", cat1.getCategorycode());
        assertEquals("Chronic", cat1.getCategoryname());
        assertEquals("hypertension", cat1.getCategorydescription());
        assertEquals("Y", cat1.getCategoryreminder());

        assertEquals(2, cat2.getId());
        assertEquals("INC", cat2.getCategorycode());
        assertEquals("Incidental", cat2.getCategoryname());
        assertEquals("cold", cat2.getCategorydescription());
        assertEquals("Y", cat2.getCategoryreminder());
    }

    public void testGetid() {
        assertEquals(1, cat1.getId());
        assertEquals(2, cat2.getId());
    }

    public void testGetCategoryCode() {
        assertEquals("CHR", cat1.getCategorycode());
        assertEquals("INC", cat2.getCategorycode());
    }

    public void testGetCategoryName() {
        assertEquals("Chronic", cat1.getCategoryname());
        assertEquals("Incidental", cat2.getCategoryname());
    }

    public void testGetCategoryDescription() {
        assertEquals("hypertension", cat1.getCategorydescription());
        assertEquals("cold", cat2.getCategorydescription());
    }

    public void testGetCategoryReminder() {
        assertEquals("Y", cat1.getCategoryreminder());
        assertEquals("Y", cat2.getCategoryreminder());
    }

    public void testEquals() {
        assertEquals(cat1, new Category(1, "CHR", "Chronic", "hypertension", "Y"));
        assertEquals(cat2, new Category(2, "INC", "Incidental", "cold", "Y"));

        assertFalse(cat1.equals(cat2));
        assertFalse(cat2.equals(cat1));

        Category cat3 = new Category(3, "COM", "Complete Course", "sinus", "Y");
        assertFalse(cat1.equals(cat3));
        assertFalse(cat3.equals(cat1));

        Category cat4 = new Category(4, "SEL", "Self Apply", "balms", "O");
        assertFalse(cat2.equals(cat4));
        assertFalse(cat4.equals(cat2));

    }
}
