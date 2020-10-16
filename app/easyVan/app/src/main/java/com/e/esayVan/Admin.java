package com.e.esayVan;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
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


}