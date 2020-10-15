package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentLocationFragment extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);
        getSupportActionBar().setTitle("Location");

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_location);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_newsfeed:
                        Intent i = new Intent(getApplicationContext(), ParentNewsfeedFragment.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_location:
                        return true;

                    case R.id.navigation_calendar:
                        Intent k = new Intent(getApplicationContext(), ParentCalendarFragment.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPayFragment.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}