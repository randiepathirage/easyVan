package com.example.ezvanadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class newsfeed extends AppCompatActivity {
    private Button btnnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        getSupportActionBar().setTitle("Newsfeed");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.newsfeed);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.manage:
                        Intent m = new Intent(getApplicationContext(),manage.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verify:
                        Intent v = new Intent(getApplicationContext(),verify.class);
                        startActivity(v);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.reportGenerator:
                        Intent r = new Intent(getApplicationContext(),reportGenerator.class);
                        startActivity(r);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newsfeed:
                        return true;
                }
                return false;
            }
        });



        btnnew=(Button)findViewById(R.id.btnNew);
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(newsfeed.this,newArticle.class);
                startActivity(newIntent);
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