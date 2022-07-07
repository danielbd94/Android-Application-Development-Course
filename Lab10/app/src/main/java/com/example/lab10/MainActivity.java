package com.example.lab10;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver mybroadcast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mybroadcast); // Unregister dynamic filter
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions(Manifest.permission.RECEIVE_SMS);
        checkPermissions(Manifest.permission.READ_SMS);
        registerFlightMode();
    }

    private void checkPermissions(String permission) {
        //permission.toString();
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), permission) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            //Toast.makeText(this, "SMS permission is granted", Toast.LENGTH_LONG).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            Toast.makeText(this, "You must grant SMS permission", Toast.LENGTH_LONG).show();
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(permission);
        }
    }

    private void registerFlightMode() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mybroadcast = new FlightModeBroadcastReceiver(), filter);
    }

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    //Toast.makeText(this, "4", Toast.LENGTH_LONG).show();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this, "You must grant SMS permission", Toast.LENGTH_LONG).show();
                }
            });

    public static class FlightModeBroadcastReceiver extends BroadcastReceiver {
        boolean mode = false;
        private static String network = "android.net.conn.CONNECTIVITY_CHANGE";
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
            NetworkInfo currentNetworkInfo = connectivityManager.getActiveNetworkInfo();
            //if (intent.getAction().equals(network)) {
            if (currentNetworkInfo != null && currentNetworkInfo.isConnected()) //if(mode = !mode)
                Toast.makeText(context, "Network ON", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Network OFF", Toast.LENGTH_LONG).show();
        }
    }
}