<?php
    //randie................................................................
    require  "conn.php";
  	
  	$username=$_POST['username'];
  	//$username='driver1';

    //get driver id 
    $query_driverID="SELECT NIC_no FROM login WHERE username='$username'";
    $result_driverID=mysqli_query($conn,$query_driverID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_driverID)){
        
        $nic=$row['NIC_no'];
    }

   // echo "$nic";

    //get van no 
    $query_no="SELECT vehicle_no FROM assign WHERE driver_NIC_no='$nic'";
    $result_no=mysqli_query($conn,$query_no);

     $no="";

    while($row=mysqli_fetch_assoc($result_no)){
        
        $no=$row['vehicle_no'];
    }

     $query="SELECT f.month,c.first_name,c.last_name FROM  fee f INNER JOIN child c ON f.child_no=c.child_no WHERE c.vehicle_no='$no'";
    $result=mysqli_query($conn,$query);

    $payments = array();

    while($row=mysqli_fetch_assoc($result)){

   		$temp = array();

   		$temp['firstName']=$row['first_name'];
   		$temp['lastName']=$row['last_name'];
    	$temp['month']=$row['month'];


    	array_push($payments, $temp);
	}
        
	echo json_encode($payments);

    $conn->close();

?>
