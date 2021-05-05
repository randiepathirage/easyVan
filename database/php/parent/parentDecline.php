<?php
    //randie................................................................
    require  "conn.php";
  	
  	$childId=$_POST['childId'];
  	$id=$_POST['id'];

  	$query_child="UPDATE child SET fees='0.00' WHERE child_no='$childId'";
   	$result_child=mysqli_query($conn,$query_child);


   	$query_request="UPDATE request SET status='acceptedDone' WHERE req_id='$id'";
   	$result_request=mysqli_query($conn,$query_request);

   	if($result_child && $result_request){
    	echo "Discarded";
    }else{
    	echo "Error".$query_child."<br>".$conn->error;
    }


     $conn->close();

?>