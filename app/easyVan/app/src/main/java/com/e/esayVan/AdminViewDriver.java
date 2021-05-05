package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewDriver extends AppCompatActivity {

    TextView tvnic,tvfname,tvlname,tvcontact,tvaddress,tvusername,tvemail;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_driver);
        getSupportActionBar().setTitle("View Driver Details");


        //Initializing Views
        tvnic = findViewById(R.id.txtdnic);
        tvfname = findViewById(R.id.txtdfname);
        tvlname = findViewById(R.id.txtdlname);
        tvcontact = findViewById(R.id.txtdcontact);
        tvaddress = findViewById(R.id.txtdaddress);
        tvusername = findViewById(R.id.txtdusern);
        tvemail = findViewById(R.id.txtdemail);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvnic.setText(AdminDrivers.adminDriverArrayList.get(position).getNic());
        tvfname.setText(AdminDrivers.adminDriverArrayList.get(position).getFname());
        tvlname.setText(AdminDrivers.adminDriverArrayList.get(position).getLname());
        tvcontact.setText(AdminDrivers.adminDriverArrayList.get(position).getContact());
        tvaddress.setText(AdminDrivers.adminDriverArrayList.get(position).getAddress());
        tvusername.setText(AdminDrivers.adminDriverArrayList.get(position).getUsername());
        tvemail.setText(AdminDrivers.adminDriverArrayList.get(position).getEmail());





    }
}
