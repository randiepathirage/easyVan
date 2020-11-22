package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewOwner extends AppCompatActivity {

    TextView tvnic,tvfname,tvlname,tvcontact,tvaddress,tvusername,tvemail;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_owner);


        //Initializing Views
        tvnic = findViewById(R.id.txtownernic);
        tvfname = findViewById(R.id.txtownerfname);
        tvlname = findViewById(R.id.txtownerlname);
        tvcontact = findViewById(R.id.txtownercontact);
        tvaddress = findViewById(R.id.txtowneraddress);
        tvusername = findViewById(R.id.txtownerusern);
        tvemail = findViewById(R.id.txtowneremail);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvnic.setText(AdminOwners.adminOwnerArrayList.get(position).getoNic());
        tvfname.setText(AdminOwners.adminOwnerArrayList.get(position).getoFname());
        tvlname.setText(AdminOwners.adminOwnerArrayList.get(position).getoLname());
        tvcontact.setText(AdminOwners.adminOwnerArrayList.get(position).getoContact());
        tvaddress.setText(AdminOwners.adminOwnerArrayList.get(position).getoAddress());
        tvusername.setText(AdminOwners.adminOwnerArrayList.get(position).getoUsername());
        tvemail.setText(AdminOwners.adminOwnerArrayList.get(position).getoEmail());





    }
}
