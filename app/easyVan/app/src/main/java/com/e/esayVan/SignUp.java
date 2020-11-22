package com.e.esayVan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText firstName,middleName,lastName,NICNo,username,password,address,contactNo,email,confirmPassword,nic;
    RadioGroup radioGroup;
    RadioButton radioParent,radioOwner;
    //AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");

        firstName=(EditText)findViewById(R.id.edtFirstname);
        lastName=(EditText)findViewById(R.id.edtLastname);
        NICNo=(EditText)findViewById(R.id.edtNic);
        username=(EditText)findViewById(R.id.edtUsername);
        password=(EditText)findViewById(R.id.edtPassword);
        confirmPassword=(EditText)findViewById(R.id.edtConfirmPassword);
        address=(EditText)findViewById(R.id.edtAddress);
        contactNo=(EditText)findViewById(R.id.edtContactNumber);
        email=(EditText)findViewById(R.id.edtEmail);
        nic=(EditText)findViewById(R.id.edtNic);


        radioParent=(RadioButton)findViewById(R.id.radioParent);
        radioOwner=(RadioButton)findViewById(R.id.radioOwner);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

    }

    private Boolean validateName(){
        String val_firstName= firstName.getText().toString();
        String val_lastName= lastName.getText().toString();

        if(val_firstName.isEmpty() & val_lastName.isEmpty()){
            firstName.setError("This field cannot be empty");
            lastName.setError("This field cannot be empty");
            return false;
        }
        else if(val_lastName.isEmpty()){
            lastName.setError("This field cannot be empty");
            firstName.setError(null);
            return false;
        }
        else if(val_firstName.isEmpty()){
            firstName.setError("This field cannot be empty");
            lastName.setError(null);
            return false;
        }
        else{
            firstName.setError(null);
            lastName.setError(null);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val_username= username.getText().toString();
        String noWhitespaces="\\A\\w{4,20}\\z";

        if(val_username.isEmpty() ){
            username.setError("This field cannot be empty");
            return false;
        }
        else if (!val_username.matches(noWhitespaces)) {
            username.setError("White Spaces are not allowed");
            return false;
        }
        else{
            username.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val_email=email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val_email.isEmpty()){
            email.setError("This field cannot be empty");
            return false;
        }
        else if(!val_email.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validateContactNo() {

        String MobilePattern = "[0-9]{10}";
        String val_contactNo = contactNo.getText().toString();

        if (val_contactNo.isEmpty()) {
            contactNo.setError("This field cannot be empty");
            return false;
        } else if (contactNo.getText().toString().matches(MobilePattern)) {
            contactNo.setError("please inser valied mobile number");
            return false;
        } else {
            contactNo.setError(null);
            return true;
        }
    }


    private Boolean validatePassword(){
        String val_password= password.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val_password.isEmpty()){
            password.setError("This field cannot be empty");
            return false;
        }
        else if(!val_password.matches(passwordVal)){
            password.setError("Password is too week");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }

    private Boolean validateConfirmPassword(){
        String val_confirmPassword= confirmPassword.getText().toString();
        String val_password= password.getText().toString();

        if(val_confirmPassword.isEmpty()){
            confirmPassword.setError("This field cannot be empty");
            return false;
        }
        else if(!val_confirmPassword.equals(val_password)){
            confirmPassword.setError("Password doesn't match");
            return false;
        }
        else{
            confirmPassword.setError(null);
            return true;
        }
    }

    private Boolean validateAddress(){
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

    private Boolean validateNic(){
        String val_nic= nic.getText().toString();

        if(val_nic.isEmpty()){
            nic.setError("This field cannot be empty");
            return false;
        }
        else{
            nic.setError(null);
            return true;
        }
    }

    public void onReg(View view){

        if(!validateName()|!validateUsername()|!validatePassword()
                |!validateContactNo()|!validateEmail()|!validateConfirmPassword()|!validateAddress()|!validateNic()){
            return;
        }

        String user_role;
        if(radioParent.isChecked()){
            user_role="parent";
        } else{
            user_role="owner";
        }

        String Str_firstName= firstName.getText().toString();
        String str_lastName= lastName.getText().toString();
        String str_NICNo= NICNo.getText().toString();
        String str_username= username.getText().toString();
        String str_password= password.getText().toString();
        String str_address= address.getText().toString();
        String str_contactNo= contactNo.getText().toString();
        String str_email=email.getText().toString();

        String type = "register";

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,Str_firstName,
                str_lastName,str_NICNo,str_username,str_password,str_address,
                str_contactNo,str_email,user_role);
    }

}
