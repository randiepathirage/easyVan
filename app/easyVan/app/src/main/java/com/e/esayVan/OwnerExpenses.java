package com.e.esayVan;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class OwnerExpenses extends AppCompatActivity {

    String userName;
    Spinner spinExp,spinVehicle;
    EditText edtDate;
    //spiner view in vehicle
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;



    //add expenses
    EditText date,amount;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_expenses);


        requestQueue = Volley.newRequestQueue(this);
        spinVehicle = findViewById(R.id.E_spinnerVehicle);
        spinExp =findViewById(R.id.E_spinExpType);

        //date= findViewById(R.id.E_date);



//expenses type spinner
        ArrayAdapter myadapter2 = new ArrayAdapter(OwnerExpenses.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.owner_report_2));
        myadapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinExp.setAdapter(myadapter2);

        //get the session username
        SessionManagement sessionManagement = new SessionManagement(this);
        userName = sessionManagement.getUserName();

        String PRODUCT_URL="http://10.0.2.2/easyvan/spinner.php?parentUsername="+userName;

//vehicle datail load to spiner
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                PRODUCT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("vehicle");

                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vehicleNumber = jsonObject.optString("number");
                        vehicleList.add(vehicleNumber);
                        vehicleAdapter = new ArrayAdapter<>(OwnerExpenses.this, android.R.layout.simple_spinner_item,vehicleList);
                        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinVehicle.setAdapter(vehicleAdapter);

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
        requestQueue.add(jsonObjectRequest);//end of vehicle load spinner


        //selecting date
      edtDate=findViewById(R.id.edtdate);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

          edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(OwnerExpenses.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=year+"-"+month+"-"+day;
                        edtDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        /*declar variable*/
        BottomNavigationView bottomNavigationView ;
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        /*Set manage*/
        bottomNavigationView.setSelectedItemId(R.id.expenses);
        /*performe*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.expenses:
                        return true;

                    case R.id.report:
                        Intent j = new Intent(getApplicationContext(),OwnerReport.class);
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
        getSupportActionBar().setTitle("Expenses");
    }
    //add UI data to db via driverBackgroundAdd
    public void ownerAddExpense(View view){

       String str_amount = amount.getText().toString();
        String str_type = spinExp.getSelectedItem().toString();

       String str_date = edtDate.getText().toString();

       String str_vehicleNo = spinVehicle.getSelectedItem().toString();

       if(str_amount.isEmpty()||str_date.isEmpty()){
           Toast.makeText(OwnerExpenses.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
       }

       // String str_amount = "23440";
      //  String str_type = "fuel";
       //  String str_date = "2021-01-21";
       // String str_vehicleNo = "kk 2332";


        String match = "ownerAddExpense";

        OwnerExpensesBackground OwnerExpensesBackground = new OwnerExpensesBackground(this);
        OwnerExpensesBackground.execute(match,str_amount,str_type,str_vehicleNo);

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
                SessionManagement sessionManagement = new SessionManagement(OwnerExpenses.this);
                sessionManagement.removeSession();

                Intent intent = new Intent(OwnerExpenses.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
        }
        return true;
    }
    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }
}