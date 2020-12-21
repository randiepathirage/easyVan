package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
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

public class MoreVanDetails extends AppCompatActivity {

    String number;
    String URL_DETAILS="http://10.0.2.2/easyvan/moreVanDetails.php";
    private String strNic,strContactNo,strFirstName,strLastName,strDriverNIC,strDriverContact,strDriverLastName,strDriverFirstName,strDriverLicense;
    private float rate = 3.5f;
    TextView ownerName,ownerContact,ownerNIC,driverName,driverNIC,driverContact,driverLicence;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_van_details);
        getSupportActionBar().setTitle("More Details");

        number=getIntent().getStringExtra("number");

        //owner details
        ownerName=findViewById(R.id.txtOwnerName);
        ownerNIC=findViewById(R.id.txtOnwerNic);
        ownerContact=findViewById(R.id.txtOwnerContact);

        //driver details
        driverName=findViewById(R.id.txtDriverName);
        driverNIC=findViewById(R.id.txtDriverNic);
        driverContact=findViewById(R.id.txtDriverContact);
        driverLicence=findViewById(R.id.txtDriverLicence);

        //average rating of the driver
        ratingBar=findViewById(R.id.ratingBar);

        //loading details from database
        loadDetails();
    }

    private void loadDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_DETAILS, new Response.Listener<String>() {
            @Override
         public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("data");
                    JSONObject collegeData = result.getJSONObject(0);


                    strNic =collegeData.getString("NIC_no");
                    strContactNo=collegeData.getString("contact_no");;
                    strFirstName=collegeData.getString("first_name");
                    strLastName=collegeData.getString("last_name");
                    strDriverNIC=collegeData.getString("driverNIC");
                    strDriverContact=collegeData.getString("driverContact");
                    strDriverLastName=collegeData.getString("driverLastName");
                    strDriverFirstName=collegeData.getString("driverFirstName");
                    strDriverLicense=collegeData.getString("licenseNo");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //set values
                ownerName.setText("Name: "+strFirstName+" "+strLastName);
                ownerNIC.setText("NIC No: "+strNic);
                ownerContact.setText("Contact No: "+strContactNo);
                driverName.setText("Name: "+strDriverFirstName+" "+strDriverLastName);
                driverNIC.setText("NIC No: "+strDriverNIC);
                driverContact.setText("Contact No: "+strDriverContact);
                driverLicence.setText("Licence No: "+strDriverLicense);


                //set rating bar value
                ratingBar.setRating(rate);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MoreVanDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                //send vehicle number to the php file
                params.put("number",number);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void request(View view) {

        SessionManagement sessionManagement = new SessionManagement(MoreVanDetails.this);
        String userRole = sessionManagement.getSession();

        if(userRole!=null){
            Intent i =new Intent(this,ParentRequest.class);
            startActivity(i);
        }
        else{
            Intent i =new Intent(this,ParentLoginBeforeRequest.class);
            startActivity(i);
        }

    }
}