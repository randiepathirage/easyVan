package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.e.esayVan.Owner.Owner;
import com.e.esayVan.Parent.Parent;
import com.e.esayVan.Parent.ParentRequest;

public class MainActivity extends AppCompatActivity {
    Button btnRequest,login,signup;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("News Feed");

        btnRequest=findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ParentRequest.class);
                startActivity(i);
            }
        });

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
            }
        });
        //TODO remove select user

    }

    protected void onStart(){
        super.onStart();

        //check if user is logged in
        //if user is logged in --> move to home

        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        String userRole = sessionManagement.getSession();

        if(userRole != null){
            if(userRole.equals("driver")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,Driver.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if(userRole.equals("parent")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this, Parent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if(userRole.equals("admin")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this,Admin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            if(userRole.equals("owner")) {
                //user role logged in and so move to home
                Intent intent = new Intent(MainActivity.this, Owner.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
;
        }
    }
}
