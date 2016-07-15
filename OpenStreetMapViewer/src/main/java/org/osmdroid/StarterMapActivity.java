// Created by plusminus on 00:23:14 - 03.10.2008
package org.osmdroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Default map view activity.
 *
 * @author Manuel Stahl
 *
 */
public class StarterMapActivity extends FragmentActivity {
    private static final String MAP_FRAGMENT_TAG = "org.osmdroid.MAP_FRAGMENT_TAG";

    final int LOCATION=3;
    final int STORAGE=6;

    // ===========================================================
    // Constructors
    // ===========================================================
    /** Called when the activity is first created. */

    private ImageView mLocation;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(org.osmdroid.R.layout.activity_starter_main);

        mLocation = (ImageView)findViewById(R.id.iv_4_location);

        checkPermissions();

        FragmentManager fm = this.getSupportFragmentManager();
		if (fm.findFragmentByTag(MAP_FRAGMENT_TAG) == null) {
			final StarterMapFragment starterMapFragment = StarterMapFragment.newInstance();
			fm.beginTransaction().add(org.osmdroid.R.id.map_container, starterMapFragment, MAP_FRAGMENT_TAG).commit();

            mLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    starterMapFragment.location();
                }
            });
        }
    }

    private void checkPermissions() {
        int permissionCheck;

        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, "Need your location to place an icon on the map", Toast.LENGTH_LONG).show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, "We store tiles to your devices storage to reduce data usage and for reading offline tile stores", Toast.LENGTH_LONG).show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

}
