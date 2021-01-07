package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentCalendarFragment extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Button btnMarkAttend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_calendar);
        getSupportActionBar().setTitle("Calendar");

        btnMarkAttend=findViewById(R.id.btnMarkAttend);
        btnMarkAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ParentAttendance.class);
                startActivity(i);
            }
        });

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_calendar);


        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_newsfeed:
                        Intent i = new Intent(getApplicationContext(), ParentNewsfeed.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_location:
                        Intent j = new Intent(getApplicationContext(), ParentLocationFragment.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPayFragment.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_dashboard:
                        Intent k = new Intent(getApplicationContext(), ParentDashboard.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parent_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.account:
                startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(ParentCalendarFragment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentCalendarFragment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}