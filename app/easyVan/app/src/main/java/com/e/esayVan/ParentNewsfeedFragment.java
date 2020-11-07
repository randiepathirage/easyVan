package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class ParentNewsfeedFragment extends AppCompatActivity {

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/viewParentNewsfeed.php";

    //a list to store all the vehicles
    List<Vans> vehicleList;
    VansAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_news_feed);
        getSupportActionBar().setTitle("News Feed");


            //getting the recyclerview from xml
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //initializing the vehiclelist
            vehicleList = new ArrayList<>();

            loadVehicles();



        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_newsfeed);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_newsfeed:
                        return true;

                    case R.id.navigation_location:
                        Intent i = new Intent(getApplicationContext(), ParentLocationFragment.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        Intent k = new Intent(getApplicationContext(), ParentCalendarFragment.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPayFragment.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

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

                                    vehicleList.add(new Vans(
                                            vehicle.getString("number"),
                                            vehicle.getInt("no_of_seats_available"),
                                            vehicle.getInt("total_no_of_seats"),
                                            vehicle.getString("model"),
                                            vehicle.getString("type"),
                                            vehicle.getString("permit_no"),
                                            vehicle.getString("image")
                                    ));

                                }

                                //creating recyclerview adapter
                               VansAdapter adapter = new VansAdapter(ParentNewsfeedFragment.this, vehicleList);
                                //setting adapter to recyclerview
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ParentNewsfeedFragment.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

            Volley.newRequestQueue(this).add(stringRequest);
        }



}
