package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Route extends AppCompatActivity {

    EditText etSource,etDestination;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        go = findViewById(R.id.btn_go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                if(sSource.equals("") && sDestination.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter both locaton",Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        try{
            Uri uri = Uri.parse("https://www/google/co.in/maps/dir/" + sSource + "/" + sDestination);
            Intent i = new Intent(Intent.ACTION_VIEW,uri);
            i.setPackage("com.google.android.apps.maps");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent i =new Intent(Intent.ACTION_VIEW,uri);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}