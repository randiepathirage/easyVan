package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Parent extends AppCompatActivity {

    Button btnNewsFeed,btnLocation,btnCalendar,btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        btnNewsFeed =findViewById(R.id.btnNewsFeed);
        btnNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ParentNewsfeedFragment.class);
                startActivity(intent);
            }
        });

        btnLocation=findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ParentLocationFragment.class);
                startActivity(intent);
            }
        });

        btnCalendar=findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ParentCalendarFragment.class);
                startActivity(intent);
            }
        });

        btnPay=findViewById(R.id.btnPayFees);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ParentPayFragment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parent_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                startActivity(new Intent(getApplicationContext(), ParentNotification.class));
                return true;

            case R.id.account:
                startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                return true;
        }
        return true;
    }
}
