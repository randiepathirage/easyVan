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

public class AdminChildren extends AppCompatActivity {

    ListView listView;
    AdminChildAdapter adapter;
    public static ArrayList<AdminChildArray> adminChildArrayList = new ArrayList<>();
    String url = "http://10.0.2.2/easyvan/children.php";
    AdminChildArray child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_children);
        getSupportActionBar().setTitle("Children");

        listView = findViewById(R.id.childrenListView);
        adapter = new AdminChildAdapter(this,adminChildArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(adminChildArrayList.get(position).getFname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),AdminViewChild.class)
                                        .putExtra("position",position));

                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(), AdminUpdateChild.class)
                                        .putExtra("position",position));

                                break;

                            case 2:

                                deleteData(adminChildArrayList.get(position).getChildnum());
                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });

        retrieveData();


    }

    private void deleteData(final int child_no) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/easyvan/deletechild.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminChildren.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminChildren.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminChildren.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("child_no", String.valueOf(child_no));
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

                        adminChildArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    int childnum = object.getInt("child_no");
                                    String parentnic = object.getString("parent_NIC_no");
                                    String fname = object.getString("first_name");
                                    String lname = object.getString("last_name");
                                    String school = object.getString("school");
                                    String grade = object.getString("grade");
                                    String pickup = object.getString("pickup_location");
                                    String vehiclenum = object.getString("vehicle_no");
                                    int fee = object.getInt("monthly_fee");
                                    String pname = object.getString("parent_name");



                                    child = new AdminChildArray(childnum,parentnic,fname,lname,school,grade,pickup,vehiclenum,fee,pname);
                                    adminChildArrayList.add(child);
                                    adapter.notifyDataSetChanged();


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
                Toast.makeText(AdminChildren.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

    public void btn_cadd_activity(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

}




