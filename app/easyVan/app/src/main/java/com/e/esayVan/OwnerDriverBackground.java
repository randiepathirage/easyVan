package com.e.esayVan;

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

public class OwnerDriverBackground extends User {

    public OwnerDriverBackground(String username, String userRole) {
        super(username, userRole);
    }
    String userName = getUsername();
    String userRole = getUserRole();
    String  OwnerDriverBackground_url = "http://10.0.2.2/easyvan/OwnerDriverBackground.php";
   protected String data() {
       try {

           URL url = new URL(OwnerDriverBackground_url);
           HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
           httpURLConnection.setRequestMethod("POST");
           httpURLConnection.setDoOutput(true);
           httpURLConnection.setDoInput(true);
           OutputStream outputStream = httpURLConnection.getOutputStream();

           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
           String post_data =
                   URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                           + URLEncoder.encode("userRole", "UTF-8") + "=" + URLEncoder.encode(userRole, "UTF-8") ;

           bufferedWriter.write(post_data);
           bufferedWriter.flush();
           bufferedWriter.close();
           outputStream.close();
           InputStream inputStream = httpURLConnection.getInputStream();
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
           String result = "";
           String line = "";
           while ((line = bufferedReader.readLine()) != null) {
               result += line;
           }
           bufferedReader.close();
           inputStream.close();
           httpURLConnection.disconnect();

           return result;

       } catch (
               MalformedURLException e) {
           e.printStackTrace();
       } catch (
               IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
