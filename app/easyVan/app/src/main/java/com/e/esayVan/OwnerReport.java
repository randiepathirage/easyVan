package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OwnerReport<webView> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context mCtx;
    private WebView webView;
    //private Menu menu;
    Button bt1,bt2;
    Spinner spin1,reportType;
    String user,c;
    ArrayList<String> vehicleList = new ArrayList<>();
    ArrayAdapter<String> vehicleAdapter;
    RequestQueue requestQueue;
    private static final String PRODUCT_URL="https://10.0.2.2/easyvan/spinner.php";

    RadioGroup expType;
    RadioButton vehiclePart, fuel,vehicleService, license, full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_report);


        requestQueue = Volley.newRequestQueue(this);
        spin1 = findViewById(R.id.spinnerVehicle);
        reportType = findViewById(R.id.spinnerType);
        //expenses type spinner
        ArrayAdapter myadapter2 = new ArrayAdapter(OwnerReport.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.report));
        myadapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        reportType.setAdapter(myadapter2);
/*         bt2.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                Intent mang = new Intent(OwnerReport.this,OwnerReportView.class);
                startActivity(mang);

            }});*/



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

    public boolean checkrole(final String username) {

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, "https://10.0.2.2/easyvan/checkuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("user is an owner")){
                            c = "y";
                        }
                        else{
                            c  = "n";
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerReport.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("username",username);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        if(c == "y")
            return true;
        else
            return false;
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

            case R.id.switchadmin:
                SessionManagement sessionManagementr = new SessionManagement(OwnerReport.this);
                user = sessionManagementr.getUserName();

                if(checkrole(user)==true)
                {
                    Intent ointent = new Intent(getApplicationContext(),AdminManage.class);
                    startActivity(ointent);
                    return true;
                }
                else{
                    Toast.makeText(OwnerReport.this,"You are not an admin.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(AdminManage.this,c, Toast.LENGTH_SHORT).show();
                }
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

        if (vehiclePart.isChecked()) {
            exType = "vehicle part";
        }
        else if(fuel.isChecked()) {
            exType = "fuel";
        }
        else if(vehicleService.isChecked()) {
            exType = "vehicle service";
        }
        else if(license.isChecked()) {
            exType = "licence";
        }
        else if(full.isChecked()) {
            exType = "full";
        }

        String str_vehicle = spin1.getSelectedItem().toString();
        String str_exType = exType;
       /* Toast.makeText(OwnerReport.this, str_vehicle,Toast.LENGTH_SHORT).show();
        Toast.makeText(OwnerReport.this, str_exType,Toast.LENGTH_SHORT).show();
 */
        Intent mang = new Intent(OwnerReport.this,OwnerReportView.class);
        mang.putExtra("vehicleNo",str_vehicle);
        mang.putExtra("expType",str_exType);//passing data to OwnerReportView
        startActivity(mang);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //  mang.putExtra("vehicleNo",str_vehicle);
    //  mang.putExtra("expType",str_exType);//passing data to OwnerReportView
      //  mCtx.startActivity(mang);


     /*OwnerReportView OwnerReportView  = new OwnerReportView(OwnerReport.this);
     OwnerReportView.execute(str_vehicle,str_exType);*/


    }


}