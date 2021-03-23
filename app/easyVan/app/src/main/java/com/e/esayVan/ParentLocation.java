package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Map;

public class ParentLocation extends AppCompatActivity {

    String userName;
    String URL_CHILDLOCATION="https://10.0.2.2/easyvan/getchildLocation.php";
    BottomNavigationView bottom_nav;
    ArrayList<String> childName=new ArrayList<>();
    ArrayList<String> longitude=new ArrayList<>();
    ArrayList<String> latitude=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_location);
        getSupportActionBar().setTitle("Location");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        getChildrenLocation();



        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_location);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_newsfeed:
                        Intent i = new Intent(getApplicationContext(), ParentNewsfeed.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_location:
                        return true;

                    case R.id.navigation_calendar:
                        Intent k = new Intent(getApplicationContext(), ParentCalendar.class);
                        startActivity(k);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_Pay:
                        Intent l = new Intent(getApplicationContext(), ParentPay.class);
                        startActivity(l);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.navigation_dashboard:
                        Intent j = new Intent(getApplicationContext(), ParentDashboard.class);
                        startActivity(j);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }


    private void getChildrenLocation() {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_CHILDLOCATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                          JSONObject jsonObject = null;
                      try {

                           jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("data");


                            for(int i=0;i<result.length();i++){
                                JSONObject collegeData = result.getJSONObject(i);
                                childName.add(collegeData.getString("child_name"));
                                longitude.add(collegeData.getString("longitude"));
                               // Toast.makeText(ParentLocationFragment.this,longitude.get(i),Toast.LENGTH_LONG).show();
                                latitude.add(collegeData.getString("latitude"));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //create bunble to send to the fragment
                        Bundle b=new Bundle();
                     //   b.putString("username",userName);
                        b.putStringArrayList("children",childName);
                        b.putStringArrayList("longitude",longitude);
                        b.putStringArrayList("latitude",latitude);

                        //initilaze fragment
                        ParentsMapsFragment fragment=new ParentsMapsFragment();
                        fragment.setArguments(b);
                        //open fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.maps,fragment).commit();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentLocation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("username", userName);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentLocation.this);
        requestQueue.add(request);

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
                SessionManagement sessionManagement = new SessionManagement(ParentLocation.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentLocation.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }
}