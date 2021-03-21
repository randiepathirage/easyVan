package com.e.esayVan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.PHResponse;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.StatusResponse;

public class ParentPayFragment extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Button payementbtn;
    EditText edtAmount;
    String amount="";
    String userName,childSelected,monthSelected;
    Spinner spinner,spinner2;
    ArrayList<String> childlist=new ArrayList<>();
    ArrayAdapter<String> childAdapter;
    RequestQueue requestQueue;
    final static int PAYHERE_REQUEST = 11010;
    String URL_PAY="https://10.0.2.2/easyvan/insertPayment.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_pay);
        getSupportActionBar().setTitle("Pay fees");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();


        //loading spinner to select child
        String URL="https://10.0.2.2/easyvan/loadSpinner.php?parentUsername="+userName;

        requestQueue= Volley.newRequestQueue(this);

        spinner=findViewById(R.id.spin);

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("children");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String childName=jsonObject.optString("child_name");
                                childlist.add(childName);
                                childAdapter=new ArrayAdapter<>(ParentPayFragment.this, android.R.layout.simple_spinner_item,childlist);
                                childAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(childAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentPayFragment.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                childSelected= (String) spinner.getItemAtPosition(i);
                // Toast.makeText(ParentCalendarFragment.this,childSelected,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //loading spinner to select month
        spinner2=findViewById(R.id.spin2);
        ArrayAdapter<CharSequence> monthAdapter=ArrayAdapter.createFromResource(this,R.array.months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(monthAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                monthSelected= (String) spinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtAmount=findViewById(R.id.edtAmount);
        payementbtn=findViewById(R.id.paymentbtn);

        //bottom navigation
        bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_Pay);

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
                        Intent i = new Intent(getApplicationContext(), ParentLocationFragment.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_calendar:
                        Intent k = new Intent(getApplicationContext(), ParentCalendarFragment.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_Pay:
                        return true;

                    case R.id.navigation_dashboard:
                        Intent l = new Intent(getApplicationContext(), ParentDashboard.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }

    public void pay(View view) {

        amount = edtAmount.getText().toString();

        //payhere
        InitRequest req = new InitRequest();
        req.setMerchantId("1216429");       // Your Merchant PayHere ID
        req.setMerchantSecret("8RfjzO7FfzG4vTWJXGsvtD4eXuKuPQt2S8Rl7kxG1N58"); // Your Merchant secret (Add your app at Settings > Domains & Credentials, to get this))
        req.setCurrency("LKR");             // Currency code LKR/USD/GBP/EUR/AUD
        req.setAmount(Double.parseDouble(amount));             // Final Amount to be charged
        req.setOrderId("230000123");        // Unique Reference ID
        req.setItemsDescription("van fees");  // Item description title
        req.setCustom1("This is the custom message 1");
        req.setCustom2("This is the custom message 2");
        req.getCustomer().setFirstName(userName);
        req.getCustomer().setLastName(userName);
        req.getCustomer().setEmail("samanp@gmail.com");
        req.getCustomer().setPhone("+94771234567");
        req.getCustomer().getAddress().setAddress("No.1, Galle Road");
        req.getCustomer().getAddress().setCity("Colombo");
        req.getCustomer().getAddress().setCountry("Sri Lanka");


        Intent intent = new Intent(this, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, req);
        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        startActivityForResult(intent, PAYHERE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYHERE_REQUEST && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) {
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT);
            if (resultCode == Activity.RESULT_OK) {
                String msg;
                if (response != null)
                    if (response.isSuccess()){
                        msg = "Activity result:" + response.getData().toString();
                        updateDatabase();
                    }
                    else
                        msg = "Result:" + response.toString();
                else
                    msg = "Result: no response";
                //Log.d(TAG, msg);
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                //textView.setText(msg);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (response != null)
                    Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "User canceled the request", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateDatabase() {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_PAY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentPayFragment.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentDashboard.class));
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentPayFragment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("amount", amount);
                params.put("childName", childSelected);
                params.put("username", userName);
                params.put("month",monthSelected);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentPayFragment.this);
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
                SessionManagement sessionManagement = new SessionManagement(ParentPayFragment.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(ParentPayFragment.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }

        return true;
    }


}