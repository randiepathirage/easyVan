package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

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

public class AdminNewsfeed extends AppCompatActivity {
    private Button btnnew;

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/viewParentNewsfeed.php";

    //a list to store all the vehicles
    List<ParentVans> vehicleList;
    //the recyclerview
    RecyclerView recyclerView;
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_newsfeed);
        getSupportActionBar().setTitle("Newsfeed");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.newsfeed);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the vehiclelist
        vehicleList = new ArrayList<>();

        loadVehicles();



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        Intent m = new Intent(getApplicationContext(),AdminManage.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(),AdminVerify.class);
                        startActivity(v);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.reportGenerator:
                        Intent r = new Intent(getApplicationContext(),AdminReportGenerator.class);
                        startActivity(r);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newsfeed:
                        return true;
                }
                return false;
            }
        });



        btnnew=(Button)findViewById(R.id.btnNew);
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(AdminNewsfeed.this,AdminNewArticle.class);
                startActivity(newIntent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.admin_appbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(AdminNewsfeed.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminNewsfeed.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
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

                                vehicleList.add(new ParentVans(
                                        vehicle.getString("number"),
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
                            ParentVansAdapter adapter = new ParentVansAdapter(AdminNewsfeed.this, vehicleList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminNewsfeed.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

}