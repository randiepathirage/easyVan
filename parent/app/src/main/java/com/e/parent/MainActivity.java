package com.e.parent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home");

        button=(Button)findViewById(R.id.btnNewsFeed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
        FragmentManager fm=getSupportFragmentManager();
        newsfeedfrag fragment=new newsfeedfrag();
        fm.beginTransaction().replace(R.id.main,fragment).commit();
        }
        });
        }
        }
