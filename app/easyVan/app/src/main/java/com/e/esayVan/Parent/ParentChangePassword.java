package com.e.esayVan.Parent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.esayVan.R;

import java.util.HashMap;
import java.util.Map;

public class ParentChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_change_password);
    }


    public void updateDetails(View view) {

    /*    final String nic = edtNic.getText().toString();
        final String username = edtUsername.getText().toString();
        final String contact = edtContactNo.getText().toString();
        final String email = edtEmail.getText().toString();
        final String address = edtAddress.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ParentEdit.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentAccount.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParentEdit.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("id", nic);
                params.put("name", username);
                params.put("contact", contact);
                params.put("email", email);
                params.put("address", address);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ParentEdit.this);
        requestQueue.add(request);*/


    }

}