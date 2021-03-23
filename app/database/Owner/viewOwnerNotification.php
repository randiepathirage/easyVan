<?php
    //randie................................................................
    require  "conn.php";
  	
  	$username=$_POST['username'];
  	//$username='owner1';

    //get parent id 
    $query_ownerID="SELECT NIC_no FROM login WHERE username='$username'";
    $result_ownerID=mysqli_query($conn,$query_ownerID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_ownerID)){
        
        $nic=$row['NIC_no'];
    }

    $notifications = array();

    $query_emergency="SELECT message,date,time FROM emergency_message INNER JOIN assign ON emergency_message.driver_NIC_no=assign.driver_NIC_no WHERE assign.owner_NIC_no='$nic'";
   	$result_emergency=mysqli_query($conn,$query_emergency);


     while($row=mysqli_fetch_assoc($result_emergency)){

    	$temp = array();
      $temp['reqId']=0;
    	$temp['message']=$row['message'];
    	$temp['date']=$row['date'];
    	$temp['time']=$row['time'];
    	$temp['type']="emergency";

    	array_push($notifications, $temp);
	}

   $query_request="SELECT req_id,vehicle_no,date,time FROM request WHERE owner_NIC_no='$nic'";
   $result_request=mysqli_query($conn,$query_request);

     while($row=mysqli_fetch_assoc($result_request)){

    	$temp = array();
      $temp['reqId']=$row['req_id'];
    	$temp['message']=$row['vehicle_no'];
    	$temp['date']=$row['date'];
    	$temp['time']=$row['time'];
    	$temp['type']="request";

    	array_push($notifications, $temp);
	}
        
	echo json_encode($notifications);

    $conn->close();

?>
