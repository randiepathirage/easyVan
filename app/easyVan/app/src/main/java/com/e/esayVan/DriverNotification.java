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

public class DriverNotification extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    private static final String URL="https://10.0.2.2/easyvan/viewDriverNotification.php";

    String userName;
    //a list to store all the notifications
    List<ParentNotifications> notificationList;
    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_notification);
        getSupportActionBar().setTitle("Notification");

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

    }


    private void loadNotifications() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject notification=array.getJSONObject(i);
                                notificationList.add(new ParentNotifications(
                                        notification.getString("reqId"),
                                        notification.getString("message"),
                                        notification.getString("date"),
                                        notification.getString("time"),
                                        notification.getString("type"),
                                        notification.getString("childId")

                                ));
                                //Toast.makeText(DriverNotification.this,"dfdfdfd", Toast.LENGTH_SHORT).show();
                            }

                            //creating recyclerview adapter
                            ParentNotificationAdapter adapter = new ParentNotificationAdapter(DriverNotification.this,notificationList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DriverNotification.this,error.getMessage(),Toast.LENGTH_SHORT).show();

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

    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.driver_appbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_notification:
                return true;
            case R.id.top_profile:
                Intent m = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(m);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                Intent j = new Intent(getApplicationContext(),DriverPayment.class);
                startActivity(j);
                return true;


            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverNotification.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverNotification.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
}