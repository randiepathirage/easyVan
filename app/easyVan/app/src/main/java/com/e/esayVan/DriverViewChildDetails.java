package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverViewChildDetails extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    RecyclerView recyclerView;
    DriverProductAdapter adapter;

    String Name;
    private static final String DRIVER_PRODUCT_URL = "http://10.0.2.2/easyvan/driverChild.php";

    List<DriverProduct> driverProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view_child_details);
        getSupportActionBar().setTitle("View");

        driverProductList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*SessionManagement sessionManagement = new SessionManagement(DriverViewChildDetails.this);
        Name = sessionManagement.getUserName();

        Toast.makeText(DriverViewChildDetails.this, Name,Toast.LENGTH_SHORT).show();*/

        loadProducts();



        //bottom navigation bar.....
        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_c_details:
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
                        Intent m = new Intent(getApplicationContext(),DriverExpense.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DRIVER_PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);

                            for(int i = 0;i<products.length();i++){
                                JSONObject productobject = products.getJSONObject(i);

                                String number = productobject.getString("number");
                                String fname = productobject.getString("fname");
                                String lname = productobject.getString("lname");
                                String grade = productobject.getString("grade");
                                String school = productobject.getString("school");
                                String pick_loc = productobject.getString("pick_loc");
                                String dropoff_loc = productobject.getString("dropoff_loc");

                                DriverProduct product = new DriverProduct(number,fname,lname,grade,school,pick_loc,dropoff_loc);
                                driverProductList.add(product);
                            }
                            DriverProductAdapter adapter =new DriverProductAdapter(DriverViewChildDetails.this,driverProductList);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DriverViewChildDetails.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}