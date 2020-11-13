package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class ParentAccount extends AppCompatActivity {

   Button btnMore,btnEdit;
   private TextView username,nic,address,contactNo,email;
    private String strNic,strAddress,strEmail;
    String userName;
    private String strContactNo;


   String URL="http://10.0.2.2/easyvan/viewParentDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_account);
        getSupportActionBar().setTitle("Account");

        username=findViewById(R.id.txtUsername);
        nic=findViewById(R.id.txtNic);
        address=findViewById(R.id.txtAddress);
        contactNo=findViewById(R.id.txtContactNo);
        email=findViewById(R.id.txtEmail);

        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();
        username.setText(userName);

        sendJsonrequest();


        btnMore=(Button)findViewById(R.id.btnMore);
        btnEdit=(Button)findViewById(R.id.btnEdit);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentAccount.this, ParentDetails.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentAccount.this, ParentEdit.class);
                startActivity(intent);
            }
        });

    }
    //load account details
    public void sendJsonrequest(){

        //String url =URL + userName.trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("data");
                    JSONObject collegeData = result.getJSONObject(0);


                    strNic=collegeData.getString("NIC_no");
                    strContactNo=collegeData.getString("contact_no");;
                    strAddress=collegeData.getString("address");
                    strEmail=collegeData.getString("email");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                nic.setText("NIC no: "+strNic);
                address.setText("Address: "+strAddress);
                contactNo.setText("Contact no: "+strContactNo);
                email.setText("Email: "+strEmail);
            }
        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParentAccount.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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