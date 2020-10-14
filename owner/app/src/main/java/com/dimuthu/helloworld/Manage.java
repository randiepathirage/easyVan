package com.dimuthu.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Manage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        /*declar variable*/
        BottomNavigationView bottomNavigationView ;

        /*selected */
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        /*Set manage*/
        bottomNavigationView.setSelectedItemId(R.id.manage);
        /*performe*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        return true;

                    case R.id.expenses:
                        Intent j = new Intent(getApplicationContext(),Expenses.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.report:
                        Intent l = new Intent(getApplicationContext(),report.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calender:
                        Intent k = new Intent(getApplicationContext(),calender.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;



                    case R.id.location:
                        Intent m = new Intent(getApplicationContext(),location.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}