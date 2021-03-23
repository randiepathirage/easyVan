package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class OwnerRespond extends AppCompatActivity {

    String id;
    String URL="https://10.0.2.2/easyvan/viewRequestDetails.php";
    TextView txtchildName,txtparentName,txtparentContact,txtschool,txtgrade,txtpickup,txtdropOff;
    String childFirstName,childLastName,parentName,parentContact,school,grade,pickup,dropOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_respond);
        getSupportActionBar().setTitle("Request");

        id=getIntent().getStringExtra("reqId");
        //Toast.makeText(this,id,Toast.LENGTH_LONG).show();

        txtchildName=findViewById(R.id.childName);
        txtparentName=findViewById(R.id.parentName);
        txtparentContact=findViewById(R.id.parentContact);
        txtschool=findViewById(R.id.school);
        txtgrade=findViewById(R.id.grade);
        txtpickup=findViewById(R.id.pickUp);
        txtdropOff=findViewById(R.id.dropOff);

        viewDetails();
    }

    private void viewDetails() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray result = jsonObject.getJSONArray("data");
                        JSONObject data = result.getJSONObject(0);


                        childFirstName =data.getString("firstName");
                        childLastName=data.getString("lastName");
                        parentName=data.getString("name");;
                        parentContact=data.getString("contactNo");
                        school=data.getString("school");
                        grade=data.getString("grade");
                        pickup=data.getString("pickUp");
                        dropOff=data.getString("dropOff");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    txtchildName.setText("Child's Name: "+childFirstName+" "+childLastName);
                    txtparentName.setText("Parent's Name: "+parentName);
                    txtschool.setText("School: "+school);
                    txtparentContact.setText("Parent's Contact: "+parentContact);
                    txtgrade.setText("Grade: "+grade);
                    txtpickup.setText("Pickup Location: "+pickup);
                    txtdropOff.setText("Dropoff Location: "+dropOff);
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(OwnerRespond.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            })
            {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String,String>();

                    params.put("id",id);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }
}