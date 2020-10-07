package com.example.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Expense extends AppCompatActivity {

    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        spin = findViewById(R.id.spinexpense);
        ArrayAdapter myadapter = new ArrayAdapter(Expense.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.select_expense));
        myadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(myadapter);
    }
}