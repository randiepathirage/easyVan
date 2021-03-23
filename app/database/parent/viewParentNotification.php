<?php
    //randie................................................................
    require  "conn.php";
  	
  	$username=$_POST['username'];
  	//$username='randie';

    //get parent id 
    $query_parentID="SELECT NIC_no FROM login WHERE username='$username'";
    $result_parentID=mysqli_query($conn,$query_parentID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_parentID)){
        
        $nic=$row['NIC_no'];
    }

   $query="SELECT message,date,time FROM notify WHERE parent_NIC_no='$nic'";
   $result=mysqli_query($conn,$query);


    $notifications = array();

     while($row=mysqli_fetch_assoc($result)){

    	$temp = array();
    	$temp['message']=$row['message'];
    	$temp['date']=$row['date'];
    	$temp['time']=$row['time'];

    	array_push($notifications, $temp);
	}
        
	echo json_encode($notifications);

    $conn->close();

?>
