package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    List<ParentVans> vehicleList;
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


        //bottom navigation
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

                    case R.id.navigation_dashboard:
                        Intent j = new Intent(getApplicationContext(), ParentDashboard.class);
                        startActivity(j);
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
                SessionManagement sessionManagement = new SessionManagement(ParentNewsfeedFragment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentNewsfeedFragment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }

        //loading vehicles
        private void loadVehicles() {
            StringRequest stringRequest=new StringRequest(Request.Method.GET, PRODUCT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONArray array=new JSONArray(response);

                                for(int i=0;i<array.length();i++){
                                    JSONObject vehicle=array.getJSONObject(i);

                                    vehicleList.add(new ParentVans(
                                            vehicle.getInt("no_of_seats_available"),
                                            vehicle.getInt("total_no_of_seats"),
                                            vehicle.getString("model"),
                                            vehicle.getString("type"),
                                            vehicle.getInt("AC_nonAC"),
                                            vehicle.getInt("caretaker"),
                                            vehicle.getString("start_location"),
                                            vehicle.getString("school"),
                                            vehicle.getString("town")

                                    ));

                                }

                                //creating recyclerview adapter
                                ParentVansAdapter adapter = new ParentVansAdapter(ParentNewsfeedFragment.this, vehicleList);
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
