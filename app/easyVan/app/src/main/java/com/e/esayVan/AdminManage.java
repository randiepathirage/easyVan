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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class AdminManage extends AppCompatActivity {
    private Button btnpar;
    private Button btnown;
    private Button btndri;
    private Button btnsvan;
    private Button btnchild;
    String user;
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage);
        getSupportActionBar().setTitle("Manage");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.manage);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(),AdminVerify.class);
                        startActivity(v);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.reportGenerator:
                        Intent r = new Intent(getApplicationContext(),AdminReportGenerator.class);
                        startActivity(r);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newsfeed:
                        Intent n = new Intent(getApplicationContext(),AdminNewsfeed.class);
                        startActivity(n);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        btnpar=(Button)findViewById(R.id.btnPar);
        btnpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parIntent = new Intent(AdminManage.this,AdminParents.class);
                startActivity(parIntent);
            }
        });

        btnown=(Button)findViewById(R.id.btnOwn);
        btnown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ownIntent = new Intent(AdminManage.this,AdminOwners.class);
                startActivity(ownIntent);
            }
        });

        btndri=(Button)findViewById(R.id.btnDri);
        btndri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driIntent = new Intent(AdminManage.this,AdminDrivers.class);
                startActivity(driIntent);
            }
        });

        btnsvan=(Button)findViewById(R.id.btnSvan);
        btnsvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent svanIntent = new Intent(AdminManage.this,AdminSchoolVans.class);
                startActivity(svanIntent);
            }
        });

        btnchild=(Button)findViewById(R.id.btnChild);
        btnchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent childIntent = new Intent(AdminManage.this,AdminChildren.class);
                startActivity(childIntent);
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
                Toast.makeText(AdminManage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                SessionManagement sessionManagement = new SessionManagement(AdminManage.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(AdminManage.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;


            case R.id.switchowner:
                SessionManagement sessionManagementr = new SessionManagement(AdminManage.this);
                user = sessionManagementr.getUserName();

                if(checkrole(user))
                {
                    Intent ointent = new Intent(getApplicationContext(),OwnerManage.class);
                    startActivity(ointent);
                    return true;
                }
                else{
                    Toast.makeText(AdminManage.this,"You are not an owner.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(AdminManage.this,c, Toast.LENGTH_SHORT).show();
                }
        }

        return true;
    }
}