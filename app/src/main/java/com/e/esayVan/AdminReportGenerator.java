package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AdminReportGenerator extends AppCompatActivity {

    Spinner spinnerowners;
    ArrayList<String> ownerlist = new ArrayList<>();
    ArrayAdapter<String> oadapter;
    RequestQueue requestQueue;
    String user, c;
    EditText edtD1,edtD2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report_generator);
        getSupportActionBar().setTitle("Report Generator");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.reportGenerator);

        requestQueue = Volley.newRequestQueue(this);
        spinnerowners = findViewById(R.id.spinnerowners);
        HttpsTrustManager.allowAllSSL();
        String url = "https://10.0.2.2/easyvan/owner_spinner.php";
        //get data to spinner
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("owners");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String username = jsonObject.optString("username");
                        ownerlist.add(username);
                        oadapter = new ArrayAdapter<>(AdminReportGenerator.this, android.R.layout.simple_spinner_item, ownerlist);
                        oadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerowners.setAdapter(oadapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        requestQueue.add(jsonObjectRequest);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.manage:
                        Intent m = new Intent(getApplicationContext(), AdminManage.class);
                        startActivity(m);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(), AdminVerify.class);
                        startActivity(v);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.reportGenerator:
                        return true;

                    case R.id.newsfeed:
                        Intent n = new Intent(getApplicationContext(), AdminNewsfeed.class);
                        startActivity(n);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        //get data from calendars
        edtD1=findViewById(R.id.edtDate1);

        Calendar calendar1=Calendar.getInstance();
        final int year1=calendar1.get(Calendar.YEAR);
        final int month1=calendar1.get(Calendar.MONTH);
        final int day1=calendar1.get(Calendar.DAY_OF_MONTH);

        edtD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog1=new DatePickerDialog(AdminReportGenerator.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                        month1=month1+1;
                        String d1=year1+"-"+month1+"-"+day1;
                        edtD1.setText(d1);
                    }
                },year1,month1,day1);
                datePickerDialog1.show();
            }
        });

        edtD2=findViewById(R.id.edtDate2);

        Calendar calendar2=Calendar.getInstance();
        final int year2=calendar2.get(Calendar.YEAR);
        final int month2=calendar2.get(Calendar.MONTH);
        final int day2=calendar2.get(Calendar.DAY_OF_MONTH);

        edtD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog2=new DatePickerDialog(AdminReportGenerator.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year2, int month2, int day2) {
                        month2=month2+1;
                        String d2=year2+"-"+month2+"-"+day2;
                        edtD2.setText(d2);
                    }
                },year2,month2,day2);
                datePickerDialog2.show();
            }
        });



    }

    public void btn_report(View view){

        final String owner = spinnerowners.getSelectedItem().toString();
        final String date1 = edtD1.getText().toString();
        final String date2 = edtD2.getText().toString();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/adminreport.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("send")) {
                            Toast.makeText(AdminReportGenerator.this,"Report Has Been Emailed To The User.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminReportGenerator.this,"", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminReportGenerator.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("owner", owner);
                params.put("date1", date1);
                params.put("date2", date2);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(AdminReportGenerator.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminReportGenerator.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;


        }

        return true;
    }
}