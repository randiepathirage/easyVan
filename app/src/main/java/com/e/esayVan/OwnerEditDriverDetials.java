package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OwnerEditDriverDetials extends AppCompatActivity {
    EditText edvehicleNo,edLIsence,edcontact,edusername,edaddress,edemail;
    String drivernic,username,vehicleNo,LicenseNo,content,email,userName;
    private int position;
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;
    Spinner spinExp,spinVehicle;
    String URL_DELETE="https://10.0.2.2/easyvan/removedriver.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_edit_driver_detials);
        getSupportActionBar().setTitle("Reassign driver");

        requestQueue = Volley.newRequestQueue(this);
        spinVehicle = findViewById(R.id.E_spinnerVehicle);
        spinExp =findViewById(R.id.E_spinExpType);

        username=getIntent().getStringExtra("username");
        vehicleNo=getIntent().getStringExtra("vehicleNo");
        LicenseNo=getIntent().getStringExtra("LicenseNo");
        content=getIntent().getStringExtra("content");
        email=getIntent().getStringExtra("email");

        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        String PRODUCT_URL="http://10.0.2.2/easyvan/spinner.php?parentUsername="+userName;

//vehicle datail load to spiner
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                PRODUCT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("vehicle");

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vehicleNumber = jsonObject.optString("number");
                        vehicleList.add(vehicleNumber);
                        vehicleAdapter = new ArrayAdapter<>(OwnerEditDriverDetials.this, android.R.layout.simple_spinner_item,vehicleList);
                        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinVehicle.setAdapter(vehicleAdapter);

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Toast.makeText(OwnerReport.this, (CharSequence) vehicleAdapter, Toast.LENGTH_LONG).show();
        requestQueue.add(jsonObjectRequest);//end of vehicle load spinner

    }


    public void assignDriver(View view) {

        final String vehicle_No = spinVehicle.getSelectedItem().toString();



        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/ownerassigndriver.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("has a driver")){

                            //ask to delete child's existing school van
                            AlertDialog.Builder builder= new AlertDialog.Builder(OwnerEditDriverDetials.this);
                            builder.setMessage("This vehicle already got a driver do you want to remove driver").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //remove child from the school van
                                    removeDriverVan(vehicle_No);

                                }
                            }).setNegativeButton("cancel",null);

                            AlertDialog alert =builder.create();
                            alert.show();
                        }else{
                            Toast.makeText(OwnerEditDriverDetials.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OwnerEditDriverDetials.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> driverparams = new HashMap<String,String>();

                driverparams.put("vehicleNo",vehicle_No);
                driverparams.put("username",username);

                return driverparams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(OwnerEditDriverDetials.this);
        requestQueue.add(request);


    }

    private void removeDriverVan(final String vehicle_No) {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(OwnerEditDriverDetials.this, "Your driver is removed from the school van",Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerEditDriverDetials.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("no",vehicle_No);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(OwnerEditDriverDetials.this);
        requestQueue.add(request);

    }

}