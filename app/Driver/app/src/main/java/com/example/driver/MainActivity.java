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

public class MainActivity extends AppCompatActivity {

    Button child_details,exense,alert,attendance,payment,calendar,route;
    Toolbar top_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_bar = findViewById(R.id.top_toolbar);
        setSupportActionBar(top_bar);

        child_details = findViewById(R.id.btnc_details);
        child_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ChildDetails.class);
                startActivity(i);
            }
        });

        exense = findViewById(R.id.btnexpanses);
        exense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Expense.class);
                startActivity(i);
            }
        });

        alert = findViewById(R.id.btnalert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Alert.class);
                startActivity(i);
            }
        });

        attendance = findViewById(R.id.btnattendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Attendance.class);
                startActivity(i);
            }
        });

        payment = findViewById(R.id.btnpayment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Payment.class);
                startActivity(i);
            }
        });

        calendar = findViewById(R.id.btncalendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Calendar.class);
                startActivity(i);
            }
        });

        route = findViewById(R.id.btnroute);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Route.class);
                startActivity(i);
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