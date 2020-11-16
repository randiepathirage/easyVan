package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class OwnerAccount extends AppCompatActivity {

    private  TextView username,nic, contactNo, email, address;
    String userName;
    Button btnEdit;
    private String strNic,strAddress,strEmail, strContactNo;

    String URL="http://10.0.2.2/easyvan/OwnerAccount.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_account);
        getSupportActionBar().setTitle("Account");

        username = findViewById(R.id.txtUsername);
        nic = findViewById(R.id.txtNic);
        contactNo = findViewById(R.id.txtContactNo);
        email = findViewById(R.id.txtEmail);
        address = findViewById(R.id.txtAddress);


        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();
        username.setText(userName);

        //load account details
        sendJsonrequest();
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerAccount.this, OwnerReport.class);
                startActivity(intent);
            }
        });

    }

        //load account details
        public void sendJsonrequest() {

            StringRequest stringRequest = new StringRequest(Request.Method.POST , URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray result = jsonObject.getJSONArray("data");
                        JSONObject collegeData = result.getJSONObject(0);


                        strNic=collegeData.getString("NIC_no");
                        strContactNo=collegeData.getString("contact_no");;
                        strEmail=collegeData.getString("email");
                        strAddress=collegeData.getString("address");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    nic.setText("ID:"+strNic);
                   contactNo.setText("Contact No:"+strContactNo);
                    email.setText("Email:"+strEmail);
                    address.setText("Address:"+strAddress);
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(OwnerAccount.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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



    }
