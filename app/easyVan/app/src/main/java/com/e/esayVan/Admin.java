package com.e.esayVan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Admin extends AppCompatActivity {
    private Button btnmng;
    private Button btnver;
    private Button btngen;
    private Button btnnws;

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
        }

        return true;
    }


}