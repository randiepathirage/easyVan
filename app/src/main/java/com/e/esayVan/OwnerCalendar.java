package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OwnerCalendar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_calendar);
        /*declar variable*/
        BottomNavigationView bottomNavigationView ;
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        /*Set manage*/
        bottomNavigationView.setSelectedItemId(R.id.calender);
        /*performe*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.calender:
                        return true;

                    case R.id.expenses:
                        Intent j = new Intent(getApplicationContext(),OwnerExpenses.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.manage:
                        Intent l = new Intent(getApplicationContext(),OwnerManage.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.report:
                        Intent k = new Intent(getApplicationContext(),OwnerReport.class);
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
        getSupportActionBar().setTitle("Calender");
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
                SessionManagement sessionManagement = new SessionManagement(OwnerCalendar.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerCalendar.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
}