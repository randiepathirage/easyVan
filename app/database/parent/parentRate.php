<?php

 //randie..........................................................
 require "conn.php";

  $rate=$_POST['rate'];
  $parent_nic_no=$_POST['parent_nic_no'];
  $driver_nic_no=$_POST['driver_nic_no'];
  $review=$_POST['review'];

  //$rate='2.5';
  //$parent_nic_no='8675743v';
  //$driver_nic_no='3';

  $day=date("Y-m-d");
  date_default_timezone_set("Asia/Colombo");
  $time=date("h:i:sa");

  $query_post="INSERT INTO rate(parent_NIC,driver_NIC,review,time,date,rate) VALUES ('$parent_nic_no','$driver_nic_no','$review','$time','$day','$rate')";
  $result_post = mysqli_query($conn,$query_post);
  

 if($result_post){
        echo 'Posted';
}else{
        echo 'You have already rated';
}
 mysqli_close($conn);
?>