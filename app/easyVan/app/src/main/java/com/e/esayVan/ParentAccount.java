package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ParentAccount extends AppCompatActivity {

   Button btnMore,btnEdit;
   private TextView firstName,nic,address,contactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_account);
        getSupportActionBar().setTitle("Account");

        firstName=(TextView)findViewById(R.id.txtUsername);
        nic=(TextView)findViewById(R.id.txtNic);
        address=(TextView)findViewById(R.id.txtAddress);
        contactNo=(TextView)findViewById(R.id.txtContactNo);

        btnMore=(Button)findViewById(R.id.btnMore);
        btnEdit=(Button)findViewById(R.id.btnEdit);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentAccount.this, ParentDetails.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentAccount.this, ParentEdit.class);
                startActivity(intent);
            }
        });


    }
}
