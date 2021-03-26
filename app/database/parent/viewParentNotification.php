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

    $notifications = array();

    //emergency msgs
    $query_emergency="SELECT message,date,time FROM emergency_message WHERE parent_NIC_no='$nic'";
   	$result_emergency=mysqli_query($conn,$query_emergency);


     while($row=mysqli_fetch_assoc($result_emergency)){

    	$temp = array();
      $temp['reqId']=0;
    	$temp['message']=$row['message'];
    	$temp['date']=$row['date'];
    	$temp['time']=$row['time'];
    	$temp['type']="emergency";
      $temp['childId']=0;

    	array_push($notifications, $temp);
	}

  //request replies-accpeted
    $query_accept="SELECT req_id,child_no,vehicle_no,date,time FROM request WHERE parent_NIC_no='$nic' AND status='accepted'";
    $result_accept=mysqli_query($conn,$query_accept);

     while($row=mysqli_fetch_assoc($result_accept)){

      $temp = array();
      $temp['reqId']=$row['req_id'];
      $temp['message']=$row['vehicle_no'];
      $temp['date']=$row['date'];
      $temp['time']=$row['time'];
      $temp['type']="accepted";
      $temp['childId']=$row['child_no'];

      array_push($notifications, $temp);
  }

    //request replies-rejected
    $query_reject="SELECT req_id,child_no,vehicle_no,date,time FROM request WHERE parent_NIC_no='$nic' AND status='rejected'";
    $result_reject=mysqli_query($conn,$query_reject);

     while($row=mysqli_fetch_assoc($result_reject)){

      $temp = array();
      $temp['reqId']=$row['req_id'];
      $temp['message']=$row['vehicle_no'];
      $temp['date']=$row['date'];
      $temp['time']=$row['time'];
      $temp['type']="rejected";
      $temp['childId']=$row['child_no'];

      array_push($notifications, $temp);
  }


  //owner msgs
   $query="SELECT message,date,time FROM notify WHERE parent_NIC_no='$nic'";
   $result=mysqli_query($conn,$query);

     while($row=mysqli_fetch_assoc($result)){

    	$temp = array();
      $temp['reqId']=0;
    	$temp['message']=$row['message'];
    	$temp['date']=$row['date'];
    	$temp['time']=$row['time'];
    	$temp['type']="owner msg";
      $temp['childId']=0;

    	array_push($notifications, $temp);
	}
        
	echo json_encode($notifications);

    $conn->close();

?>
