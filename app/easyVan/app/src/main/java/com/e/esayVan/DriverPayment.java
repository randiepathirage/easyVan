package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class DriverPayment extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    //a list to store all the child details
    List<PayemntChild> childlist;
    EditText searchView;
    CharSequence search="";
    ChildrenPaymentAdapter adapter;

    //the recyclerview
    RecyclerView recyclerView;
    String userName;
    private static final String VIEW_CHILD_URL="https://10.0.2.2/easyvan/viewChildPayment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_payment);
        getSupportActionBar().setTitle("Payments");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        //view children details
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the childrenlist
        childlist = new ArrayList<PayemntChild>();

        loadChildren();


        //filtering option
        searchView=findViewById(R.id.searchView);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                search=charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    private void loadChildren() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,VIEW_CHILD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject child=array.getJSONObject(i);
                                childlist.add(new PayemntChild(
                                        child.getString("firstName"),
                                        child.getString("lastName"),
                                        child.getString("month")

                                ));

                            }

                            //creating recyclerview adapter
                             adapter = new ChildrenPaymentAdapter(DriverPayment.this,childlist);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DriverPayment.this,error.getMessage(),Toast.LENGTH_SHORT).show();

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
                Intent m = new Intent(getApplicationContext(),DriverNotification.class);
                startActivity(m);
                return true;
            case R.id.top_profile:
                Intent j = new Intent(getApplicationContext(),DriverProfile.class);
                startActivity(j);
                return true;
            case R.id.top_calendar:
                Intent i = new Intent(getApplicationContext(),DriverCalendar.class);
                startActivity(i);
                return true;
            case R.id.top_payment:
                return true;


            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(DriverPayment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(DriverPayment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
}