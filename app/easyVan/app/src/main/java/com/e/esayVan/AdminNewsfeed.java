package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminNewsfeed extends AppCompatActivity {
    private Button btnnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_newsfeed);
        getSupportActionBar().setTitle("Newsfeed");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.newsfeed);

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
                        return true;
                }
                return false;
            }
        });



        btnnew=(Button)findViewById(R.id.btnNew);
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(AdminNewsfeed.this,AdminNewArticle.class);
                startActivity(newIntent);
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
                SessionManagement sessionManagement = new SessionManagement(AdminNewsfeed.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminNewsfeed.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}