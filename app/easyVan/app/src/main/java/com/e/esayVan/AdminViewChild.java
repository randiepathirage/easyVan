package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewChild extends AppCompatActivity {

    TextView tvcnum,tvpnic,tvclname,tvcfname,tvschool,tvpname,tvpickup,tvvan,tvfee,tvgrade;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_child);


        //Initializing Views
        tvcnum = findViewById(R.id.txtcnum);
        tvpnic = findViewById(R.id.txtpnic);
        tvcfname = findViewById(R.id.txtcfname);
        tvclname = findViewById(R.id.txtclname);
        tvschool = findViewById(R.id.txtcschool);
        tvpname = findViewById(R.id.txtcpname);
        tvpickup = findViewById(R.id.txtcpickup);
        tvvan = findViewById(R.id.txtcvan);
        tvfee = findViewById(R.id.txtcfee);
        tvgrade = findViewById(R.id.txtcgrade);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvcnum.setText(AdminChildren.adminChildArrayList.get(position).getChildnum());
        tvpnic.setText(AdminChildren.adminChildArrayList.get(position).getParentNIC());
        tvcfname.setText(AdminChildren.adminChildArrayList.get(position).getFname());
        tvclname.setText(AdminChildren.adminChildArrayList.get(position).getLname());
        tvschool.setText(AdminChildren.adminChildArrayList.get(position).getSchool());
        tvpname.setText(AdminChildren.adminChildArrayList.get(position).getPname());
        tvpickup.setText(AdminChildren.adminChildArrayList.get(position).getPickup());
        tvvan.setText(AdminChildren.adminChildArrayList.get(position).getVehiclenum());
        tvfee.setText(AdminChildren.adminChildArrayList.get(position).getFee());
        tvgrade.setText(AdminChildren.adminChildArrayList.get(position).getGrade());





    }
}
