package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewUser extends AppCompatActivity {
    EditText fname, lname, nic, contact, email, address, username, password, role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        getSupportActionBar().setTitle("Add New User");
        fname = (EditText)findViewById(R.id.et_fname);
        lname = (EditText)findViewById(R.id.et_lname);
        nic = (EditText)findViewById(R.id.et_nic);
        contact = (EditText)findViewById(R.id.et_contact);
        email = (EditText)findViewById(R.id.et_email);
        address = (EditText)findViewById(R.id.et_address);
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);


    }
public void onReg(View view){
        String str_fname = fname.getText().toString();
        String str_lname = lname.getText().toString();
        String str_nic = nic.getText().toString();
        String str_contact = contact.getText().toString();
        String str_email = email.getText().toString();
        String str_address = address.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_fname, str_lname, str_nic, str_contact, str_email, str_address, str_username, str_password);



}

}