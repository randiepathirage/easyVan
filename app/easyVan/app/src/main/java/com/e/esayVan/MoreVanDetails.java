package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MoreVanDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_van_details);
        getSupportActionBar().setTitle("More Details");
    }

    public void request(View view) {

        SessionManagement sessionManagement = new SessionManagement(MoreVanDetails.this);
        String userRole = sessionManagement.getSession();

        if(userRole!=null){
            Intent i =new Intent(this,ParentRequest.class);
            startActivity(i);
        }
        else{
            Intent i =new Intent(this,ParentLoginBeforeRequest.class);
            startActivity(i);
        }

    }
}