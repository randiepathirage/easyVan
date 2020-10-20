package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectUser extends AppCompatActivity {

    Button owner,parent,driver,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        owner = findViewById(R.id.btnsignup_owner);
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpOwner.class);
                startActivity(i);
            }
        });

        parent = findViewById(R.id.btnsignup_parent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpParent.class);
                startActivity(i);
            }
        });
    }
}