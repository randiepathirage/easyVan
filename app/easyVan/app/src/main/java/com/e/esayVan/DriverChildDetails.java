package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverChildDetails extends AppCompatActivity {

    Button ok;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_child_details);
        getSupportActionBar().setTitle("Child Details");

        ok = findViewById(R.id.btnok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverViewChildDetails.class);
                startActivity(i);
            }
        });

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_c_details);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
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

}