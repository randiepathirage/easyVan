package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DriverPayment extends AppCompatActivity {
    String userName,childSelected,monthSelected;
    RequestQueue requestQueue;
    Spinner spinner,spinner2;
    String amount="";
    ArrayList<String> childlist=new ArrayList<>();
    ArrayAdapter<String> childAdapter;
    EditText edtAmount;
    String URL_PAY="https://10.0.2.2/easyvan/insertPaymentDriver.php";

    BottomNavigationView bottom_nav;
    //a list to store all the child details

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_payment);
        getSupportActionBar().setTitle("Payments");


        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();


        //loading spinner to select child
        String URL="https://10.0.2.2/easyvan/loadSpinnerDriver.php?userName="+userName;

        requestQueue= Volley.newRequestQueue(this);

        spinner=findViewById(R.id.spin);

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("children");
                            if(jsonArray.length()==0){
                                Toast.makeText(DriverPayment.this,"You dont have children in van",Toast.LENGTH_LONG).show();
                            }
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String childName=jsonObject.optString("child_name");
                                childlist.add(childName);
                                childAdapter=new ArrayAdapter<>(DriverPayment.this, android.R.layout.simple_spinner_item,childlist);
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
                Toast.makeText(DriverPayment.this,error.getMessage(),Toast.LENGTH_SHORT).show();

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

        //loading spinner to select month
        spinner2=findViewById(R.id.spin2);
        ArrayAdapter<CharSequence> monthAdapter=ArrayAdapter.createFromResource(this,R.array.months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(monthAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected= (String) spinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtAmount=findViewById(R.id.edtAmount);

    }


    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.driver_appbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                Intent m = new Intent(getApplicationContext(),DriverNotification.class);
                startActivity(m);
                return true;
            case R.id.top_profile:
                Intent j = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(j);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                return true;


            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverPayment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverPayment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }


    public void viewChildren(View view) {
        Intent intent = new Intent(DriverPayment.this, DriverViewPaidChildren.class);
        startActivity(intent);
    }

    public void paid(View view) {

        amount = edtAmount.getText().toString();

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
        } else {

            HttpsTrustManager.allowAllSSL();
            StringRequest request = new StringRequest(Request.Method.POST, URL_PAY,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(DriverPayment.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ParentDashboard.class));
                            finish();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DriverPayment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("amount", amount);
                    params.put("childName", childSelected);
                    params.put("username", userName);
                    params.put("month", monthSelected);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(DriverPayment.this);
            requestQueue.add(request);

        }
    }
}