package com.example.adarsh.notificationapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;

    static boolean cautious_mode = false;

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        Log.i("location","connecting");
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton profile = (ImageButton) findViewById(R.id.myProfile);
        assert profile != null;
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to profile
            }
        });
        Button pid = (Button) findViewById(R.id.pid);
        assert pid != null;
        pid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to people in danger
            }
        });
        Button track = (Button) findViewById(R.id.track);
        assert track != null;
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to track activity
            }
        });
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        final Button cautious = (Button) findViewById(R.id.cautious);
        assert cautious != null;
        final Button stopc = (Button) findViewById(R.id.stopcautious);
        assert stopc != null;
        if (cautious.getDrawingCacheBackgroundColor() == Color.parseColor("#aaaaaa"))
            cautious_mode = true;
        cautious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cautious_mode) {
                    cautious_mode = true;
                    cautious.setBackgroundColor(Color.parseColor("#aaaaaa"));
                    cautious.setClickable(false);
                    stopc.setVisibility(View.VISIBLE);
                    // call to alarm manager
                    Toast.makeText(getApplicationContext(), "Activatedd Cautious mode", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stopc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cautious_mode = false;
                stopc.setVisibility(View.GONE);
                cautious.setBackgroundColor(Color.parseColor("#000000"));
                cautious.setClickable(true);
                // Calls to cancel Alarm manager
            }
        });
        Button emergency = (Button) findViewById(R.id.emergency);
        assert emergency != null;
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // API calls to show emergency
                // Calls to Alarm manager.
                // Do the same as in cautious mode.
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                Log.i("location","latitude = "+String.valueOf(mLastLocation.getLatitude()) + " longitude = " + String.valueOf(mLastLocation.getLongitude()));
                Toast.makeText(getApplicationContext(),"latitude = "+String.valueOf(mLastLocation.getLatitude()) + " longitude = " + String.valueOf(mLastLocation.getLongitude()),Toast.LENGTH_SHORT).show();
            }

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_5:
                scheduleNotification(getNotification("5 second delay"), 5000);
                return true;
            case R.id.action_10:
                scheduleNotification(getNotification("10 second delay"), 10000);
                return true;
            case R.id.action_30:
                scheduleNotification(getNotification("30 second delay"), 30000);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
*/
}
