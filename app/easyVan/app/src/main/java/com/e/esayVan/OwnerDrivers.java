package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
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
import java.util.List;


public class OwnerDrivers extends AppCompatActivity {

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/Api.php";

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


        //initializing the vehiclelist
        DriverList = new ArrayList<>();

       loadVehicles();
    }
    private void loadVehicles() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject products=array.getJSONObject(i);

                                DriverList.add(new OwnerDriversProduct(
                                       products.getString("username"),
                                        products.getString("password")
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
                Toast.makeText(OwnerDrivers.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);



    }
    public void btn_add_Driver (View view) {
        startActivity(new Intent(getApplicationContext(),DriverSignUp.class));
    }


    }



    
