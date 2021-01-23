package com.e.esayVan;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminSchoolVans extends AppCompatActivity {

    ListView vanlistView;
    AdminSchoolVanAdapter vanadapter;
    public static ArrayList<AdminSchoolVanArray> adminVanArrayList = new ArrayList<>();
    String url = "http://10.0.2.2/easyvan/vans.php";
    AdminSchoolVanArray van;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_school_vans);
        getSupportActionBar().setTitle("School Vans List");

        vanlistView = findViewById(R.id.myListView);
        vanadapter = new AdminSchoolVanAdapter(this,adminVanArrayList);
        vanlistView.setAdapter(vanadapter);


        vanlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(adminVanArrayList.get(position).getNum());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),AdminViewSchoolVan.class)
                                        .putExtra("position",position));
                                break;

                            case 1:

                                deleteData(adminVanArrayList.get(position).getNum());
                                break;

                        }

                    }
                });


                builder.create().show();

            }
        });

        retrieveData();


    }

    private void deleteData(final String number) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/easyvan/deletevan.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminSchoolVans.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminSchoolVans.this, "School Van Cannot be Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminSchoolVans.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("number", number);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        adminVanArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String number = object.getString("number");
                                    String model = object.getString("model");
                                    String dnic = object.getString("driver_NIC_no");
                                    String onic = object.getString("owner_NIC_no");
                                    String tnseats = object.getString("total_no_of_seats");
                                    String noseats = object.getString("no_of_seats_available");
                                    String start = object.getString("start_location");
                                    String inno = object.getString("insurance_no");
                                    String licno = object.getString("license_no");



                                    van = new AdminSchoolVanArray(number,model,dnic,onic,tnseats,noseats,start,inno,licno);
                                    adminVanArrayList.add(van);
                                    vanadapter.notifyDataSetChanged();


                                }


                            }


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminSchoolVans.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }


}




