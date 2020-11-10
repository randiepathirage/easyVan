package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class ParentAccount extends AppCompatActivity {

   Button btnMore,btnEdit;
   private TextView username,name,nic,address,contactNo,email;
    private String strName,strFirstName,strLastName,strNic,strAddress,strEmail;
    private String strContactNo;
    private RequestQueue requestQueue;


   String URL="http://10.0.2.2/easyvan/viewParentDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_account);
        getSupportActionBar().setTitle("Account");

        username=findViewById(R.id.txtUsername);
        name=findViewById(R.id.txtName);
        nic=findViewById(R.id.txtNic);
        address=findViewById(R.id.txtAddress);
        contactNo=findViewById(R.id.txtContactNo);
        email=findViewById(R.id.txtEmail);

        requestQueue=Volley.newRequestQueue(this);

        SessionManagement sessionManagement = new SessionManagement(this);
        String userName = sessionManagement.getUserName();
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
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject user=jsonArray.getJSONObject(i);
                                strNic=user.getString("NIC_no");
                                strContactNo=user.getString("contact_no");
                                strLastName=user.getString("last_name");
                                strFirstName=user.getString("first_name");
                                strAddress=user.getString("address");

                                name.setText(strFirstName+" "+strLastName);
                                nic.setText(strNic);
                                address.setText(strAddress);
                                contactNo.setText(strContactNo);
                                //email.setText(strEmail);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        requestQueue.add(request);
    }

}
