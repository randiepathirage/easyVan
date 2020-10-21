package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Owner extends AppCompatActivity {
    Button manage1 , report1 ,expenses1 , calender1, request1 , location1 ;
    private Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);


        manage1=findViewById(R.id.manage);
        report1=findViewById(R.id.report);
        expenses1=findViewById(R.id.expenses);
        calender1=findViewById(R.id.calender);
        request1=findViewById(R.id.reqest);
        location1=findViewById(R.id.location);



        manage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mang = new Intent(Owner.this,OwnerManage.class);
                startActivity(mang);

            }
        });
        report1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(Owner.this,OwnerReport.class);
                startActivity(rpt);

            }
        });
       expenses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpt = new Intent(Owner.this,OwnerExpenses.class);
                startActivity(rpt);

            }
        });
        calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clc = new Intent(Owner.this,OwnerCalendar.class);
                startActivity(clc);

            }
        });

        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lc = new Intent(Owner.this,OwnerLocation.class);
                startActivity(lc);

            }
        });

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
                        Intent j = new Intent(getApplicationContext(),OwnerExpenses.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.report:
                        Intent l = new Intent(getApplicationContext(),OwnerReport.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calender:
                        Intent k = new Intent(getApplicationContext(),OwnerCalendar.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;



                    case R.id.location:
                        Intent m = new Intent(getApplicationContext(),OwnerLocation.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
        getSupportActionBar().setTitle("Home");


    }
}