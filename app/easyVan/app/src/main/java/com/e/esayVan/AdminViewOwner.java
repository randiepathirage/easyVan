package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewOwner extends AppCompatActivity {

    TextView tvnic,tvfname,tvlname,tvcontact,tvaddress;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_owner);


        //Initializing Views
        tvnic = findViewById(R.id.txtnic);
        tvfname = findViewById(R.id.txtfname);
        tvlname = findViewById(R.id.txtlname);
        tvcontact = findViewById(R.id.txtcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvnic.setText(AdminOwners.adminOwnerArrayList.get(position).getNic());
        tvfname.setText(AdminOwners.adminOwnerArrayList.get(position).getFname());
        tvlname.setText(AdminOwners.adminOwnerArrayList.get(position).getLname());
        tvcontact.setText(AdminOwners.adminOwnerArrayList.get(position).getContact());
        tvaddress.setText(AdminOwners.adminOwnerArrayList.get(position).getAddress());





    }
}
