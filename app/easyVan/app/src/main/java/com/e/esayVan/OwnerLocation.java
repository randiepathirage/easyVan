package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Map;

public class OwnerLocation extends AppCompatActivity {

    String userName;
    String URL_VANLOCATION="https://10.0.2.2/easyvan/getVehicleLocation.php";
    ArrayList<String> vehicleNo=new ArrayList<>();
    ArrayList<String> longitude=new ArrayList<>();
    ArrayList<String> latitude=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_location);
        /*declar variable*/
        BottomNavigationView bottomNavigationView ;
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        /*Set manage*/
        bottomNavigationView.setSelectedItemId(R.id.location);

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        getVansLocation();

        /*performe*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.location:
                        return true;

                    case R.id.expenses:
                        Intent j = new Intent(getApplicationContext(),OwnerExpenses.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.manage:
                        Intent l = new Intent(getApplicationContext(),OwnerManage.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calender:
                        Intent k = new Intent(getApplicationContext(),OwnerCalendar.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.report:
                        Intent m = new Intent(getApplicationContext(),OwnerReport.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
        getSupportActionBar().setTitle("Location");

    }

    private void getVansLocation() {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_VANLOCATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;
                        try {

                            jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("data");


                            for(int i=0;i<result.length();i++){
                                JSONObject collegeData = result.getJSONObject(i);
                                vehicleNo.add(collegeData.getString("vehicle_no"));
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
                        b.putStringArrayList("vehicle",vehicleNo);
                        b.putStringArrayList("longitude",longitude);
                        b.putStringArrayList("latitude",latitude);

                        //initilaze fragment
                        OwnerMapsFragment fragment=new OwnerMapsFragment();
                        fragment.setArguments(b);
                        //open fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.ownerMaps,fragment).commit();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerLocation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("username", userName);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(OwnerLocation.this);
        requestQueue.add(request);

    }



    private Menu menu;
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

            case R.id.top_profile:
                startActivity(new Intent(this,OwnerAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(OwnerLocation.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerLocation.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }

}