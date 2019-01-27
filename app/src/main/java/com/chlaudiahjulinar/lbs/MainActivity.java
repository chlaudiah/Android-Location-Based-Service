package com.chlaudiahjulinar.lbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
//import android.test.mock.MockPackageManager;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button btnShowLocation;
    private static final int REQUEST_CODE_Permission = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {mPermission}, REQUEST_CODE_Permission);
            }
        } catch (Exception e){
            e.printStackTrace();

        }

        btnShowLocation = (Button) findViewById(R.id.button);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);

                if (gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLatitude" + latitude + "\nLongitude" + longitude, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });
    }
}
