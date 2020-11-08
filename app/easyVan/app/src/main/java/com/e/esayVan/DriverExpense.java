package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverExpense extends AppCompatActivity {

    Spinner spin;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_expense);


        spin = findViewById(R.id.spinexpense);
        //ArrayAdapter myadapter = new ArrayAdapter(DriverExpense.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.select_expense));
       // myadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //spin.setAdapter(myadapter);

        bottom_nav = findViewById(R.id.bottom_navigation);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
                        Intent i = new Intent(getApplicationContext(),DriverChildDetails.class);
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

                    case R.id.nav_payment:
                        Intent m = new Intent(getApplicationContext(),DriverPayment.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}