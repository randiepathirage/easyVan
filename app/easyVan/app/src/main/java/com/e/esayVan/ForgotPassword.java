package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {

    Button btnforgot;
    EditText edNic,edEmail;
    String nic_no,e_mail;
    StringRequest stringRequest;
    String URL = "http://10.0.2.2/easyvan/forgetpassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setTitle("Forgot Password");

        btnforgot = (Button)findViewById(R.id.btnForgot);
        edNic = (EditText)findViewById(R.id.ed_nic);
        edEmail = (EditText)findViewById(R.id.ed_email);

    }


    public void onForgot(View view) {
        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nic_no = edNic.getText().toString();
                e_mail = edEmail.getText().toString();

                stringRequest = new StringRequest(Request.Method.POST,"http://10.0.2.2/easyvan/forgetpassword.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("SUCCESS")) {
                            Toast.makeText(ForgotPassword.this, "Email successfully sent please check your mail inbox.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();
                        } else {
                            Toast.makeText(ForgotPassword.this, "Failed.", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPassword.this, "Please Check Connection", Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("NIC_no", nic_no);
                        params.put("email", e_mail);

                        return super.getParams();
                    }
                };
            }
        });
    }
}