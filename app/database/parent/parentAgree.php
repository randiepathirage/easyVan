<?php
    //randie................................................................
    require  "conn.php";
  	
  	$childId=$_POST['childId'];
  	$vehicleNo=$_POST['vehicleNo'];
  	$startDate=$_POST['date'];
  	$id=$_POST['id'];

  	$query_child="UPDATE child SET start_date='$startDate' WHERE child_no='$childId'";
   	$result_child=mysqli_query($conn,$query_child);

   	$query_vehicle="UPDATE vehicle SET no_of_seats_available=no_of_seats_available-1 WHERE number='$vehicleNo'";
   	$result_vehicle=mysqli_query($conn,$query_vehicle);


   	$query_request="UPDATE request SET status='acceptedDone' WHERE req_id='$id'";
   	$result_request=mysqli_query($conn,$query_request);

   	if($result_child && $result_vehicle && $result_request){
    	echo "Your child is assigned to a van";
    }else{
    	echo "error";
    }


     $conn->close();

?>