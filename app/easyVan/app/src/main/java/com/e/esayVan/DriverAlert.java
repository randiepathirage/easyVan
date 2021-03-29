package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class DriverAlert extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Spinner spin;
    TextInputEditText msg;
    Button btn_send;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_alert);
        getSupportActionBar().setTitle("Alert");

        msg = (TextInputEditText)findViewById(R.id.alert_msg);
        btn_send = (Button)findViewById(R.id.alert_send);

        SessionManagement sessionManagement = new SessionManagement(DriverAlert.this);
        Name = sessionManagement.getUserName();

        spin = findViewById(R.id.spinner_alert);
        ArrayAdapter myadapter = new ArrayAdapter(DriverAlert.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.select_alert));
        myadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(myadapter);

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_alert);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
                        Intent i = new Intent(getApplicationContext(),DriverViewChildDetails.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_attendance:
                        Intent j = new Intent(getApplicationContext(),DriverAttendance.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_alert:
                        return true;

                    case R.id.nav_route:
                        Intent l = new Intent(getApplicationContext(),DriverRoute.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_expense:
                        Intent m = new Intent(getApplicationContext(),DriverExpense.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.driver_appbar,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                Intent m = new Intent(getApplicationContext(),DriverNotification.class);
                startActivity(m);
                return true;
            case R.id.top_profile:
                Intent k = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(k);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                Intent j = new Intent(getApplicationContext(),DriverPayment.class);
                startActivity(j);
                return true;
            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverAlert.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverAlert.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return false;
    }

    public void SendAlert(View view){
        String str_msg = msg.getText().toString();
        String str_username = Name ;

        String match = "sendalert";
        if(str_msg.isEmpty()){
            Toast.makeText(DriverAlert.this,"Please enter message",Toast.LENGTH_LONG).show();
        }else {

            DriverBackgroundAlert driverBackgroundAlert = new DriverBackgroundAlert(this);
            driverBackgroundAlert.execute(match, str_msg, str_username);
        }
    }

}