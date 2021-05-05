package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
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

public class Admin extends AppCompatActivity {
    private Button btnmng;
    private Button btnver;
    private Button btngen;
    private Button btnnws;
    String user,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle("Home");

        btnmng=(Button)findViewById(R.id.btnMng);
        btnmng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mngIntent = new Intent(Admin.this,AdminManage.class);
                startActivity(mngIntent);
            }
        });

        btnver=(Button)findViewById(R.id.btnVer);
        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verIntent = new Intent(Admin.this,AdminVerify.class);
                startActivity(verIntent);
            }
        });

        btngen=(Button)findViewById(R.id.btnGen);
        btngen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genIntent = new Intent(Admin.this,AdminReportGenerator.class);
                startActivity(genIntent);
            }
        });

        btnnws=(Button)findViewById(R.id.btnNws);
        btnnws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nwsIntent = new Intent(Admin.this,AdminNewsfeed.class);
                startActivity(nwsIntent);
            }
        });


    }

    public boolean checkrole(final String username) {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/checkuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("user is an owner")){
                            c = "y";
                        }
                        else{
                            c  = "n";
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Admin.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("username",username);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        if(c == "y")
            return true;
        else
            return false;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.admin_appbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(Admin.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(Admin.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;


            case R.id.switchowner:
                SessionManagement sessionManagementr = new SessionManagement(Admin.this);
                user = sessionManagementr.getUserName();

                if(checkrole(user)==true)
                {
                    Intent ointent = new Intent(getApplicationContext(),OwnerManage.class);
                    startActivity(ointent);
                    return true;
                }
                else{
                    Toast.makeText(Admin.this,"You are not an owner.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(AdminManage.this,c, Toast.LENGTH_SHORT).show();
                }
        }


        return true;
    }


}