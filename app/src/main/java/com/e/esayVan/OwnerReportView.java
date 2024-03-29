package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerReportView extends AppCompatActivity {
    private static final String PRODUCT_URL = "https://10.0.2.2/easyvan/reportView.php";
    private static final String URL_VIEW = "https://10.0.2.2/easyvan/total.php";
    private String Str_amount;
    private TextView Total;
    //a list to store all the vehicles
    List<OwnerReportProduct> ReportList;
    OwnerReportProductAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
    //View for van list
    String Name;
    //add button
    Button addVan;
    /* String vehicle = "KK 2332";
     String expType = "fuel";*/
    String vehicle ;
    String expType,date1,date2;

    private WebView webView;
    //  String vehicle,expType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_report_view);

        vehicle=getIntent().getStringExtra("vehicleNo");
        expType=getIntent().getStringExtra("expType");
        date1=getIntent().getStringExtra("date1");
        date2=getIntent().getStringExtra("date2");

        Total = findViewById(R.id.textView35);


       /* Toast.makeText(OwnerReportView.this, vehicle, Toast.LENGTH_SHORT).show();
        Toast.makeText(OwnerReportView.this, expType, Toast.LENGTH_SHORT).show();*/

        Toast.makeText(OwnerReportView.this,"OK",Toast.LENGTH_SHORT);



        recyclerView = (RecyclerView) findViewById(R.id.R_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessionManagement sessionManagement = new SessionManagement(OwnerReportView.this);
        Name = sessionManagement.getUserName();

        // Toast.makeText(OwnerVans.this, Name,Toast.LENGTH_SHORT).show();

        //initializing the vehiclelist
        ReportList = new ArrayList<>();

        sendJsonrequest();
        loadVehicles();



    }


    private void loadVehicles() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject products = array.getJSONObject(i);

                                ReportList.add(new OwnerReportProduct(
                                        products.getString("vehicleNo"),
                                        products.getString("type"),
                                        products.getString("amount"),
                                        products.getString("date")
                                ));


                            }

                            //creating recyclerview adapter
                            OwnerReportProductAdapter adapter = new OwnerReportProductAdapter(OwnerReportView.this, ReportList);
                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(OwnerDrivers.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //params.put("username",Name);

                params.put("expType", expType);
                params.put("vehicle", vehicle);
                params.put("date1", date1);
                params.put("date2", date2);
                /*   Toast.makeText(OwnerReportView.this,expType,Toast.LENGTH_LONG).show();
                Toast.makeText(OwnerReportView.this,vehicle,Toast.LENGTH_LONG).show();*/
                // params.put("username",userName);
                //  Toast.makeText(OwnerDrivers.this,userName,Toast.LENGTH_SHORT);
                return params;
            }


        };

        Volley.newRequestQueue(this).add(stringRequest);


    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>pdf view>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
/*    public void pdf(View view) {
        Context context = OwnerReportView.this;
        PrintManager printManager = (PrintManager)OwnerReportView.this.getSystemService(context.PRINT_SERVICE);
        PrintDocumentAdapter adapter = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            adapter=webView.createPrintDocumentAdapter();
        }
        String JobName = getString(R.string.app_name) + "Document";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
        }


    }*/
    private void sendJsonrequest() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URL_VIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Total.setText("1000");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("ata");
                    JSONObject collegeData = result.getJSONObject(0);


                    Str_amount = collegeData.getString("amount");
                  /*  strContactNo = collegeData.getString("contact_no");
                    strEmail = collegeData.getString("email");
                    strAddress = collegeData.getString("address");*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Total.setText("total:"+Str_amount);
                Total.setText("2000");
                // Total.setText("1000");
                // Total.setText(Str_amount);
                //   Toast.makeText(OwnerReportView.this,Str_amount,Toast.LENGTH_SHORT);

               /* edtContactNo.setText(strContactNo);
                edtEmail.setText(strEmail);
                edtUsername.setText(userName);*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerReportView.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("vehicle_no", vehicle);

                return params;
            }
        };


    }
}