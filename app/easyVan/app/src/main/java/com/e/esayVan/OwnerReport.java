package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OwnerReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    //private Menu menu;
    Button btnView;
    Spinner spin1;
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;
    private static final String PRODUCT_URL_spin="http://10.0.2.2/easyvan/spinner.php";
    //data transfert to db
    Spinner spinner_R_vehicle;
    RadioButton vehicle_part,fuel,license,vehicle_service,full_report ;
    RadioGroup vanType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_report);


        requestQueue = Volley.newRequestQueue(this);
        spin1 = findViewById(R.id.R_spinnerVehicle);

        //getUI data
        vanType = (RadioGroup)findViewById(R.id.R_van_type);
        vehicle_part = (RadioButton)findViewById(R.id.R_vehicle_part);
        fuel = (RadioButton)findViewById(R.id.R_fuel);
        license = (RadioButton)findViewById(R.id.R_licence);
        vehicle_service = (RadioButton)findViewById(R.id.R_vehicle_service);
        full_report = (RadioButton)findViewById(R.id.R_full_report);
        btnView = findViewById(R.id.buttonView);

       /* btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mang = new Intent(OwnerReport.this,OwnerReportView2.class);
                startActivity(mang);

            }});*/



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, PRODUCT_URL_spin, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("vehicle");

                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String vehicleNumber = jsonObject.optString("number");
                    vehicleList.add(vehicleNumber);
                    vehicleAdapter = new ArrayAdapter<>(OwnerReport.this, android.R.layout.simple_spinner_item,vehicleList);
                    vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin1.setAdapter(vehicleAdapter  );

                }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //Toast.makeText(OwnerReport.this, (CharSequence) vehicleAdapter, Toast.LENGTH_LONG).show();
        requestQueue.add(jsonObjectRequest);



        /*declar variable*/
        BottomNavigationView bottomNavigationView ;
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        /*Set manage*/
        bottomNavigationView.setSelectedItemId(R.id.report);
        /*performe*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.report:
                        return true;

                    case R.id.expenses:
                        Intent j = new Intent(getApplicationContext(),OwnerExpenses.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.manage:
                        Intent l = new Intent(getApplicationContext(),OwnerManage.class);
                        startActivity(l);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calender:
                        Intent k = new Intent(getApplicationContext(),OwnerCalendar.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        return true;



                    case R.id.location:
                        Intent m = new Intent(getApplicationContext(),OwnerLocation.class);
                        startActivity(m);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        getSupportActionBar().setTitle("Report");
    }
    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }
    public void GenareteView(View view) {
        //vehicle_part,fuel,license,vehicle_service,full_report
        String ExpType = null;
        if (vehicle_part.isChecked()) {
            ExpType = "vehicle part";
        }
        else if (fuel.isChecked()) {
            ExpType = "fuel";
        }
        else if (license.isChecked()){
            ExpType = "license";
        }
        else if(vehicle_service.isChecked()){
            ExpType = "vehicle service";
        }
        else if(full_report.isChecked()){
            ExpType = "full report";
        }

        String Str_vehicleNO = spin1.getSelectedItem().toString();
        String str_type = ExpType;
        String match = "report";
        /*String str_type = "fuel";
        String Str_vehicleNO = "KK 2332";*/
/*

        Toast.makeText(OwnerReport.this, str_type, Toast.LENGTH_LONG).show();
        Toast.makeText(OwnerReport.this, match, Toast.LENGTH_LONG).show();
        Toast.makeText(OwnerReport.this, Str_vehicleNO, Toast.LENGTH_LONG).show();
*/


        OwnerReportBackground backgroundWorker=new OwnerReportBackground(OwnerReport.this);
        backgroundWorker.execute(match,Str_vehicleNO,
                str_type);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                startActivity(new Intent(this,OwnerNotification.class));
                return true;

            case R.id.reqest:
                startActivity(new Intent(this,OwnerRequest.class));
                return true;

            case R.id.top_profile:
                startActivity(new Intent(this,OwnerAccount.class));
                return true;

            case R.id.logout:
                SessionManagement sessionManagement = new SessionManagement(OwnerReport.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerReport.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    }
