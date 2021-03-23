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

public class AdminUpdateChild extends AppCompatActivity {

    EditText edcfname,edclname,edcgrade,edcschool,edcfee,edcpickup,edcvan;
    String cnum,parentnic,pname;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_child);
        getSupportActionBar().setTitle("Update Child Details");

        edcfname = findViewById(R.id.ed_cfname);
        edclname = findViewById(R.id.ed_clname);
        edcgrade = findViewById(R.id.ed_cgrade);
        edcschool = findViewById(R.id.ed_cschool);
        edcfee = findViewById(R.id.ed_cfee);
        edcpickup = findViewById(R.id.ed_cpickup);
        edcvan = findViewById(R.id.ed_cvan);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        cnum = AdminChildren.adminChildArrayList.get(position).getChildnum();
        parentnic = AdminChildren.adminChildArrayList.get(position).getParentNIC();
        edcfname.setText(AdminChildren.adminChildArrayList.get(position).getFname());
        edclname.setText(AdminChildren.adminChildArrayList.get(position).getLname());
        edcschool.setText(AdminChildren.adminChildArrayList.get(position).getSchool());
        edcgrade.setText(AdminChildren.adminChildArrayList.get(position).getGrade());
        edcpickup.setText(AdminChildren.adminChildArrayList.get(position).getPickup());
        edcvan.setText(AdminChildren.adminChildArrayList.get(position).getVehiclenum());
        edcfee.setText(AdminChildren.adminChildArrayList.get(position).getFee());
        pname = AdminChildren.adminChildArrayList.get(position).getPname();


    }

    public void btn_updateData(View view) {

        final String child_no = cnum;
        final String parent_NIC_no = parentnic;
        final String first_name = edcfname.getText().toString();
        final String last_name = edclname.getText().toString();
        final String school = edcschool.getText().toString();
        final String grade = edcgrade.getText().toString();
        final String pickup_location = edcpickup.getText().toString();
        final String vehicle_no = edcvan.getText().toString();
        final String fees = edcfee.getText().toString();
        final String parent_name = pname;




        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/updatechild.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AdminUpdateChild.this,"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),AdminChildren.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdminUpdateChild.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("child_no", child_no);
                params.put("parent_NIC_no", parent_NIC_no);
                params.put("first_name",first_name);
                params.put("last_name",last_name);
                params.put("school",school);
                params.put("grade",grade);
                params.put("pickup_location",pickup_location);
                params.put("vehicle_no",vehicle_no);
                params.put("fees",fees);
                params.put("parent_name", parent_name);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminUpdateChild.this);
        requestQueue.add(request);


    }
}
