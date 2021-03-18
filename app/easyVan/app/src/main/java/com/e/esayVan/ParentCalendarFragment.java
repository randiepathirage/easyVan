package com.e.esayVan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class ParentCalendarFragment extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Button btnMarkAttend;
    EditText edtDate;
    private Spinner spinner;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_calendar);
        getSupportActionBar().setTitle("Calendar");

        //selecting date
        edtDate=findViewById(R.id.edtDate);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ParentCalendarFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        edtDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        //add spinner to select child
        spinner=findViewById(R.id.spinner);
      //  ArrayAdapter<String> adapter=new ArrayAdapter<String>(ParentCalendarFragment.this,
     //           android.R.layout.simple_list_item_1,getResources().getStringArray(R.strings.names));
     //   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     //   adapter.setAdaptor(adapter);

        btnMarkAttend=findViewById(R.id.btnMarkAttend);
        btnMarkAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_calendar);


        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_newsfeed:
                        Intent i = new Intent(getApplicationContext(), ParentNewsfeed.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_location:
                        Intent j = new Intent(getApplicationContext(), ParentLocationFragment.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPayFragment.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_dashboard:
                        Intent k = new Intent(getApplicationContext(), ParentDashboard.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parent_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.account:
                startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(ParentCalendarFragment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentCalendarFragment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}