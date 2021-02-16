package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ParentDetails extends AppCompatActivity {

    String URL="https://10.0.2.2/easyvan/viewMoreDetails.php";
    String URL_POST="https://10.0.2.2/easyvan/parentRate.php";

    private String strfirstName, strlastName ,strvehicleNo,strstartDate,strmonthlyFee,strownerNIC,strownerContact,strownerLastName,strownerFirstName,strdriverNIC,
            strdriverContact, strdriverLastName, strdriverFirstName, strlicenseNo;
    String childNumber,r,parentNIC,review;
    Button editDates, btnPost;
    RatingBar ratingBar;
    private TextView startDate,MonthlyFee,driverName,driverContact,driverNic,driverLicence,ownerName,ownerContact,ownerNic,name,vehicleNo;
    EditText edtReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        getSupportActionBar().setTitle("Child Details");

        childNumber=getIntent().getStringExtra("childNumber");
        parentNIC=getIntent().getStringExtra("parentNIC");

        name=findViewById(R.id.txtName);
        vehicleNo=findViewById(R.id.txtVehicleNo);
        startDate=findViewById(R.id.txtStartDate);
        MonthlyFee=findViewById(R.id.txtFee);
        driverName=findViewById(R.id.txtDriverName);
        driverContact=findViewById(R.id.txtDriverContact);
        driverNic=findViewById(R.id.txtDriverNic);
        driverLicence=findViewById(R.id.txtDriverLicence);
        ownerName=findViewById(R.id.txtOwnerName);
        ownerContact=findViewById(R.id.txtOnwerContact);
        ownerNic=findViewById(R.id.txtOnwerNic);

        edtReview = findViewById(R.id.edtReview);


        //load child details
        loadDetails();

        //edit days button
        editDates=(Button)findViewById(R.id.btnEditDays);
        editDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentDetails.this,ParentEditDays.class);
                startActivity(intent);

            }
        });


    }

    private void loadDetails() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("data");
                    JSONObject data = result.getJSONObject(0);

                    strfirstName=data.getString("firstName");
                    strlastName=data.getString("lastName");;
                    strvehicleNo=data.getString("vehicleNo");
                    strstartDate=data.getString("startDate");
                    strmonthlyFee=data.getString("monthlyFee");
                    strownerNIC=data.getString("ownerNIC");
                    strownerContact=data.getString("ownerContact");
                    strownerLastName=data.getString("ownerLastName");
                    strownerFirstName=data.getString("ownerFirstName");
                    strdriverNIC=data.getString("driverNIC");
                    strdriverContact=data.getString("driverContact");
                    strdriverLastName=data.getString("driverLastName");
                    strdriverFirstName=data.getString("driverFirstName");
                    strlicenseNo=data.getString("licenseNo");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                name.setText(strfirstName+" "+strlastName);
                vehicleNo.setText("Vehicle No: "+strvehicleNo);
                startDate.setText("Start date: "+strstartDate);
                MonthlyFee.setText("Monthly Fee: "+strmonthlyFee);
                driverName.setText("Name: "+strdriverFirstName+" "+strdriverLastName);
                driverContact.setText("Contact no: "+strdriverContact);
                driverNic.setText("NIC no: "+strdriverNIC);
                driverLicence.setText("License no: "+strlicenseNo);
                ownerName.setText("Name: "+strownerFirstName+" "+strownerLastName);
                ownerContact.setText("Contact no: "+strownerContact);
                ownerNic.setText("NIC no: "+strownerNIC);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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

    public void onClickPost(View view) {

        //get rate bar details
        ratingBar=findViewById(R.id.ratingBar);
        btnPost=findViewById(R.id.btnPost);
        r= String.valueOf(ratingBar.getRating());

        //get the review
        review=edtReview.getText().toString();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentDetails.this, response, Toast.LENGTH_SHORT).show();
                        finish();
                        //progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentDetails.this, "error", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("rate",r);
                params.put("parent_nic_no",parentNIC);
                params.put("driver_nic_no",strdriverNIC);
                params.put("review",review);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentDetails.this);
        requestQueue.add(request);


    }

}
