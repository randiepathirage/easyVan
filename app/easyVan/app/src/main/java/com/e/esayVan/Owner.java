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
        getSupportActionBar().setTitle("Home");
    }

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
                startActivity(new Intent(this,OwnerNotification.class));
                return true;

            case R.id.reqest:
                startActivity(new Intent(this,OwnerRequest.class));
                return true;

            case R.id.top_profile:
                startActivity(new Intent(this,OwnerAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(Owner.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(Owner.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
}