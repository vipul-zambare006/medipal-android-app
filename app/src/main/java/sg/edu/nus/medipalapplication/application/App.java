package sg.edu.nus.medipalapplication.application;

/**
 * Created by DELL on 3/19/2017.
 */

public class App {
     public static ManageMedical club;

    @Override public void onCreate() {
        super.onCreate();
        club = new ManageMedical();

    }
}
