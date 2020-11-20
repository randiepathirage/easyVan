package com.e.esayVan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {

    Button btnforgot;
    EditText edEmail;
    String email;
    String URL = "http://10.0.2.2/easyvan/forgetpassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setTitle("Forgot Password");


        edEmail = (EditText)findViewById(R.id.ed_email);
        btnforgot = (Button)findViewById(R.id.btnForgot);

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edEmail.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Enter your email", Toast.LENGTH_SHORT).show();

                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        String mail = object.getString("mail");
                                        if(mail.equals("send")){
                                            Toast.makeText(ForgotPassword.this, "Email successfully send", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(ForgotPassword.this,response, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ForgotPassword.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> forgotparams = new HashMap<>();
                            forgotparams.put("email",email);
                            return forgotparams;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

    }



}