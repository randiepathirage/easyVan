package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OwnerDrivers extends AppCompatActivity {


        String PRODUCT_URL = "http://10.0.2.2/easyvan/Api.php";
        //a list to store all the products
        List<OwnerDriversProduct> productList;
        //the recyclerview
        RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_owner_drivers);

            //getting the recyclerview from xml
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //initializing the productlist
            productList = new ArrayList<>();

            loadProduct();

            //creating recyclerview adapter
            OwnerDriverProductAdapter adapter = new OwnerDriverProductAdapter(this, productList);

            //setting adapter to recyclerview
            recyclerView.setAdapter( adapter);

        }
        private void loadProduct(){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //converting the string to json array object
                                JSONArray array=new JSONArray(response);

                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject product = array.getJSONObject(i);

                                    //adding the product to product list
                                    productList.add(new OwnerDriversProduct(
                                            product.getString("username"),
                                            product.getString("password")

                                    ) );
                                }

                                //creating adapter object and setting it to recyclerview
                                OwnerDriverProductAdapter adapter = new OwnerDriverProductAdapter(OwnerDrivers.this, productList);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OwnerDrivers.this, error.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
            );
            Volley.newRequestQueue(this).add(stringRequest);
        }
    }



    
