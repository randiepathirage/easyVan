package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverPayment extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_payment);
        getSupportActionBar().setTitle("Payments");

        bottom_nav = findViewById(R.id.bottom_navigation);

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
                        Intent l = new Intent(getApplicationContext(),DriverRoute.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
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

    }

    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.driver_appbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                Intent m = new Intent(getApplicationContext(),DriverNotification.class);
                startActivity(m);
                return true;
            case R.id.top_profile:
                Intent j = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(j);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                return true;


            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverPayment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverPayment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
}