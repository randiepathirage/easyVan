package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerReportView extends AppCompatActivity {
    private static final String PRODUCT_URL="https://10.0.2.2/easyvan/reportView.php";
    //a list to store all the vehicles
    List<OwnerReportProduct> ReportList;
    OwnerReportProductAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
    //View for van list
    String Name;
    //add button
    Button addVan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_report_view);
        recyclerView = (RecyclerView) findViewById(R.id.R_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionManagement sessionManagement = new SessionManagement(OwnerReportView.this);
        Name = sessionManagement.getUserName();

        // Toast.makeText(OwnerVans.this, Name,Toast.LENGTH_SHORT).show();

        //initializing the vehiclelist
        ReportList = new ArrayList<>();

        loadVehicles();


    }
    private void loadVehicles() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject products=array.getJSONObject(i);

                                ReportList.add(new OwnerReportProduct(
                                        products.getString("vehicleNo"),
                                        products.getString("type"),
                                        products.getString("amount"),
                                        products.getString("date")
                                ));


                            }

                            //creating recyclerview adapter
                            OwnerReportProductAdapter adapter = new OwnerReportProductAdapter(OwnerReportView.this, ReportList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(OwnerDrivers.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("username",Name);
                // Toast.makeText(OwnerDrivers.this,userName,Toast.LENGTH_LONG).show();
                // params.put("username",userName);
                //  Toast.makeText(OwnerDrivers.this,userName,Toast.LENGTH_SHORT);
                return params;
            }


        };

        Volley.newRequestQueue(this).add(stringRequest);


    }

}