
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

public class OwnerVanAddBackgroundWorker extends AsyncTask<String,Void,String> {


    Context context;

    OwnerVanAddBackgroundWorker (Context ctx){
        context=ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String vanAdd_url = "http://10.0.2.2/easyvan/OwnerAddVan.php";
        try {
            String vehicleNo = params[0];
            String vehicleType = params[1];
            String noOfSeats = params[2];
            String model = params[3];
            String permitNo = params[4];
            String caretacker = params[5];
            String condition=params[6];
            String ownerusername = params[7];
            String school =params[8];
            String town = params[9];

            URL url = new URL(vanAdd_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data =
                    URLEncoder.encode("vehicleNo","UTF-8")+"="+URLEncoder.encode(vehicleNo,"UTF-8")+"&"
                            +URLEncoder.encode("vehicleType","UTF-8")+"="+URLEncoder.encode(vehicleType,"UTF-8")+"&"
                            +URLEncoder.encode("totalSeats","UTF-8")+"="+URLEncoder.encode(noOfSeats,"UTF-8")+"&"
                            +URLEncoder.encode("model","UTF-8")+"="+URLEncoder.encode(model,"UTF-8")+"&"
                            +URLEncoder.encode("permitNo","UTF-8")+"="+URLEncoder.encode(permitNo,"UTF-8")+"&"
                            +URLEncoder.encode("caretaker","UTF-8")+"="+URLEncoder.encode(caretacker,"UTF-8")+"&"
                            +URLEncoder.encode("condition","UTF-8")+"="+URLEncoder.encode(condition,"UTF-8") +"&"
                            +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(ownerusername,"UTF-8") +"&"
                            +URLEncoder.encode("school","UTF-8")+"="+URLEncoder.encode(school,"UTF-8") +"&" 
                            +URLEncoder.encode("town","UTF-8")+"="+URLEncoder.encode(town,"UTF-8");

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


        return null;
    }

    @Override
    protected void onPreExecute() {

    }
    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context,result,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
