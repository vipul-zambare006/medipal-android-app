package sg.edu.nus.medipalapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import sg.edu.nus.medipalapplication.MedipalFolder.Appointment;
import sg.edu.nus.medipalapplication.database.AppointmentDAO;
import sg.edu.nus.medipalapplication.database.DBHelper;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class AppointmentDAOTest {
    private DBHelper database;
    AppointmentDAO appointmentDAO;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = getTargetContext();

        assertEquals("sg.edu.nus.medipalapplication", appContext.getPackageName());
    }

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase("MedipalDB.db");
        database = new DBHelper(getTargetContext());
        appointmentDAO = new AppointmentDAO(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void shouldAddAppointment() throws Exception
    {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        appointmentDAO.addAppointment(new Appointment(1,"Clementi Hospital","Routine checkup","22-10-2017","3:00 PM"));
        appointments = appointmentDAO.GetAllAppointment();

        assertTrue(appointments.size() == 1);
    }
}
