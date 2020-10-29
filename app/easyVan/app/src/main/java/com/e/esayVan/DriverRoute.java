package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverRoute extends AppCompatActivity {

    EditText etSource,etDestination;
    Button go;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_route);
        getSupportActionBar().setTitle("Route");

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        go = findViewById(R.id.btn_go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                if(sSource.equals("") && sDestination.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter both locaton",Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sSource,sDestination);
                }
            }
        });

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_route);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
                        Intent i = new Intent(getApplicationContext(),DriverChildDetails.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_attendance:
                        Intent j = new Intent(getApplicationContext(),DriverAttendance.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_alert:
                        Intent k = new Intent(getApplicationContext(),DriverAlert.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_route:
                        return true;

                    case R.id.nav_payment:
                        Intent m = new Intent(getApplicationContext(),DriverPayment.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        try{
            Uri uri = Uri.parse("https://www/google/co.in/maps/dir/" + sSource + "/" + sDestination);
            Intent i = new Intent(Intent.ACTION_VIEW,uri);
            i.setPackage("com.google.android.apps.maps");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent i =new Intent(Intent.ACTION_VIEW,uri);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}