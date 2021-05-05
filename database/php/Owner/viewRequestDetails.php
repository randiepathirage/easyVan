<?php
    //randie................................................................
    require  "conn.php";
  	
  	$id=$_POST['id'];
  	//$id="15";

  	$nic="";
  	$childNo="";

  	$query="SELECT parent_NIC_no,child_no FROM request WHERE req_id='$id'";
    $result=mysqli_query($conn,$query);

    while($row=mysqli_fetch_assoc($result)){
        
        $nic=$row['parent_NIC_no'];
        $childNo=$row['child_no'];

    }

    $query_parent="SELECT contact_no,first_name FROM user WHERE NIC_no='$nic'";
    $result_parent=mysqli_query($conn,$query_parent);

    $notifications['data'] = array();

    while($row=mysqli_fetch_assoc($result_parent)){

    	$temp = array();
    	$temp['contactNo']=$row['contact_no'];
    	$temp['name']=$row['first_name'];

    	$query_child="SELECT grade,school,first_name,last_name,pickup_location,dropoff_location FROM child WHERE child_no='$childNo'";
    	$result_child=mysqli_query($conn,$query_child);


    	while($row=mysqli_fetch_assoc($result_child)){

    		$temp['grade']=$row['grade'];
    		$temp['school']=$row['school'];
    		$temp['firstName']=$row['first_name'];
    		$temp['lastName']=$row['last_name'];
    		$temp['pickUp']=$row['pickup_location'];
    		$temp['dropOff']=$row['dropoff_location'];

    	}

    	array_push($notifications['data'], $temp);
	}

	echo json_encode($notifications);
    $conn->close();

?>
