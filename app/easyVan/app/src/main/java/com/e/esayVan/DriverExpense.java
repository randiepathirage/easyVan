package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class DriverExpense extends AppCompatActivity {

    EditText date,amount;

    Spinner spin;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_expense);
        getSupportActionBar().setTitle("Expenses");

        date = (EditText) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount);

        spin = findViewById(R.id.type);
        ArrayAdapter myadapter = new ArrayAdapter(DriverExpense.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.select_expense));
        myadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(myadapter);

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_expense);

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
                        return true;
                }
                return false;
            }
        });
    }

    public void AddExpense(View view){

        String str_amount = amount.getText().toString();
        String str_type = spin.getSelectedItem().toString();
        String str_date = date.getText().toString();


        String match = "addexpense";

        DriverBackgroundAdd driverBackgroundAdd = new DriverBackgroundAdd(this);
        driverBackgroundAdd.execute(match,str_amount,str_type,str_date);

    }
}