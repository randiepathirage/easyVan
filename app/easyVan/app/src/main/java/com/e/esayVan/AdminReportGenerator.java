package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminReportGenerator extends AppCompatActivity {

    Spinner spinnerowners;
    ArrayList<String> ownerlist = new ArrayList<>();
    ArrayAdapter<String> oadapter;
    RequestQueue requestQueue;
    String user, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report_generator);
        getSupportActionBar().setTitle("Report Generator");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.reportGenerator);
        requestQueue = Volley.newRequestQueue(this);
        spinnerowners = findViewById(R.id.spinnerowners);
        HttpsTrustManager.allowAllSSL();
        String url = "https://10.0.2.2/easyvan/owner_spinner.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("owners");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String username = jsonObject.optString("username");
                        ownerlist.add(username);
                        oadapter = new ArrayAdapter<>(AdminReportGenerator.this, android.R.layout.simple_spinner_item, ownerlist);
                        oadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerowners.setAdapter(oadapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        requestQueue.add(jsonObjectRequest);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.manage:
                        Intent m = new Intent(getApplicationContext(), AdminManage.class);
                        startActivity(m);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(), AdminVerify.class);
                        startActivity(v);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.reportGenerator:
                        return true;

                    case R.id.newsfeed:
                        Intent n = new Intent(getApplicationContext(), AdminNewsfeed.class);
                        startActivity(n);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    public boolean checkrole(final String username) {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/checkuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("user is an owner")) {
                            c = "y";
                        } else {
                            c = "n";
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminReportGenerator.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        if (c == "y")
            return true;
        else
            return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(AdminReportGenerator.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminReportGenerator.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;


            case R.id.switchowner:
                SessionManagement sessionManagementr = new SessionManagement(AdminReportGenerator.this);
                user = sessionManagementr.getUserName();

                if(checkrole(user)==true)
                {
                    Intent ointent = new Intent(getApplicationContext(),OwnerManage.class);
                    startActivity(ointent);
                    return true;
                }
                else{
                    Toast.makeText(AdminReportGenerator.this,"You are not an owner.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(AdminManage.this,c, Toast.LENGTH_SHORT).show();
                }
        }

        return true;
    }
}