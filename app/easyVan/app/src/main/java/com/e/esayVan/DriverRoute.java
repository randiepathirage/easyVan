package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class DriverRoute extends AppCompatActivity {

    EditText etSource,etDestination;
    Button go;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;
    boolean isActive;

    Button btLocation,btnEnd;
    String longitude, latitude,userName;
    FusedLocationProviderClient client;
    String URL_SET="https://10.0.2.2/easyvan/setLocation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_route);
        getSupportActionBar().setTitle("Route");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_route);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
                        Intent i = new Intent(getApplicationContext(),DriverViewChildDetails.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_attendance:
                        Intent j = new Intent(getApplicationContext(),DriverAttendance.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_alert:
                        Intent k = new Intent(getApplicationContext(),DriverAlert.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_route:
                        return true;

                    case R.id.nav_expense:
                        Intent m = new Intent(getApplicationContext(),DriverExpense.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //get location
        btLocation = findViewById(R.id.btnStart);
        btnEnd=findViewById(R.id.btnEnd);
        //initilize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isActive=true;
                content();

  /*             if (ActivityCompat.checkSelfPermission(DriverRoute.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(DriverRoute.this
                        ,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {

                    //when both permission are granted
                    getLocation();
                } else {
                    //permission denied
                    ActivityCompat.requestPermissions(DriverRoute.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }*/







            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isActive=false;
                Toast.makeText(DriverRoute.this, "Location stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void content(){
        if(isActive){

            //check permission
            if (ActivityCompat.checkSelfPermission(DriverRoute.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(DriverRoute.this
                    ,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {

                //when both permission are granted
                getLocation();
            } else {
                //permission denied
                ActivityCompat.requestPermissions(DriverRoute.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            }

            refresh(30000);//30 seconds
        }

    }

    private void refresh(int milliseconds){

        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                content();
            }
        };
        handler.postDelayed(runnable,milliseconds);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100 && grantResults.length>0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getLocation();
        }else{
            Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if (location != null) {
                        //Toast.makeText(DriverRoute.this,"ssssss",Toast.LENGTH_LONG).show();
                          //  Geocoder geocoder = new Geocoder(DriverRoute.this, Locale.getDefault());
                         //   List<Address> addresses = geocoder.getFromLocation(
                         //           location.getLatitude(), location.getLongitude(), 1);

                        longitude=String.valueOf(location.getLongitude());
                        latitude=String.valueOf(location.getLatitude());

                        setLocation();

                    }else{
                        //when location result is null
                        LocationRequest locationRequest=new LocationRequest().setPriority(
                                LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(1000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback=new LocationCallback(){

                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1=locationResult.getLastLocation();

                                longitude=String.valueOf(location1.getLongitude());
                                latitude=String.valueOf(location1.getLatitude());

                                setLocation();

                            }
                        };
                        client.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());
                    }
                }
            });
        }  else{
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    private void setLocation() {


        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_SET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(DriverRoute.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DriverRoute.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("username", userName);
                params.put("longitude",longitude);
                params.put("latitude",latitude);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(DriverRoute.this);
        requestQueue.add(request);



    }


    //header navigation bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.driver_appbar,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                Intent m = new Intent(getApplicationContext(),DriverNotification.class);
                startActivity(m);
                return true;
            case R.id.top_profile:
                Intent k = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(k);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                Intent j = new Intent(getApplicationContext(),DriverPayment.class);
                startActivity(j);
                return true;
            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverRoute.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverRoute.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return false;
    }
}