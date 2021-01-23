package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminViewSchoolVan extends AppCompatActivity {

    TextView tvvnum,tvvmodel,tvvdnic,tvvonic,tvvtseats,tvvaseats,tvvstart,tvvinsnum,tvvlnum;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_school_van);
        getSupportActionBar().setTitle("View School Van Details");


        //Initializing Views
        tvvnum = findViewById(R.id.txtvnum);
        tvvmodel = findViewById(R.id.txtvmodel);
        tvvdnic = findViewById(R.id.txtvdnic);
        tvvonic = findViewById(R.id.txtvonic);
        tvvtseats = findViewById(R.id.txtvtseats);
        tvvaseats = findViewById(R.id.txtvaseats);
        tvvstart = findViewById(R.id.txtvstart);
        tvvinsnum = findViewById(R.id.txtvinsnum);
        tvvlnum = findViewById(R.id.txtvlnum);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvvnum.setText(AdminSchoolVans.adminVanArrayList.get(position).getNum());
        tvvmodel.setText(AdminSchoolVans.adminVanArrayList.get(position).getModel());
        tvvdnic.setText(AdminSchoolVans.adminVanArrayList.get(position).getDnic());
        tvvonic.setText(AdminSchoolVans.adminVanArrayList.get(position).getOnic());
        tvvtseats.setText(AdminSchoolVans.adminVanArrayList.get(position).getTnseats());
        tvvaseats.setText(AdminSchoolVans.adminVanArrayList.get(position).getNoseats());
        tvvstart.setText(AdminSchoolVans.adminVanArrayList.get(position).getStart());
        tvvinsnum.setText(AdminSchoolVans.adminVanArrayList.get(position).getInno());
        tvvlnum.setText(AdminSchoolVans.adminVanArrayList.get(position).getLicno());


    }
}
