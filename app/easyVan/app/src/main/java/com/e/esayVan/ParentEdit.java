package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ParentEdit extends AppCompatActivity {

    Button btnChangePass;
    private EditText edtContactNo, edtAddress, edtEmail, edtNic, edtUsername;
    private String strNic, strAddress, strEmail, strContactNo, userName;

    String URL_VIEW = "http://10.0.2.2/easyvan/viewParentDetails.php";
    String URL_UPDATE = "http://10.0.2.2/easyvan/updateParentDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_edit);
        getSupportActionBar().setTitle("Edit Profile");

        edtAddress = findViewById(R.id.edtAddress);
        edtContactNo = findViewById(R.id.edtContactNo);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtNic = findViewById(R.id.edtNic);

        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        //existing details from the database
        sendJsonrequest();

    }
    //load account details
    public void sendJsonrequest(){

            //String url =URL + userName.trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_VIEW, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray result = jsonObject.getJSONArray("data");
                        JSONObject collegeData = result.getJSONObject(0);


                        strNic = collegeData.getString("NIC_no");
                        strContactNo = collegeData.getString("contact_no");
                        strAddress = collegeData.getString("address");
                        strEmail = collegeData.getString("email");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    edtAddress.setText(strAddress);
                    edtContactNo.setText(strContactNo);
                    edtEmail.setText(strEmail);
                    edtUsername.setText(userName);
                    edtNic.setText(strNic);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ParentEdit.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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

    public void updateDetails(View view) {

        final String nic = edtNic.getText().toString();
        final String username = edtUsername.getText().toString();
        final String contact = edtContactNo.getText().toString();
        final String email = edtEmail.getText().toString();
        final String address = edtAddress.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentEdit.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentEdit.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(ParentEdit.this);
        requestQueue.add(request);


    }

}