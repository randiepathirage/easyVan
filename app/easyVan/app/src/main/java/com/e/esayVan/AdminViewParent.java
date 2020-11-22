package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewParent extends AppCompatActivity {

    TextView tvnic,tvfname,tvlname,tvcontact,tvaddress,tvusername,tvemail;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_parent);
        getSupportActionBar().setTitle("View Parent Details");


        //Initializing Views
        tvnic = findViewById(R.id.txtpnic);
        tvfname = findViewById(R.id.txtpfname);
        tvlname = findViewById(R.id.txtplname);
        tvcontact = findViewById(R.id.txtpcontact);
        tvaddress = findViewById(R.id.txtpaddress);
        tvusername = findViewById(R.id.txtpusern);
        tvemail = findViewById(R.id.txtpemail);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvnic.setText(AdminParents.adminParentArrayList.get(position).getNic());
        tvfname.setText(AdminParents.adminParentArrayList.get(position).getFname());
        tvlname.setText(AdminParents.adminParentArrayList.get(position).getLname());
        tvcontact.setText(AdminParents.adminParentArrayList.get(position).getContact());
        tvaddress.setText(AdminParents.adminParentArrayList.get(position).getAddress());
        tvusername.setText(AdminParents.adminParentArrayList.get(position).getUsername());
        tvemail.setText(AdminParents.adminParentArrayList.get(position).getEmail());





    }
}
