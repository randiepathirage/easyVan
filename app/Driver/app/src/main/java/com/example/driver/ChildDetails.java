package com.example.driver;

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

public class ChildDetails extends AppCompatActivity {

    Button ok;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_details);

        top_bar = findViewById(R.id.top_toolbar);
        setSupportActionBar(top_bar);


        ok = findViewById(R.id.btnok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewChildDetails.class);
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
                        Intent j = new Intent(getApplicationContext(),Attendance.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_alert:
                        Intent k = new Intent(getApplicationContext(),Alert.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_route:
                        Intent l = new Intent(getApplicationContext(),Route.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_payment:
                        Intent m = new Intent(getApplicationContext(),Payment.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                /*notification open*/
                return true;
            case R.id.top_profile:
                Intent k = new Intent(getApplicationContext(),Profile.class);
                startActivity(k);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),Calendar.class);
                startActivity(i);
                return true;
            case R.id.top_expense:
                Intent j = new Intent(getApplicationContext(),Expense.class);
                startActivity(j);
                return true;
        }
        return false;
    }
}