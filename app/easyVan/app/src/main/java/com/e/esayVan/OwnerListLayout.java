package com.e.esayVan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerListLayout extends AppCompatActivity {
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        update = findViewById(R.id.updatedriver);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mang = new Intent(OwnerListLayout.this, OwnerDriverUpdate.class);
                startActivity(mang);

            }
        });
    }
}
