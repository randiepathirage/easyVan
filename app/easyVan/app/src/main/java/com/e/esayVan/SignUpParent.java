package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class SignUpParent extends AppCompatActivity {
    EditText firstName,middleName,lastName,NICNo,username,password,address,contactNo,email,confirmPassword;
    RadioGroup radioGroup;
    RadioButton radioParent,radioOwner;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_parent);
        getSupportActionBar().setTitle("Sign Up");

        firstName=(EditText)findViewById(R.id.edtFirstname);
        middleName=(EditText)findViewById(R.id.edtMiddlename);
        lastName=(EditText)findViewById(R.id.edtLastname);
        NICNo=(EditText)findViewById(R.id.edtNic);
        username=(EditText)findViewById(R.id.edtUsername);
        password=(EditText)findViewById(R.id.edtPassword);
        confirmPassword=(EditText)findViewById(R.id.edtConfirmPassword);
        address=(EditText)findViewById(R.id.edtAddress);
        contactNo=(EditText)findViewById(R.id.edtContactNumber);
        email=(EditText)findViewById(R.id.edtEmail);


        radioParent=(RadioButton)findViewById(R.id.radioParent);
        radioOwner=(RadioButton)findViewById(R.id.radioOwner);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        //validation
       /* awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.edtFirstname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.edtMiddlename,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.edtLastname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.edtPassword,
                ".{6,}",R.string.invalid_password);

        awesomeValidation.addValidation(this,R.id.edtConfirmPassword,
                R.id.edtPassword,R.string.invalid_confirm_password);*/
}
    public void onReg(View view){

        String user_role;
        if(radioParent.isChecked()){
            user_role="parent";
        } else{
            user_role="owner";
        }


        String Str_firstName= username.getText().toString();
        String str_middleName= middleName.getText().toString();
        String str_lastName= lastName.getText().toString();
        String str_NICNo= NICNo.getText().toString();
        String str_username= username.getText().toString();
        String str_password= password.getText().toString();
        String str_address= address.getText().toString();
        String str_contactNo= contactNo.getText().toString();
        String str_email=email.getText().toString();

        String type = "register";

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,Str_firstName,str_middleName,
                str_lastName,str_NICNo,str_username,str_password,str_address,
                str_contactNo,str_email,user_role);
    }

}