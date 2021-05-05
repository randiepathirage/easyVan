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

    $day=date("Y-m-d");
   // echo "$nic";

    //get van no 
    $query_no="SELECT vehicle_no FROM assign WHERE driver_NIC_no='$nic'";
    $result_no=mysqli_query($conn,$query_no);

     $no="";

    while($row=mysqli_fetch_assoc($result_no)){
        
        $no=$row['vehicle_no'];
    }

    //echo "$no";

   $query="SELECT a.abesence_in_the_evening,a.absence_in_the_morning,c.first_name FROM  absence_date a INNER JOIN child c ON a.child_no=c.child_no WHERE c.vehicle_no='$no' AND a.date='$day'";
    $result=mysqli_query($conn,$query);

    $notifications = array();

    while($row=mysqli_fetch_assoc($result)){



   	$temp = array();
      $temp['reqId']=0;

      if($row['abesence_in_the_evening']==0 && $row['absence_in_the_morning']==1){
        $temp['message']="will not come in the school van in evening";
      }else if($row['abesence_in_the_evening']==1 && $row['absence_in_the_morning']==0){
        $temp['message']="will not come in the school van in morning";
      }else if($row['abesence_in_the_evening']==1 && $row['absence_in_the_morning']==1){
        $temp['message']="will not come in the school van in both morning and evening";
      }

    	$temp['date']=$day;
    	$temp['time']='00';
    	$temp['type']="attendance";
      $temp['childId']=$row['first_name'];

    	array_push($notifications, $temp);
	}
        
	echo json_encode($notifications);

    $conn->close();

?>
