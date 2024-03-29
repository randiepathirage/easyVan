package com.e.esayVan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DriverSignUp extends AppCompatActivity {
        EditText firstName,lastName,NICNo,username,password,address,contactNo,email,confirmPassword, licenceNo;
        String userName;
        Spinner spinVehicle;
        //spiner view in vehicle
        ArrayList<String> vehicleList = new ArrayList<>();
        ArrayAdapter<String> vehicleAdapter;
        RequestQueue requestQueue;
        //AwesomeValidation awesomeValidation;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_driver_sign_up);
                getSupportActionBar().setTitle("Add Driver");

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


                //get the session username
           SessionManagement sessionManagement = new SessionManagement(this);
           userName = sessionManagement.getUserName();

           requestQueue = Volley.newRequestQueue(this);
              spinVehicle = findViewById(R.id.type);

                String PRODUCT_URL="http://10.0.2.2/easyvan/spinner.php?parentUsername="+userName;

//vehicle datail load to spiner
                       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                               PRODUCT_URL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                                JSONArray jsonArray = null;
                                try {
                                        jsonArray = response.getJSONArray("vehicle");

                                        for(int i=0; i<jsonArray.length();i++){
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                String vehicleNumber = jsonObject.optString("number");
                                                vehicleList.add(vehicleNumber);
                                                vehicleAdapter = new ArrayAdapter<>(DriverSignUp.this, android.R.layout.simple_spinner_item,vehicleList);
                                                vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinVehicle.setAdapter(vehicleAdapter);

                                        }
                                }
                                catch (JSONException e) {
                                        e.printStackTrace();
                                }
                        }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                });
                //Toast.makeText(OwnerReport.this, (CharSequence) vehicleAdapter, Toast.LENGTH_LONG).show();
                requestQueue.add(jsonObjectRequest);//end of vehicle load spinner




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

        private Boolean validateName(){
                String val_firstName= firstName.getText().toString();
               // String val_lastName= lastName.getText().toString();

                if(val_firstName.isEmpty() ){
                        firstName.setError("This field cannot be empty");
                      //  lastName.setError("This field cannot be empty");
                        return false;
                }
//                else if(val_lastName.isEmpty()){
//                        lastName.setError("This field cannot be empty");
//                        firstName.setError(null);
//                        return false;
//                }
                else if(val_firstName.isEmpty()){
                        firstName.setError("This field cannot be empty");
                      //  lastName.setError(null);
                        return false;
                }
                else{
                        firstName.setError(null);
                       // lastName.setError(null);
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
                String val_contactNo = contactNo.getText().toString();
                String MobilePattern = "[0-9]{10}";
               // String val_contactNo = contactNo.getText().toString();
                if (val_contactNo.isEmpty()) {
                        contactNo.setError("This field cannot be empty");
                        return false;}
                 else if (!val_contactNo.matches(MobilePattern)){
                        contactNo.setError("This Not valid");
                        return false;
                        }
                 else {
                        contactNo.setError(null);
                        return true;
                }


                /*String MobilePattern = "[0-9]{10}";
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
                }*/
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
        private Boolean validateLicenseNo(){
                String val_license= licenceNo.getText().toString();

                if(val_license.isEmpty()){
                        licenceNo.setError("This field cannot be empty");
                        return false;
                }
                else{
                        licenceNo.setError(null);
                        return true;
                }
        }

        private Boolean validateNIC(){
                String val_NIC= NICNo.getText().toString();
                if(val_NIC.isEmpty()){
                        NICNo.setError("This field cannot be empty");
                        return false;
                }
                else {
                        NICNo.setError(null);
                        return true;
                }

        }


        public void onReg(View view){

                if(!validateName()|!validateUsername()|!validatePassword() |!validateEmail()
                        |!validateContactNo()|!validateConfirmPassword()|!validateAddress()|!validateLicenseNo() | !validateNIC()){
                        return;
                }

                String user_role;
                user_role="driver";


                String Str_firstName= firstName.getText().toString();//
                String str_lastName= lastName.getText().toString();//
                String str_NICNo= NICNo.getText().toString();//
                String str_username= username.getText().toString();//
                String str_password= password.getText().toString();//
                String str_address= address.getText().toString();//
                String str_contactNo= contactNo.getText().toString();
                String str_email=email.getText().toString();//
                String str_licenceNo=licenceNo.getText().toString();//
                String str_user_role = user_role;//
                String Str_owner_name = userName ;//
                String type = "register";
                String str_vehicleNo = spinVehicle.getSelectedItem().toString();

                DriverSignUpBackgroundWorker backgroundWorker=new DriverSignUpBackgroundWorker(DriverSignUp.this);
                backgroundWorker.execute(type,Str_firstName,
                        str_lastName,str_NICNo,str_username,str_password,str_address,
                        str_contactNo,str_email,str_user_role,str_licenceNo,Str_owner_name,str_vehicleNo);
        }

}
