package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentSelectChild extends AppCompatActivity {

    private static final String VIEW_CHILD_URL="http://10.0.2.2/easyvan/viewChildDetails.php";
    String URL="http://10.0.2.2/easyvan/viewParentDetails.php";
    String ownerID,vehicleNo;
    //a list to store all the child details
    List<ParentChild> childlist;
    String userName;
    String strNic;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_select_child);
        getSupportActionBar().setTitle("Select Child");

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        //load account details
        getParentId();



        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the childrenlist
        childlist = new ArrayList<>();

        //van number and owner id
        vehicleNo=getIntent().getStringExtra("vehicleNo");
        ownerID=getIntent().getStringExtra("ownerID");

        loadChildren();

        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(ParentSelectChild.this,ParentRequest.class);
                intent.putExtra("childNo","0");
                intent.putExtra("nic",strNic);
                intent.putExtra("vehicleNo",vehicleNo);
                intent.putExtra("ownerID",ownerID);
                startActivity(intent);
            }
        });
    }

    private void getParentId() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("data");
                    JSONObject collegeData = result.getJSONObject(0);


                    strNic =collegeData.getString("NIC_no");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentSelectChild.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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

    private void loadChildren() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,VIEW_CHILD_URL,
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
                                        child.getString("childNo")
                                ));
                            }

                            //creating recyclerview adapter
                            ParentChildrenAdapter adapter = new ParentChildrenAdapter(ParentSelectChild.this,childlist,strNic,vehicleNo,ownerID,"request");
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentSelectChild.this,error.getMessage(),Toast.LENGTH_SHORT).show();

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