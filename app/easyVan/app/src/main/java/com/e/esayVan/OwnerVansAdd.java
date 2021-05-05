package com.e.esayVan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerVansAdd extends AppCompatActivity {
    EditText vehicleNo, type, noOfSeats, model, permitNo, caretaker , school , town;
    RadioGroup radioGroup,addVanGroup;
    RadioButton radioAC, radioNonAC ,  addVan , addBus ;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vans_add);
        getSupportActionBar().setTitle("Add Vehicle");

        vehicleNo = (EditText) findViewById(R.id.add_vanNO);
        //type = (EditText) findViewById(R.id.add_type);

        addVanGroup = (RadioGroup)findViewById(R.id.add_van_type);
        addVan = (RadioButton)findViewById(R.id.add_van_van);
        addBus = (RadioButton)findViewById(R.id.add_van_Bus);

        noOfSeats = (EditText) findViewById(R.id.add_noOfSeats);
        model = (EditText) findViewById(R.id.add_model);
        permitNo = (EditText) findViewById(R.id.add_permitNo);
        caretaker = (EditText) findViewById(R.id.add_caretacker);
        school = (EditText) findViewById(R.id.add_school);
        town = (EditText) findViewById(R.id.add_Town);


        radioAC = (RadioButton) findViewById(R.id.add_radioAC);
        radioNonAC = (RadioButton) findViewById(R.id.add_radioNoneAC);
        radioGroup = (RadioGroup) findViewById(R.id.add_radioGroup);

        SessionManagement sessionManagement = new SessionManagement(OwnerVansAdd.this);
        userName = sessionManagement.getUserName();

    }
/*

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

    private Boolean validateContactNo(){
        String val_contactNo= contactNo.getText().toString();

        if(val_contactNo.isEmpty()){
            contactNo.setError("This field cannot be empty");
            return false;
        }
        else{
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


*/

    private Boolean validateVehicleNo() {
        String val_vehicleNo = vehicleNo.getText().toString();

        if (val_vehicleNo.isEmpty()) {
            vehicleNo.setError("This field cannot be empty");
            return false;
        } else {
            vehicleNo.setError(null);
            return true;
        }
    }

    private Boolean validateType() {
        String val_type = type.getText().toString();

        if (val_type.isEmpty()) {
            type.setError("This field cannot be empty");
            return false;
        } else {
            type.setError(null);
            return true;
        }
    }

    private Boolean validateModel() {
        String val_model = model.getText().toString();

        if (val_model.isEmpty()) {
            model.setError("This field cannot be empty");
            return false;
        } else {
            model.setError(null);
            return true;
        }
    }

    private Boolean validateNoOfSeats() {
        String val_noOfSeats = noOfSeats.getText().toString();

        if (val_noOfSeats.isEmpty()) {
            noOfSeats.setError("This field cannot be empty");
            return false;
        } else {
            noOfSeats.setError(null);
            return true;
        }
    }

    private Boolean validatePermitNo() {
        String val_permit = permitNo.getText().toString();

        if (val_permit.isEmpty()) {
            permitNo.setError("This field cannot be empty");
            return false;
        } else {
            permitNo.setError(null);
            return true;
        }
    }

/*    private Boolean validateCaretacker() {
        String val_caretacker = caretaker.getText().toString();

        if (val_caretacker.isEmpty()) {
            caretaker.setError("This field cannot be empty");
            return false;
        } else {
            caretaker.setError(null);
            return true;
        }
    }*/
    private Boolean validateSeats()
    {

        String MobilePattern = "[1-9]{10}";
        String val_contactNo = noOfSeats.getText().toString();

        if (val_contactNo.isEmpty()) {
            noOfSeats.setError("This field cannot be empty");
                if (noOfSeats.getText().toString().matches(MobilePattern)) {
                noOfSeats.setError("please inser valied number");
                }
            return false;

        }
            else {
            noOfSeats.setError(null);
            return true;
        }
    }
    private Boolean validateschool(){
        String val_school = school.getText().toString();

        if (val_school.isEmpty()) {
            school.setError("This field cannot be empty");
            return false;
        } else {
            school.setError(null);
            return true;
        }
    }
    private Boolean validatetown(){
        String val_town = town.getText().toString();

        if (val_town.isEmpty()) {
            town.setError("This field cannot be empty");
            return false;
        } else {
            town.setError(null);
            return true;
        }
    }

// save button

    public void onAddVan(View view) {

        if (!validateNoOfSeats()
             //   | !validateType()
                | !validateModel()
                | !validatePermitNo()
                //| !validateCaretacker()
                | !validateVehicleNo()
                | !validateSeats()
                | !validateschool()
                | !validatetown())
        {
            return;
        }


        String condition;
        if (radioAC.isChecked()) {
            condition = "AC";
        } else {
            condition = "None AC";
        }
        String typeV;
        if (addVan.isChecked()) {
            typeV = "Van";
        } else {
            typeV = "Bus";
        }

       // Toast.makeText(OwnerVansAdd.this, userName, Toast.LENGTH_LONG).show();

        String Str_vehicleNO = vehicleNo.getText().toString();
        String str_type = typeV;
        String str_noOfSeats = noOfSeats.getText().toString();
        String str_model = model.getText().toString();
        String str_permitNo = permitNo.getText().toString();
        String str_caretaker = caretaker.getText().toString();
        String str_school = school.getText().toString();
        String str_town = town.getText().toString();
        String str_condition = condition;
        String str_Ownerusername = userName;
       // Toast.makeText(OwnerVansAdd.this, str_condition, Toast.LENGTH_LONG).show();


        OwnerVanAddBackgroundWorker backgroundWorker=new OwnerVanAddBackgroundWorker(OwnerVansAdd.this);
        backgroundWorker.execute(Str_vehicleNO,
                str_type,str_noOfSeats,str_model,str_permitNo,str_caretaker,
                str_condition,str_Ownerusername,str_school,str_town);
    }


}