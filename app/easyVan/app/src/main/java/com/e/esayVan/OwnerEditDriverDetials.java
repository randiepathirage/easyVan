package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class OwnerEditDriverDetials extends AppCompatActivity {
    EditText edvehicleNo,edLIsence,edcontact,edusername,edaddress,edemail;
    String drivernic,username,vehicleNo,LicenseNo,content,email;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_edit_driver_detials);
        getSupportActionBar().setTitle("Update Driver Details");

        edvehicleNo = findViewById(R.id.ed_dfn);
        edLIsence = findViewById(R.id.ed_dln);
        edcontact = findViewById(R.id.ed_dcontact);
        edusername = findViewById(R.id.ed_dusername);
        edaddress = findViewById(R.id.ed_daddress);
        edemail = findViewById(R.id.ed_demail);

        username=getIntent().getStringExtra("username");
        vehicleNo=getIntent().getStringExtra("vehicleNo");
        LicenseNo=getIntent().getStringExtra("LicenseNo");
        content=getIntent().getStringExtra("content");
        email=getIntent().getStringExtra("email");

        edcontact.setText(content);
        edusername.setText(username);
        edemail.setText(email);
        edvehicleNo.setText(vehicleNo);
        edLIsence.setText(LicenseNo);

    }


    public void btn_updateowner(View view) {

        final String vehicle_No = edvehicleNo.getText().toString();
        final String license_no = edLIsence.getText().toString();
        final String contact_no = edcontact.getText().toString();
        final String username = edusername.getText().toString();
        final String email = edemail.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/ownerupdatedriver.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(OwnerEditDriverDetials.this,"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),OwnerManage.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OwnerEditDriverDetials.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> driverparams = new HashMap<String,String>();

                driverparams.put("vehicleNo",vehicle_No);
                driverparams.put("contact_no",contact_no);
                driverparams.put("licenseNo",license_no);
                driverparams.put("username",username);
                driverparams.put("email",email);

                return driverparams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(OwnerEditDriverDetials.this);
        requestQueue.add(request);


    }

}