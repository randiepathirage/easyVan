<?php
    //randie..............................................................................

	   $file = fopen('accessLogs.txt','a');

	   $user_name=$_POST["username"];
       $role=$_POST["role"];

       //$user_name="akalanka";
       //$role="admin";

	   date_default_timezone_set("Asia/Colombo");
       $day=date("Y-m-d");
       $time=date("h:i:sa");


       fwrite($file,"\n$day  $time   $user_name      LOGOUT $role      Success");

       fclose($file);

 ?>