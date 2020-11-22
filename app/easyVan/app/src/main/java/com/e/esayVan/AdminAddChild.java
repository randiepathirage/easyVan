package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class AdminAddChild extends AppCompatActivity {

    EditText txtcfname,txtclname,txtcpnic,txtcgrade,txtcschool,txtcpickoff,txtcdropoff,txtcsdate,txtcvehicle,txtcfee;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_child);
        getSupportActionBar().setTitle("Add Child Details ");

        txtcfname = findViewById(R.id.editcfname);
        txtclname    = findViewById(R.id.editclname);
        txtcpnic  = findViewById(R.id.editpnic);
        txtcgrade  = findViewById(R.id.editgrade);
        txtcschool  = findViewById(R.id.editschool);
        txtcpickoff  = findViewById(R.id.editpickup);
        txtcdropoff  = findViewById(R.id.editdrop);
        txtcsdate  = findViewById(R.id.editsdate);
        txtcvehicle  = findViewById(R.id.editcvehicle);
        txtcfee  = findViewById(R.id.editcfee);


        btn_insert = findViewById(R.id.btn_insertc);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String fname = txtcfname.getText().toString().trim();
        final String lname = txtclname.getText().toString().trim();
        final String pnic = txtcpnic.getText().toString().trim();
        final String school = txtcschool.getText().toString().trim();
        final String grade = txtcgrade.getText().toString().trim();
        final String pick = txtcpickoff.getText().toString().trim();
        final String drop = txtcdropoff.getText().toString().trim();
        final String vehicle = txtcvehicle.getText().toString().trim();
        final String sdate = txtcsdate.getText().toString().trim();
        final String fee = txtcfee.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(fname.isEmpty()){
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(lname.isEmpty()){
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pnic.isEmpty()){
            Toast.makeText(this, "Enter parent NIC number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(vehicle.isEmpty()){
            Toast.makeText(this, "Enter Vehicle Number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(school.isEmpty() || grade.isEmpty() || pick.isEmpty() || drop.isEmpty() || sdate.isEmpty() || fee.isEmpty()){
            Toast.makeText(this, "Some Fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/easyvan/addchild.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(AdminAddChild.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(AdminAddChild.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AdminAddChild.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("parent_NIC_no",pnic);
                    params.put("grade",grade);
                    params.put("school",school);
                    params.put("first_name",fname);
                    params.put("last_name",lname);
                    params.put("pickup_location",pick);
                    params.put("dropoff_location",drop);
                    params.put("vehicle_no",vehicle);
                    params.put("start_date",sdate);
                    params.put("monthly_fee",fee);

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AdminAddChild.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
