package com.example.ezvanadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class manage extends AppCompatActivity {
    private Button btnpar;
    private Button btnown;
    private Button btndri;
    private Button btnsvan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        getSupportActionBar().setTitle("Manage");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.manage);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(),verify.class);
                        startActivity(v);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.reportGenerator:
                        Intent r = new Intent(getApplicationContext(),reportGenerator.class);
                        startActivity(r);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newsfeed:
                        Intent n = new Intent(getApplicationContext(),newsfeed.class);
                        startActivity(n);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        btnpar=(Button)findViewById(R.id.btnPar);
        btnpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parIntent = new Intent(manage.this,Parents.class);
                startActivity(parIntent);
            }
        });

        btnown=(Button)findViewById(R.id.btnOwn);
        btnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ownIntent = new Intent(manage.this,Owners.class);
                startActivity(ownIntent);
            }
        });

        btndri=(Button)findViewById(R.id.btnDri);
        btndri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driIntent = new Intent(manage.this,Drivers.class);
                startActivity(driIntent);
            }
        });

        btnsvan=(Button)findViewById(R.id.btnSvan);
        btnsvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent svanIntent = new Intent(manage.this,schoolVans.class);
                startActivity(svanIntent);
            }
        });
    }
}