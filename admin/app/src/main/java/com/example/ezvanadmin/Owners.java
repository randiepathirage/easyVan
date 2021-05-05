package com.example.ezvanadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Owners extends AppCompatActivity {
    private Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners);
        getSupportActionBar().setTitle("Owners");

        btnadd=(Button)findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(Owners.this,addnewOwner.class);
                startActivity(addIntent);
            }
        });
    }
}