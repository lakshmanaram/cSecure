package com.example.adarsh.notificationapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.sql.Time;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    static boolean connected = false;
    static boolean cautious_mode = false;
    static String Username = "";
    final static String UNAME = "username";
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
//        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = getSharedPreferences("user",Context.MODE_PRIVATE);
        Username = prefs.getString(UNAME, "default");
        ImageButton profile = (ImageButton) findViewById(R.id.myProfile);
        assert profile != null;
        final Button track = (Button) findViewById(R.id.track);
        assert track != null;
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Track.class));
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
                    if(mGoogleApiClient.isConnected())
                        startLocationUpdates();
                    Toast.makeText(getApplicationContext(), "Activated Cautious mode", Toast.LENGTH_SHORT).show();
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
                if (mGoogleApiClient.isConnected()) {
                    stopLocationUpdates();
                }
            }
        });
        ImageView emergency = (ImageView) findViewById(R.id.emergency);
        assert emergency != null;
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGoogleApiClient.isConnected())
                    startLocationUpdates();
                Toast.makeText(getApplicationContext(), "Started Transferring Locations", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Emergency.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    protected void startLocationUpdates() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Request calling
            Toast.makeText(getApplicationContext(),"Sufficient permissions not given",Toast.LENGTH_SHORT).show();
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            if (mLastLocation != null) {
//                Log.i("location","latitude = "+String.valueOf(mLastLocation.getLatitude()) + " longitude = " + String.valueOf(mLastLocation.getLongitude()));
//                Toast.makeText(getApplicationContext(),"latitude = "+String.valueOf(mLastLocation.getLatitude()) + " longitude = " + String.valueOf(mLastLocation.getLongitude()),Toast.LENGTH_SHORT).show();
//            }
        connected = true;
//            startLocationUpdates();
            else{
                Toast.makeText(getApplicationContext(),"Sufficient permissions not given",Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getApplicationContext(),"Connection Suspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),"Connection failed! Restart the app",Toast.LENGTH_SHORT).show();
    }

    protected void CallAPIs(Location location, String last_updated){
        // Make API calls
        Log.i("location","latitude = "+String.valueOf(location.getLatitude())+" longitude = "+String.valueOf(location.getLongitude())+" last updated time = "+last_updated);
        Toast.makeText(getApplicationContext(),"latitude = "+String.valueOf(location.getLatitude())+" longitude = "+String.valueOf(location.getLongitude())+" last updated time = "+last_updated,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        String current_time = DateFormat.getTimeFormat(getApplicationContext()).format(new Date());
        CallAPIs(location,current_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_5: {
                startActivity(new Intent(getApplicationContext(),Profile.class));
                return true;
            }
            case R.id.action_10:
                return true;
            case R.id.action_30:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
