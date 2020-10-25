package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminVerify extends AppCompatActivity {
    private Button btnsvan;
    private Button btndri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify);
        getSupportActionBar().setTitle("Verify");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.verify);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        Intent m = new Intent(getApplicationContext(),AdminManage.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verify:
                        return true;

                    case R.id.reportGenerator:
                        Intent r = new Intent(getApplicationContext(),AdminReportGenerator.class);
                        startActivity(r);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newsfeed:
                        Intent n = new Intent(getApplicationContext(),AdminNewsfeed.class);
                        startActivity(n);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        btnsvan=(Button)findViewById(R.id.btnSvan);
        btnsvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent svanIntent = new Intent(AdminVerify.this,AdminVerifySchoolVans.class);
                startActivity(svanIntent);
            }
        });

        btndri=(Button)findViewById(R.id.btnDri);
        btndri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driIntent = new Intent(AdminVerify.this,AdminVerifyDrivers.class);
                startActivity(driIntent);
            }
        });
    }

}





