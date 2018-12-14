package com.vcodewise.karobarlist.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.utils.AppConstants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermission();
            }
        }, 2000);
    }

    void requestPermission()
    {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            SharedPreferences prefs = getSharedPreferences(AppConstants.appPrefName, MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            if (isLoggedIn){
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            startActivity(intent);
            finish();
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, 10);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
            }
        }else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
