package com.e.esayVan;

import android.content.Context;
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

public class OwnerExpensesBackground extends AsyncTask<String,Void,String>  {

    Context context;

    OwnerExpensesBackground(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String match = params[0];
        String addexpense_url2 = "http://10.0.2.2/easyvan/OwnerAddExpensesBackground.php";

        if(match.equals("ownerAddExpense")){
            try {

                String amount = params[1];
                String type = params[2];
               String date = params[3];
                String vehicleNo = params[4];


                URL url = new URL(addexpense_url2);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("amount","UTF-8")+"="+URLEncoder.encode(amount,"UTF-8")+"&"
                        +URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("vehicle_no","UTF-8")+"="+URLEncoder.encode(vehicleNo,"UTF-8");

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

        Toast.makeText(context,result,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
