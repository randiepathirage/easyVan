package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterCode extends AppCompatActivity {

    String code,email;
    EditText no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        getSupportActionBar().setTitle("Password reset");

        code=getIntent().getStringExtra("code");
        email=getIntent().getStringExtra("email");
        no = findViewById(R.id.edtCode);


    }


    public void submit(View view) {
        String r=no.getText().toString();

        if(r.equals(code)){
            Intent i = new Intent(EnterCode.this,ChangePassword.class);
            i.putExtra("email",email);
            startActivity(i);
        }
        else{
            Toast.makeText(EnterCode.this, "incorrect code", Toast.LENGTH_SHORT).show();
        }




    }
}