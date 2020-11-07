package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    EditText usernameEt,passwordEt;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt=(EditText)findViewById(R.id.txtusername);
        passwordEt=(EditText)findViewById(R.id.txtpassword);
    }

    public void onLogin(View view){
        String username= usernameEt.getText().toString();
        String password=passwordEt.getText().toString();
        String type="login";


        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        try {
            String a=backgroundWorker.execute(type,username,password).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}