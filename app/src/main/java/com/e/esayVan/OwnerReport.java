package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Calendar;

public class OwnerReport<webView> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context mCtx;
    private WebView webView;
    //private Menu menu;
    Button bt1,bt2;
    Spinner spin1;
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;
    private static final String PRODUCT_URL="https://10.0.2.2/easyvan/spinner.php";

    RadioGroup expType;
    RadioButton expense, fuel , profit;
    EditText fromDate , toDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_report);


        requestQueue = Volley.newRequestQueue(this);
        spin1 = findViewById(R.id.spinnerVehicle);
/*         bt2.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                Intent mang = new Intent(OwnerReport.this,OwnerReportView.class);
                startActivity(mang);

            }});*/
        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);
        Calendar calendar1=Calendar.getInstance();
        final int year1=calendar1.get(Calendar.YEAR);
        final int month1=calendar1.get(Calendar.MONTH);
        final int day1=calendar1.get(Calendar.DAY_OF_MONTH);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog1=new DatePickerDialog(OwnerReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                        month1=month1+1;
                        String d1=year1+"-"+month1+"-"+day1;
                        fromDate.setText(d1);
                    }
                },year1,month1,day1);
                datePickerDialog1.show();
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog1=new DatePickerDialog(OwnerReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                        month1=month1+1;
                        String d1=year1+"-"+month1+"-"+day1;
                        toDate.setText(d1);
                    }
                },year1,month1,day1);
                datePickerDialog1.show();
            }
        });


        //get UI data DB >>>>>>>>>>>>>>>.
        expType = (RadioGroup)findViewById(R.id.add_van_type);
      //  vehiclePart = (RadioButton)findViewById(R.id.R_vPart);
        fuel = (RadioButton)findViewById(R.id.R_fuel);
      //  vehicleService = (RadioButton)findViewById(R.id.R_v_service);
        expense = (RadioButton)findViewById(R.id.R_licence);
        profit = (RadioButton)findViewById(R.id.R_full);



        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, PRODUCT_URL, null, new Response.Listener<JSONObject>() {
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

    public void genarate(View view) {

        String exType = null;


         if(fuel.isChecked()) {
            exType = "Fuel";
        }

        else if(expense.isChecked()) {
            exType = "expense";
        }
        else if(profit.isChecked()) {
            exType = "profit";
        }

        String str_vehicle = spin1.getSelectedItem().toString();
        String str_exType = exType;
        final String date1 = fromDate.getText().toString();
        final String date2 = toDate.getText().toString();
        Toast.makeText(OwnerReport.this, date1,Toast.LENGTH_SHORT).show();
        Toast.makeText(OwnerReport.this, date2,Toast.LENGTH_SHORT).show();
        Intent mang = new Intent(OwnerReport.this,OwnerReportView.class);
        mang.putExtra("vehicleNo",str_vehicle);
        mang.putExtra("expType",str_exType);
        mang.putExtra("date1",date1);
        mang.putExtra("date2",date2);
        //passing data to OwnerReportView
        startActivity(mang);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //  mang.putExtra("vehicleNo",str_vehicle);
        //  mang.putExtra("expType",str_exType);//passing data to OwnerReportView
        //  mCtx.startActivity(mang);


     /*OwnerReportView OwnerReportView  = new OwnerReportView(OwnerReport.this);
     OwnerReportView.execute(str_vehicle,str_exType);*/


    }


}