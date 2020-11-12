package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OwnerVans extends AppCompatActivity {
    private Menu menu;
    //View van detail
    // OwnerVanAdapter + OwnerVanProduct + OwnerVanProductAdapter
    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/Api.php";

    //a list to store all the vehicles
    List<OwnerVanProduct> vehicleList;
    OwnerVanProductAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
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

            case R.id.account:
                startActivity(new Intent(this,OwnerAccount.class));
                return true;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vans);
        getSupportActionBar().setTitle("Van");

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the vehiclelist
        vehicleList = new ArrayList<>();

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
                                JSONObject vehicle=array.getJSONObject(i);

                                vehicleList.add(new OwnerVanProduct(
                                        vehicle.getString("username"),
                                        vehicle.getString("password")
                                ));

                            }

                            //creating recyclerview adapter
                            OwnerVanProductAdapter adapter = new OwnerVanProductAdapter(OwnerVans.this, vehicleList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerVans.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}