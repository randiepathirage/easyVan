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

public class AdminOwners extends AppCompatActivity {

    ListView listView;
    AdminOwnerAdapter adapter;
    public static ArrayList<AdminOwnerArray> adminOwnerArrayList = new ArrayList<>();
    String url = "http://10.0.2.2/easyvan/owners.php";
    AdminOwnerArray owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_owners);
        getSupportActionBar().setTitle("Owners");

        listView = findViewById(R.id.myListView);
        adapter = new AdminOwnerAdapter(this,adminOwnerArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(adminOwnerArrayList.get(position).getFname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),AdminViewOwner.class)
                                        .putExtra("position",position));

                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(), AdminUpdateOwner.class)
                                        .putExtra("position",position));

                                break;

                            case 2:

                                deleteData(adminOwnerArrayList.get(position).getNic());
                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });

        retrieveData();


    }

    private void deleteData(final String NIC_no) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/easyvan/deleteuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminOwners.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminOwners.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminOwners.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("NIC_no", NIC_no);
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

                        adminOwnerArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nic = object.getString("NIC_no");
                                    String contact = object.getString("contact_no");
                                    String lname = object.getString("last_name");
                                    String fname = object.getString("first_name");
                                    String address = object.getString("address");
                                    String username = object.getString("username");
                                    String email = object.getString("email");



                                    owner = new AdminOwnerArray(nic,contact,lname,fname,address,username,email);
                                    adminOwnerArrayList.add(owner);
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
                Toast.makeText(AdminOwners.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }

    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

}




