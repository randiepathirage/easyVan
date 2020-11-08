package com.e.esayVan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;

    BackgroundWorker(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.0.2.2/easyvan/login.php";
        String register_url = "http://10.0.2.2/easyvan/register.php";

        if(type.equals("login")){
            try {

                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try {
                String firstName = params[1];
                String lastName = params[2];
                String NICNo = params[3];
                String username = params[4];
                String password = params[5];
                String address = params[6];
                String contactNo = params[7];
                String email = params[8];
                String userRole=params[9];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data =
                         URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(firstName,"UTF-8")+"&"
                        +URLEncoder.encode("lastName","UTF-8")+"="+URLEncoder.encode(lastName,"UTF-8")+"&"
                        +URLEncoder.encode("NICNo","UTF-8")+"="+URLEncoder.encode(NICNo,"UTF-8")+"&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        +URLEncoder.encode("contactNo","UTF-8")+"="+URLEncoder.encode(contactNo,"UTF-8")+"&"
                                 +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("userRole","UTF-8")+"="+URLEncoder.encode(userRole,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    protected void onPostExecute(String result) {

        //if login fails
        if (result.equals("Login Fail")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        //if insert successful
        else if(result.equals("Insert Successful")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent i  = new Intent(context,Login.class);
            context.startActivity(i);
        }
        //if login successful
        else if (result.equals("Login Success driver")){
                Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();
                Intent i  = new Intent(context,Driver.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        else if (result.equals("Login Success owner")){
                Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();
                Intent i  = new Intent(context,Owner.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        else if (result.equals("Login Success parent")){
                Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();
                Intent i  = new Intent(context,Parent.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        else{
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
