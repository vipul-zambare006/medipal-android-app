package sg.edu.nus.medipalapplication.application;

import android.app.Application;

import sg.edu.nus.medipalapplication.MedipalFolder.ManageHealthBio;

/**
 * Created by DELL on 3/19/2017.
 */

public class App extends Application {
    public static ManageHealthBio health_bio;

    @Override public void onCreate() {
        super.onCreate();
        health_bio = new ManageHealthBio();
    }
}