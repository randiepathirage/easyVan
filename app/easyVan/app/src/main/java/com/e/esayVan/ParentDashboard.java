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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentDashboard extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    private static final String PRODUCT_URL="https://10.0.2.2/easyvan/viewParentNotification.php";

    String userName;
    //a list to store all the notifications
    List<ParentNotifications> notificationList;
    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_notification);
        getSupportActionBar().setTitle("Notifications");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the vehiclelist
        notificationList = new ArrayList<>();

        loadNotifications();


        //bottom navigation
        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_dashboard);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_newsfeed:
                        Intent j = new Intent(getApplicationContext(), ParentNewsfeed.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_location:
                        Intent i = new Intent(getApplicationContext(), ParentLocation.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        Intent k = new Intent(getApplicationContext(), ParentCalendar.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPay.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_dashboard:
                        return true;
                }
                return false;
            }
        });
    }

    private void loadNotifications() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject notification=array.getJSONObject(i);
                                notificationList.add(new ParentNotifications(
                                        notification.getString("message"),
                                        notification.getString("date"),
                                        notification.getString("time"),
                                        notification.getString("type")

                                ));
                           // Toast.makeText(ParentDashboard.this, notification.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                            //creating recyclerview adapter
                            ParentNotificationAdapter adapter = new ParentNotificationAdapter(ParentDashboard.this,notificationList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentDashboard.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("username",userName);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


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
                SessionManagement sessionManagement = new SessionManagement(ParentDashboard.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentDashboard.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}
