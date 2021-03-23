<?php
    //randie................................................................
    require  "conn.php";
  	
  	$id=$_POST['id'];
  	//$id="15";

  	$day=date("Y-m-d");
    date_default_timezone_set("Asia/Colombo");
    $time=date("h:i:sa");

  	$query_update="UPDATE request SET status='rejected',date='$day',time='$time' WHERE req_id='$id'";
    $result_update=mysqli_query($conn,$query_update);

    if($result_update){
         echo "Rejected";
        
    }
    else{
         echo "Error";
    }

    $conn->close();

?>