package com.e.esayVan.Owner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.e.esayVan.R;

public class OwnerVansAdd extends AppCompatActivity {
    EditText firstName,lastName,NICNo,username,password,address,contactNo,email,confirmPassword, licenceNo;
    RadioGroup radioGroup;
    RadioButton radioParent,radioOwner;
    //AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vans_add);
        getSupportActionBar().setTitle("Add Vans");

        firstName=(EditText)findViewById(R.id.edtFirstname);
        lastName=(EditText)findViewById(R.id.edtLastname);
        NICNo=(EditText)findViewById(R.id.edtNic);
        username=(EditText)findViewById(R.id.edtUsername);
        password=(EditText)findViewById(R.id.edtPassword);
        confirmPassword=(EditText)findViewById(R.id.edtConfirmPassword);
        address=(EditText)findViewById(R.id.edtAddress);
        contactNo=(EditText)findViewById(R.id.edtContactNumber);
        email=(EditText)findViewById(R.id.edtEmail);
        licenceNo = (EditText)findViewById(R.id.licence_no);


        radioParent=(RadioButton)findViewById(R.id.radioParent);
        radioOwner=(RadioButton)findViewById(R.id.radioOwner);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

    }
    private Boolean validatelicence_no (){
        String val_address= address.getText().toString();

        if(val_address.isEmpty()){
            address.setError("This field cannot be empty");
            return false;
        }
        else{
            address.setError(null);
            return true;
        }
    }

    public void onReg(View view){

        String user_role;
        user_role="driver";


        String Str_firstName= firstName.getText().toString();
        String str_lastName= lastName.getText().toString();
        String str_NICNo= NICNo.getText().toString();
        String str_username= username.getText().toString();
        String str_password= password.getText().toString();
        String str_address= address.getText().toString();
        String str_contactNo= contactNo.getText().toString();
        String str_email=email.getText().toString();
        String str_licenceNo=licenceNo.getText().toString();
        String str_user_role = user_role;
        String type = "register";

        DriverSignUpBackgroundWorker backgroundWorker=new DriverSignUpBackgroundWorker(this);
        backgroundWorker.execute(type,Str_firstName,
                str_lastName,str_NICNo,str_username,str_password,str_address,
                str_contactNo,str_email,str_user_role,str_licenceNo);
    }



    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }
}
