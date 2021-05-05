package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OwnerRequest extends AppCompatActivity {
    private Menu menu;
    //spiner variable
    Spinner spin1;
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;
    private static final String PRODUCT_URL="http://10.0.2.2/easyvan/spinner.php";
    ///>>

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);

     //spiner detail
        requestQueue = Volley.newRequestQueue(this);
        spin1 = findViewById(R.id.R_spinnerVehicle);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, PRODUCT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("vehicle");

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vehicleNumber = jsonObject.optString("number");
                        vehicleList.add(vehicleNumber);
                        vehicleAdapter = new ArrayAdapter<>(OwnerRequest.this, android.R.layout.simple_spinner_item,vehicleList);
                        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin1.setAdapter(vehicleAdapter  );

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
        requestQueue.add(jsonObjectRequest);

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
                SessionManagement sessionManagement = new SessionManagement(OwnerRequest.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerRequest.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_request);
        getSupportActionBar().setTitle("Send Notification");
    }

}