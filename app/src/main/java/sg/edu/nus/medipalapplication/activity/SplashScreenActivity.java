package sg.edu.nus.medipalapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import sg.edu.nus.medipalapplication.MainActivity;
import sg.edu.nus.medipalapplication.R;

/**
 * Created by Monalisa on 3/21/2017.
 */

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
/*
                SharedPreferences sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                boolean firstRun = sharedPreferences.getBoolean("firstRun", true);

                if (firstRun) {
                    editor.putBoolean("firstRun", false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreenActivity.this, PersonActivity.class);
                    //Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
*/
            }
        }, SPLASH_TIME_OUT);

        finish();
    }
}

