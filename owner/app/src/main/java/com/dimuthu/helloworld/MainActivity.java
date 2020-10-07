package com.dimuthu.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button manage1 , report1 ,expenses1 , calender1, request1 , location1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manage1=findViewById(R.id.manage);
        report1=findViewById(R.id.report);
        expenses1=findViewById(R.id.expenses);
        calender1=findViewById(R.id.calender);
        request1=findViewById(R.id.reqest);
        location1=findViewById(R.id.location);



        manage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mang = new Intent(MainActivity.this,Manage.class);
                startActivity(mang);

            }
        });
        report1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(MainActivity.this,report.class);
                startActivity(rpt);

            }
        });
        expenses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(MainActivity.this,Expenses.class);
                startActivity(rpt);

            }
        });
        calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clc = new Intent(MainActivity.this,calender.class);
                startActivity(clc);

            }
        });
        request1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rq = new Intent(MainActivity.this,request.class);
                startActivity(rq);

            }
        });
        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lc = new Intent(MainActivity.this,location.class);
                startActivity(lc);

            }
        });


    }
}