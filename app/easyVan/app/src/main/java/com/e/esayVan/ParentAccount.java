package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentAccount extends AppCompatActivity {

   Button btnMore,btnEdit;
   private TextView username,nic,address,contactNo,email;
    private String strNic,strAddress,strEmail;
    String userName;
    private String strContactNo;

    //a list to store all the child details
    List<ParentChild> childlist;

    //the recyclerview
    RecyclerView recyclerView;

   String URL="http://10.0.2.2/easyvan/viewParentDetails.php";
   private static final String VIEW_CHILD_URL="http://10.0.2.2/easyvan/viewChildDetails.php";

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

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();
        username.setText(userName);

        //load account details
        sendJsonrequest();


        //view children details
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the childrenlist
        childlist = new ArrayList<>();

        loadChildren();


        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentAccount.this, ParentEdit.class);
                startActivity(intent);
            }
        });

    }

    private void loadChildren() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,VIEW_CHILD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            for(int i=0;i<array.length();i++){
                                JSONObject child=array.getJSONObject(i);
                                childlist.add(new ParentChild(
                                        child.getString("grade"),
                                        child.getString("school"),
                                        child.getString("firstName"),
                                        child.getString("lastName"),
                                        child.getString("pickupLocation"),
                                        child.getString("dropoffLocation"),
                                        child.getString("vehicleNo"),
                                        child.getString("startDate"),
                                        child.getString("monthlyFee")

                                ));

                            }

                            //creating recyclerview adapter
                            ParentChildrenAdapter adapter = new ParentChildrenAdapter(ParentAccount.this,childlist);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentAccount.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
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