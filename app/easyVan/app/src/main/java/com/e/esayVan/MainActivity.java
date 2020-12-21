package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    Button btnRequest,login,signup;;

    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/viewParentNewsfeed.php";

    //a list to store all the vehicles
    List<ParentVans> vehicleList;
    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("News Feed");

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
            }
        });


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
                            ParentVansAdapter adapter = new ParentVansAdapter(MainActivity.this, vehicleList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    protected void onStart(){
        super.onStart();

        //check if user is logged in
        //if user is logged in --> move to home

        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        String userRole = sessionManagement.getSession();

        if(userRole != null){
            if(userRole.equals("driver")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,DriverAttendance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if(userRole.equals("parent")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,ParentDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if(userRole.equals("admin")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,AdminManage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            if(userRole.equals("owner")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,OwnerManage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
;
        }
    }
}
