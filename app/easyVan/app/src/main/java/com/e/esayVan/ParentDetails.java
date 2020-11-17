package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class ParentDetails extends AppCompatActivity {

    String URL="http://10.0.2.2/easyvan/viewParentDetails.php";
    String childNumber=getIntent().getStringExtra("childNumber");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        getSupportActionBar().setTitle("Details");

        loadDetails();
    }

    private void loadDetails() {

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

                params.put("childNumber",childNumber);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
