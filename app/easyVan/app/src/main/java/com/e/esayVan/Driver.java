package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Driver extends AppCompatActivity {

    Button child_details,expense,alert,attendance,payment,calendar,route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        getSupportActionBar().setTitle("Driver");

        child_details = findViewById(R.id.btnc_details);
        child_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverChildDetails.class);
                startActivity(i);
            }
        });

       alert = findViewById(R.id.btnalert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverAlert.class);
                startActivity(i);
            }
        });

        attendance = findViewById(R.id.btnattendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DriverAttendance.class);
                startActivity(i);
            }
        });


      route = findViewById(R.id.btnroute);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverRoute.class);
                startActivity(i);
            }
        });


        expense = findViewById(R.id.btnexpanses);
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverExpense.class);
                startActivity(i);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.driver_appbar,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                /*notification open*/
                return true;
            case R.id.top_profile:
                /*profile open*/
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_expense:
                Intent j = new Intent(getApplicationContext(),DriverPayment.class);
                startActivity(j);
                return true;
        }
        return false;
    }
}