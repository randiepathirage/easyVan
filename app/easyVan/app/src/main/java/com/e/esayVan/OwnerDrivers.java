package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OwnerDrivers extends AppCompatActivity {

    //View for driver list
    String Name;

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/driverlist.php";

    //a list to store all the vehicles
    List<OwnerDriversProduct> DriverList;
    OwnerDriverProductAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
   // private View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_drivers);


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionManagement sessionManagement = new SessionManagement(OwnerDrivers.this);
        Name = sessionManagement.getUserName();

        Toast.makeText(OwnerDrivers.this, Name,Toast.LENGTH_SHORT).show();

        //initializing the vehiclelist
        DriverList = new ArrayList<>();

       loadVehicles();

        getSupportActionBar().setTitle("Drivers");



    }
    private void loadVehicles() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject products=array.getJSONObject(i);

                                DriverList.add(new OwnerDriversProduct(
                                       products.getString("username"),
                                        products.getString("vehicleNo"),
                                        products.getString("licenceNo"),
                                        products.getString("contactNO"),
                                        products.getString("email")

                                ));


                            }

                            //creating recyclerview adapter
                            OwnerDriverProductAdapter adapter = new OwnerDriverProductAdapter(OwnerDrivers.this, DriverList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(OwnerDrivers.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String,String>();

            params.put("username",Name);
               // Toast.makeText(OwnerDrivers.this,userName,Toast.LENGTH_LONG).show();
           // params.put("username",userName);
              //  Toast.makeText(OwnerDrivers.this,userName,Toast.LENGTH_SHORT);
            return params;
        }


        };

        Volley.newRequestQueue(this).add(stringRequest);


    }
    public void btn_add_Driver (View view) {
        startActivity(new Intent(getApplicationContext(),DriverSignUp.class));
    }

    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }



    }



    
