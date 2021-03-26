package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParentAccepted extends AppCompatActivity {
    EditText editDate;
    TextView fee,vNo;
    String URL_AGREE="https://10.0.2.2/easyvan/parentAgree.php";
    String URL_FEE="https://10.0.2.2/easyvan/getChildFees.php";
    String URL_DISCARD="https://10.0.2.2/easyvan/parentDecline.php";
    String id,vehicleNo,childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_accepted);
        getSupportActionBar().setTitle("Request");

        id=getIntent().getStringExtra("reqId");
        vehicleNo=getIntent().getStringExtra("vehicleNo");
        childId=getIntent().getStringExtra("childId");

        fee=findViewById(R.id.edtFee);
        vNo=findViewById(R.id.edtVno);

        vNo.setText(vehicleNo);
        setFee();

        //selecting date
        editDate=findViewById(R.id.edtDay);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ParentAccepted.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=year+"-"+month+"-"+day;
                        editDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void setFee() {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_FEE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ParentAccepted.this,response,Toast.LENGTH_LONG).show();
                        fee.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentAccepted.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("childId", childId);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentAccepted.this);
        requestQueue.add(request);
    }

    public void agree(View view) {

        final String date = editDate.getText().toString();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_AGREE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentAccepted.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentDashboard.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentAccepted.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("childId", childId);
                params.put("vehicleNo",vehicleNo);
                params.put("date",date);
                params.put("id",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentAccepted.this);
        requestQueue.add(request);

    }

    public void discard(View view) {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_DISCARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentAccepted.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentDashboard.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentAccepted.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("childId", childId);
                params.put("id",id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentAccepted.this);
        requestQueue.add(request);





        Intent intent=new Intent(ParentAccepted.this,ParentDashboard.class);
        startActivity(intent);

    }
}