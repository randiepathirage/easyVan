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

public class UserChangePassword extends AppCompatActivity {

    EditText password,confirmPassword,oldPass;
    String URL_UPDATE="https://10.0.2.2/easyvan/userResetPass.php";
    String oldPassword ,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change_password);

        password=findViewById(R.id.edtNewPass);
        confirmPassword=findViewById(R.id.edtConfirmPass);
        oldPass=findViewById(R.id.edtOldPass);

        oldPassword= oldPass.getText().toString();

        //get username from the session
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();
    }

    public void changePass(View view) {

        if(!validatePassword()|!validateConfirmPassword())
        {

        }else{
            updatePassword();
        }
    }

    private Boolean validatePassword(){
        String val_password= password.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=*])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val_password.isEmpty()){
            password.setError("This field cannot be empty");
            return false;
        }
        else if(!val_password.matches(passwordVal)){
            password.setError("Password is too week");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }

    private Boolean validateConfirmPassword(){
        String val_confirmPassword= confirmPassword.getText().toString();
        String val_password= password.getText().toString();

        if(val_confirmPassword.isEmpty()){
            confirmPassword.setError("This field cannot be empty");
            return false;
        }
        else if(!val_confirmPassword.equals(val_password)){
            confirmPassword.setError("Password doesn't match");
            return false;
        }
        else{
            confirmPassword.setError(null);
            return true;
        }
    }

    public void updatePassword() {

        final String val_password= password.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("updating....");
        progressDialog.show();

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UserChangePassword.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParentEdit.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserChangePassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();

                params.put("password", val_password);
                params.put("oldPassword", oldPassword);
                params.put("username", userName);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UserChangePassword.this);
        requestQueue.add(request);


    }
}
