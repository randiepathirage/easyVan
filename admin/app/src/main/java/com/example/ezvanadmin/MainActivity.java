package com.example.ezvanadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button btnmng;
    private Button btnver;
    private Button btngen;
    private Button btnnws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Dashboard");

        btnmng=(Button)findViewById(R.id.btnMng);
        btnmng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mngIntent = new Intent(MainActivity.this,manage.class);
                startActivity(mngIntent);
            }
        });

        btnver=(Button)findViewById(R.id.btnVer);
        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verIntent = new Intent(MainActivity.this,verify.class);
                startActivity(verIntent);
            }
        });

        btngen=(Button)findViewById(R.id.btnGen);
        btngen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genIntent = new Intent(MainActivity.this,reportGenerator.class);
                startActivity(genIntent);
            }
        });

        btnnws=(Button)findViewById(R.id.btnNws);
        btnnws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nwsIntent = new Intent(MainActivity.this,newsfeed.class);
                startActivity(nwsIntent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                startActivity(new Intent(this,notification.class));
                return true;

        }
        return true;
    }

  
}