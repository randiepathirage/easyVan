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

public class AdminUpdateOwner extends AppCompatActivity {

    EditText edfname,edlname,edcontact,edusername,edaddress,edemail;
    String ownernic;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_owner);
        getSupportActionBar().setTitle("Update Owner Details");

        edfname = findViewById(R.id.ed_ofn);
        edlname = findViewById(R.id.ed_oln);
        edcontact = findViewById(R.id.ed_ocontact);
        edusername = findViewById(R.id.ed_ousername);
        edaddress = findViewById(R.id.ed_oaddress);
        edemail = findViewById(R.id.ed_oemail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        ownernic = AdminOwners.adminOwnerArrayList.get(position).getoNic();
        edfname.setText(AdminOwners.adminOwnerArrayList.get(position).getoFname());
        edlname.setText(AdminOwners.adminOwnerArrayList.get(position).getoLname());
        edcontact.setText(AdminOwners.adminOwnerArrayList.get(position).getoContact());
        edaddress.setText(AdminOwners.adminOwnerArrayList.get(position).getoAddress());
        edusername.setText(AdminOwners.adminOwnerArrayList.get(position).getoUsername());
        edemail.setText(AdminOwners.adminOwnerArrayList.get(position).getoEmail());

    }

    private Boolean validateName(){
        String val_firstName= edfname.getText().toString();
        String val_lastName= edlname.getText().toString();

        if(val_firstName.isEmpty() & val_lastName.isEmpty()){
            edfname.setError("This field cannot be empty");
            edlname.setError("This field cannot be empty");
            return false;
        }
        else if(val_lastName.isEmpty()){
            edfname.setError("This field cannot be empty");
            edfname.setError(null);
            return false;
        }
        else if(val_firstName.isEmpty()){
            edfname.setError("This field cannot be empty");
            edlname.setError(null);
            return false;
        }
        else{
            edfname.setError(null);
            edlname.setError(null);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val_username= edusername.getText().toString();
        String noWhitespaces="\\A\\w{4,20}\\z";

        if(val_username.isEmpty() ){
            edusername.setError("This field cannot be empty");
            return false;
        }
        else if (!val_username.matches(noWhitespaces)) {
            edusername.setError("White Spaces are not allowed");
            return false;
        }
        else{
            edusername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val_email=edemail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val_email.isEmpty()){
            edemail.setError("This field cannot be empty");
            return false;
        }
        else if(!val_email.matches(emailPattern)){
            edemail.setError("Invalid email address");
            return false;
        }
        else{
            edemail.setError(null);
            return true;
        }
    }

    private Boolean validateContactNo() {

        String MobilePattern = "[0-9]{10}";
        String val_contactNo = edcontact.getText().toString();

        if (val_contactNo.isEmpty()) {
            edcontact.setError("This field cannot be empty");
            return false;
        }
        else if (!edcontact.getText().toString().matches(MobilePattern)) {

            edcontact.setError("please insert valid mobile number");
            return false;
        }else {
            edcontact.setError(null);
            return true;
        }
    }



    private Boolean validateAddress(){
        String val_address= edaddress.getText().toString();

        if(val_address.isEmpty()){
            edaddress.setError("This field cannot be empty");
            return false;
        }
        else{
            edaddress.setError(null);
            return true;
        }
    }

    public void btn_updateowner(View view) {

        final String NIC_no = ownernic;
        final String first_name = edfname.getText().toString();
        final String last_name = edlname.getText().toString();
        final String address = edaddress.getText().toString();
        final String contact_no = edcontact.getText().toString();
        final String username = edusername.getText().toString();
        final String email = edemail.getText().toString();


        if(!validateName()|!validateUsername() |!validateContactNo()|!validateEmail()|!validateAddress()){
            return;
        }



        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/updateuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AdminUpdateOwner.this,"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),AdminOwners.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdminUpdateOwner.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> ownerparams = new HashMap<String,String>();

                ownerparams.put("NIC_no",NIC_no);
                ownerparams.put("contact_no",contact_no);
                ownerparams.put("last_name",last_name);
                ownerparams.put("first_name",first_name);
                ownerparams.put("address",address);
                ownerparams.put("username",username);
                ownerparams.put("email",email);

                return ownerparams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminUpdateOwner.this);
        requestQueue.add(request);


    }
}
