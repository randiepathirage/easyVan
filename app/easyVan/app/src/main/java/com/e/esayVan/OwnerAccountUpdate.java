package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerAccountUpdate extends AppCompatActivity {

    private EditText edtContactNo, edtAddress, edtEmail, edtUsername, edtNic;
    private String strNic, strAddress, strEmail, strContactNo, userName;

    String URL_VIEW = "http://10.0.2.2/easyvan/OwnerAccount.php";
    String URL_UPDATE = "http://10.0.2.2/easyvan/updateAccount.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_account_update);
        getSupportActionBar().setTitle("Edit Profile");

        edtAddress = findViewById(R.id.acc_et_Address);
        edtContactNo = findViewById(R.id.acc_et_ContactNo);
        edtEmail = findViewById(R.id.acc_et_Email);
        edtUsername = findViewById(R.id.acc_et_firstName);

        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        //existing details from the database
        sendJsonrequest();

    }
    //load account details
    public void sendJsonrequest(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_VIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("data");
                    JSONObject collegeData = result.getJSONObject(0);


                    strNic = collegeData.getString("NIC_no");
                    strContactNo = collegeData.getString("contact_no");
                    strEmail = collegeData.getString("email");
                    strAddress = collegeData.getString("address");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                edtAddress.setText(strAddress);
                edtContactNo.setText(strContactNo);
                edtEmail.setText(strEmail);
                edtUsername.setText(userName);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerAccountUpdate.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }){

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

    public void updateAccountDetails(View view) {

        final String username = edtUsername.getText().toString();
        final String contact = edtContactNo.getText().toString();
        final String email = edtEmail.getText().toString();
        final String address = edtAddress.getText().toString();
        final String nic = "123456789";
        final ProgressDialog progressDialog = new ProgressDialog(OwnerAccountUpdate.this);
        progressDialog.setMessage("updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(OwnerAccountUpdate.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), OwnerAccount.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerAccountUpdate.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                params.put("id", nic);
                params.put("name", username);
                params.put("contact", contact);
                params.put("email", email);
                params.put("address", address);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(OwnerAccountUpdate.this);
        requestQueue.add(request);


    }

}