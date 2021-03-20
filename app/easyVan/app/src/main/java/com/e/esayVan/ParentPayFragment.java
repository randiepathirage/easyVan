package com.e.esayVan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Config;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ParentPayFragment extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    Button payementbtn;
    EditText edtAmount;
    String amount="";
    String userName,childSelected;
    Spinner spinner;
    ArrayList<String> childlist=new ArrayList<>();
    ArrayAdapter<String> childAdapter;
    RequestQueue requestQueue;


    public static final int PAYPAL_REQUEST_CODE=7171;
    private static PayPalConfiguration config= new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalClientIDConfigClass.PAYPAL_CLIENT_ID);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_pay);
        getSupportActionBar().setTitle("Pay fees");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();


        //loading spinner
        String URL="http://10.0.2.2/easyvan/loadSpinner.php?parentUsername="+userName;

        requestQueue= Volley.newRequestQueue(this);

        spinner=findViewById(R.id.spin);


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



        edtAmount=findViewById(R.id.edtAmount);
        payementbtn=findViewById(R.id.paymentbtn);

        //start paypal service
        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        payementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaypalPaymentsMethod();
            }
        });






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

    private void PaypalPaymentsMethod() {

        amount=edtAmount.getText().toString();
        PayPalPayment payment=new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD","van fees",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent=new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Payment Canceled", Toast.LENGTH_SHORT).show();
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("paymentAmount", amount)
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
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