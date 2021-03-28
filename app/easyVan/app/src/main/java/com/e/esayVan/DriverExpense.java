package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DriverExpense extends AppCompatActivity {

    EditText amount;
    DatePicker datePicker;
    Spinner spin;
    BottomNavigationView bottom_nav;
    Toolbar top_bar;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_expense);
        getSupportActionBar().setTitle("Expenses");

        SessionManagement sessionManagement = new SessionManagement(DriverExpense.this);
        Name = sessionManagement.getUserName();

        datePicker = (DatePicker) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount);
        spin = findViewById(R.id.type);

        ArrayAdapter myadapter = new ArrayAdapter(DriverExpense.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.select_expense));
        myadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(myadapter);

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.nav_expense);

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
                        Intent k = new Intent(getApplicationContext(),DriverAlert.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_route:
                        Intent l = new Intent(getApplicationContext(),DriverRoute.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_expense:
                        return true;
                }
                return false;
            }
        });
    }

    public void AddExpense(View view){

        String str_amount = amount.getText().toString();
        String str_type = spin.getSelectedItem().toString();
        String str_username = Name ;

        int int_day = datePicker.getDayOfMonth();
        int int_month = datePicker.getMonth();
        int int_year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(int_year, int_month, int_day);

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        String str_Date = format.format(calendar.getTime());

        String match = "addexpense";

        DriverBackgroundAdd driverBackgroundAdd = new DriverBackgroundAdd(this);
        driverBackgroundAdd.execute(match,str_amount,str_type,str_Date,str_username);

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
                SessionManagement sessionManagement = new SessionManagement(DriverExpense.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverExpense.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return false;
    }
}