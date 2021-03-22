package com.e.esayVan;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParentCalendar extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Button btnMarkAttend;
    EditText edtDate;
    String userName,childSelected,morning="1",evening="1";
    Spinner spinner;
    ArrayList<String> childlist=new ArrayList<>();
    ArrayAdapter<String> childAdapter;
    RequestQueue requestQueue;
    CheckBox chkMorning,chkEvening;
    String URL_MARK="https://10.0.2.2/easyvan/parentMarkAttendance.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_calendar);
        getSupportActionBar().setTitle("Calendar");

        btnMarkAttend=findViewById(R.id.btnMarkAttend);
        chkMorning=findViewById(R.id.checkM);
        chkEvening=findViewById(R.id.checkE);

        //checkbox
        chkMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkMorning.isChecked()){
                    morning="0";
                }
            }
        });

        chkEvening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkEvening.isChecked()){
                    evening="0";
                }
            }
        });

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();


        //loading spinner
        String URL="https://10.0.2.2/easyvan/loadSpinner.php?parentUsername="+userName;

        requestQueue=Volley.newRequestQueue(this);

        spinner=findViewById(R.id.spin);

        HttpsTrustManager.allowAllSSL();
      JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("children");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String childName=jsonObject.optString("child_name");
                                childlist.add(childName);
                                childAdapter=new ArrayAdapter<>(ParentCalendar.this, android.R.layout.simple_spinner_item,childlist);
                                childAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(childAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentCalendar.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                childSelected= (String) spinner.getItemAtPosition(i);
               // Toast.makeText(ParentCalendarFragment.this,childSelected,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //selecting date
        edtDate=findViewById(R.id.edtDate);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ParentCalendar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=year+"-"+month+"-"+day;
                        edtDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        //bottom navigation
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
                        Intent j = new Intent(getApplicationContext(), ParentLocation.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPay.class);
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

    public void markAttendance(View view) {

        final String date = edtDate.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("updating....");
        progressDialog.show();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_MARK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentCalendar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentDashboard.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentCalendar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("date", date);
                params.put("childName", childSelected);
                params.put("username", userName);
                params.put("morning", morning);
                params.put("evening", evening);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentCalendar.this);
        requestQueue.add(request);

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
                SessionManagement sessionManagement = new SessionManagement(ParentCalendar.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentCalendar.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }


}