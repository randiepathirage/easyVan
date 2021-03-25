<?php
    //randie................................................................
    require  "conn.php";
  	
  	$id=$_POST['id'];
    $fee=$_POST['fees'];
    $childId=$_POST['childId'];
  	//$id="15";

    $day=date("Y-m-d");
    date_default_timezone_set("Asia/Colombo");
    $time=date("h:i:sa");


  	$query_update="UPDATE request SET status='accepted',date='$day',time='$time' WHERE req_id='$id'";
    $result_update=mysqli_query($conn,$query_update);

    $query_child="UPDATE child SET fees='$fee' WHERE child_no='$childId'";
    $result_child=mysqli_query($conn,$query_child);

    if($result_update && $result_child){
         echo "Accepted";
        
    }
    else{
         echo "Error";
    }

    $conn->close();

?>