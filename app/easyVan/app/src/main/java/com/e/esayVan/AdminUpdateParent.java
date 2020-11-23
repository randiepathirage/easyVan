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

public class AdminUpdateParent extends AppCompatActivity {

    EditText edfname,edlname,edcontact,edusername,edaddress,edemail;
    String nic;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_parent);
        getSupportActionBar().setTitle("Update Parent Details");

        edfname = findViewById(R.id.ed_pfn);
        edlname = findViewById(R.id.ed_pln);
        edcontact = findViewById(R.id.ed_pcontact);
        edusername = findViewById(R.id.ed_pusername);
        edaddress = findViewById(R.id.ed_paddress);
        edemail = findViewById(R.id.ed_pemail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        nic = AdminParents.adminParentArrayList.get(position).getNic() ;
        edfname.setText(AdminParents.adminParentArrayList.get(position).getFname());
        edlname.setText(AdminParents.adminParentArrayList.get(position).getLname());
        edcontact.setText(AdminParents.adminParentArrayList.get(position).getContact());
        edaddress.setText(AdminParents.adminParentArrayList.get(position).getAddress());
        edusername.setText(AdminParents.adminParentArrayList.get(position).getUsername());
        edemail.setText(AdminParents.adminParentArrayList.get(position).getEmail());




    }

    public void btn_updateparent(View view) {

        final String NIC_no = nic;
        final String first_name = edfname.getText().toString();
        final String last_name = edlname.getText().toString();
        final String address = edaddress.getText().toString();
        final String contact_no = edcontact.getText().toString();
        final String username = edusername.getText().toString();
        final String email = edemail.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/easyvan/updateuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AdminUpdateParent.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),AdminParents.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdminUpdateParent.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("NIC_no",NIC_no);
                params.put("contact_no",contact_no);
                params.put("first_name",first_name);
                params.put("last_name",last_name);
                params.put("address",address);
                params.put("username",username);
                params.put("email",email);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminUpdateParent.this);
        requestQueue.add(request);


    }
}
