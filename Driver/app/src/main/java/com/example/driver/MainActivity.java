package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button child_details,exense,alert,attendance,payment,calendar,route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}