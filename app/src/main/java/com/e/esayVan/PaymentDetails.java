package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtId,txtAmount,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtId=findViewById(R.id.txtid);
        txtStatus=findViewById(R.id.txtStatus);
        txtAmount=findViewById(R.id.txtAmount);

        //get intent
        Intent intent =getIntent();
        try{
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringArrayExtra("PaymentAmount"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String[] paymentAmounts) {

        try {
            txtId.setText(response.getString("id"));
            txtStatus.setText(response.getString("status"));
            txtAmount.setText(response.getString(String.format("$%s",paymentAmounts)));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}