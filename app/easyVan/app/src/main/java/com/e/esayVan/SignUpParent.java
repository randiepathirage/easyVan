package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpParent extends AppCompatActivity {
    EditText firstName,middleName,lastName,NICNo,username,password,address,contactNo,email,userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_parent);
        getSupportActionBar().setTitle("Sign Up-parent");

        firstName=(EditText)findViewById(R.id.txt_firstname);
        middleName=(EditText)findViewById(R.id.txtset_middlename);
        lastName=(EditText)findViewById(R.id.txt_lastname);
        NICNo=(EditText)findViewById(R.id.txt_nic);
        username=(EditText)findViewById(R.id.txt_username);
        password=(EditText)findViewById(R.id.txtset_password);
        address=(EditText)findViewById(R.id.txt_address);
        contactNo=(EditText)findViewById(R.id.txt_contactnumber);
        email=(EditText)findViewById(R.id.txtset_email);
        userRole=(EditText)findViewById(R.id.txtset_user_role);

}

    public void onReg(View view){
        String Str_firstName= username.getText().toString();
        String str_middleName= middleName.getText().toString();
        String str_lastName= lastName.getText().toString();
        String str_NICNo= NICNo.getText().toString();
        String str_username= username.getText().toString();
        String str_password= password.getText().toString();
        String str_address= address.getText().toString();
        String str_contactNo= contactNo.getText().toString();
        String str_email=email.getText().toString();
        String user_role=userRole.getText().toString();
        String type = "register";

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,Str_firstName,str_middleName,
                str_lastName,str_NICNo,str_username,str_password,str_address,
                str_contactNo,str_email,user_role);
    }

}