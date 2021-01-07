package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ParentRequest extends AppCompatActivity {

    EditText childName,school,grade,pickupLoc,pickupLink,dropOffLoc,dropOffLink;
    String URL_REQ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_request);
        getSupportActionBar().setTitle("Request");

        childName=findViewById(R.id.edtChildName);
        school=findViewById(R.id.edtSchool);
        grade=findViewById(R.id.edtGrade);
        pickupLoc=findViewById(R.id.edtPickupLoc);
        pickupLink=findViewById(R.id.edtPickupLink);
        dropOffLoc=findViewById(R.id.editDropLoc);
        dropOffLink=findViewById(R.id.edtDropLink);

    }

    public void sendReq(View view) {

        final String strName = childName.getText().toString();
        final String strSchool = school.getText().toString();
        final String strGrade = grade.getText().toString();
        final String strPickupLoc = pickupLoc.getText().toString();
        final String strPickupLink = pickupLink.getText().toString();
        final String strDropOffLoc = dropOffLoc.getText().toString();
        final String strDropOffLink = dropOffLink.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("sending....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, URL_REQ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentRequest.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("name",strName);
                params.put("school",strSchool);
                params.put("grade",strGrade);
                params.put("pick_up",strPickupLoc);
                params.put("pick_link",strPickupLink);
                params.put("drop",strDropOffLoc);
                params.put("drop_link",strDropOffLink);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentRequest.this);
        requestQueue.add(request);

    }
}
