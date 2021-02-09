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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParentRequest extends AppCompatActivity {

    EditText edtchildFirstName,edtchildLastName,edtschool,edtgrade,edtpickupLoc,edtdropOffLoc;
    String childNo,firstName,lastName,grade,school,pick,drop,nic;;
    String URL_REQ="";
    String URL_VIEW="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_request);
        getSupportActionBar().setTitle("Request");

        edtchildFirstName=findViewById(R.id.edtChildFirstName);
        edtchildLastName=findViewById(R.id.edtChildLastName);
        edtschool=findViewById(R.id.edtSchool);
        edtgrade=findViewById(R.id.edtGrade);
        edtpickupLoc=findViewById(R.id.edtPickupLoc);
        edtdropOffLoc=findViewById(R.id.editDropLoc);

        childNo=getIntent().getStringExtra("childNo");



        if(childNo.equals("0")){

            //if child is not registered before

        }else{
            //if child is registered in the system


            nic=getIntent().getStringExtra("nic");
            firstName=getIntent().getStringExtra("firstName");
            lastName=getIntent().getStringExtra("lastName");
            grade=getIntent().getStringExtra("grade");
            school=getIntent().getStringExtra("school");
            pick=getIntent().getStringExtra("pick");
            drop=getIntent().getStringExtra("drop");

            edtchildFirstName.setText(firstName);
            edtchildLastName.setText(lastName);
            edtdropOffLoc.setText(drop);
            edtpickupLoc.setText(pick);
            edtschool.setText(school);
            edtgrade.setText(grade);

        }
        
    }

    public void sendReq(View view) {

        final String strFirstName = edtchildFirstName.getText().toString();
        final String strLastName=edtchildLastName.getText().toString();
        final String strSchool = edtschool.getText().toString();
        final String strGrade = edtgrade.getText().toString();
        final String strPickupLoc = edtpickupLoc.getText().toString();
        final String strDropOffLoc = edtdropOffLoc.getText().toString();

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

                params.put("nic",nic);
                params.put("fristName",strFirstName);
                params.put("lastName",strLastName);
                params.put("school",strSchool);
                params.put("grade",strGrade);
                params.put("pick",strPickupLoc);
                params.put("drop",strDropOffLoc);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentRequest.this);
        requestQueue.add(request);

    }
}
