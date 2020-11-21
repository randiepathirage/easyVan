package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminManage extends AppCompatActivity {
    private Button btnpar;
    private Button btnown;
    private Button btndri;
    private Button btnsvan;
    private Button btnchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage);
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
                        Intent v = new Intent(getApplicationContext(),AdminVerify.class);
                        startActivity(v);
                        overridePendingTransition(0,0);
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



        btnpar=(Button)findViewById(R.id.btnPar);
        btnpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parIntent = new Intent(AdminManage.this,AdminParents.class);
                startActivity(parIntent);
            }
        });

        btnown=(Button)findViewById(R.id.btnOwn);
        btnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ownIntent = new Intent(AdminManage.this,AdminOwners.class);
                startActivity(ownIntent);
            }
        });

        btndri=(Button)findViewById(R.id.btnDri);
        btndri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driIntent = new Intent(AdminManage.this,AdminDrivers.class);
                startActivity(driIntent);
            }
        });

        btnsvan=(Button)findViewById(R.id.btnSvan);
        btnsvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent svanIntent = new Intent(AdminManage.this,AdminSchoolVans.class);
                startActivity(svanIntent);
            }
        });

        btnchild=(Button)findViewById(R.id.btnChild);
        btnchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent childIntent = new Intent(AdminManage.this,AdminChildren.class);
                startActivity(childIntent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.admin_appbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(AdminManage.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminManage.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}