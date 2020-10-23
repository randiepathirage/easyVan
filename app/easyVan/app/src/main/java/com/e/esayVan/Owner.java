package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.e.esayVan.OwnerNotification;
import com.e.esayVan.OwnerRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button manage1 , report1 ,expenses1 , calender1, request1 , location1 ;
    private Menu menu;

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                startActivity(new Intent(this, OwnerNotification.class));
                return true;

            case R.id.reqest:
                startActivity(new Intent(this, OwnerRequest.class));
                return true;
        }
        return true;
    }


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
                Intent mang = new Intent(MainActivity.this,OwnerManage.class);
                startActivity(mang);

            }
        });
        report1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(MainActivity.this,OwnerReport.class);
                startActivity(rpt);

            }
        });
        expenses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(MainActivity.this,OwnerExpenses.class);
                startActivity(rpt);

            }
        });
        calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clc = new Intent(MainActivity.this,OwnerCalendar.class);
                startActivity(clc);

            }
        });
        request1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rq = new Intent(MainActivity.this,OwnerRequest.class);
                startActivity(rq);

            }
        });
        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lc = new Intent(MainActivity.this,OwnerLocation.class);
                startActivity(lc);

            }
        });
        getSupportActionBar().setTitle("Home");


    }
}