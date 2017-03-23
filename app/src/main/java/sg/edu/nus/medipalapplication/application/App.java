package sg.edu.nus.medipalapplication.application;

import android.app.Application;

import sg.edu.nus.medipalapplication.MedipalFolder.ManageMedical;

/**
 * Created by DELL on 3/19/2017.
 */

public class App extends Application {
     public static ManageMedical club;

    @Override public void onCreate() {
        super.onCreate();
        club = new ManageMedical();

    }
}
