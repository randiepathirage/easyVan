package com.e.esayVan;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerVans extends AppCompatActivity {

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/vanlist.php";
    //a list to store all the vehicles
    List<OwnerVansProduct> VanList;
    OwnerVansProductAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
    //View for van list
    String Name;
    //add button
    Button addVan;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vans);

        getSupportActionBar().setTitle("Vans");
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionManagement sessionManagement = new SessionManagement(OwnerVans.this);
        Name = sessionManagement.getUserName();

       // Toast.makeText(OwnerVans.this, Name,Toast.LENGTH_SHORT).show();

        //initializing the vehiclelist
        VanList = new ArrayList<>();

        loadVehicles();


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

                                VanList.add(new OwnerVansProduct(
                                        products.getString("vehicleNo"),
                                        products.getString("NoOfSeatsAV")
                                ));


                            }

                            //creating recyclerview adapter
                            OwnerVansProductAdapter adapter = new OwnerVansProductAdapter(OwnerVans.this, VanList);
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                startActivity(new Intent(this,OwnerNotification.class));
                return true;

            case R.id.reqest:
                startActivity(new Intent(this,OwnerRequest.class));
                return true;

            case R.id.top_profile:
                startActivity(new Intent(this,OwnerAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(OwnerVans.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerVans.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }
    public void btn_add_Vans (View view) {
        startActivity(new Intent(getApplicationContext(),OwnerVansAdd.class));
    }




}

