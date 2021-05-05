<?php

 //randie..........................................................
 require "conn.php";

	date_default_timezone_set("Asia/Colombo");
    $day=date("Y-m-d");


  $amount=$_POST['amount'];
  $child_name=$_POST['childName'];
  $username=$_POST['username'];
  $month=$_POST['month'];

/*
  $amount='1000.00';
  $child_name='mihisara';
  $username='driver1';
  $month='January';*/



  //get parent nic
    $query_parentID="SELECT parent_NIC_no,child_no FROM child WHERE first_name='$child_name'";
    $result_parentID=mysqli_query($conn,$query_parentID);

    $nic="";
    $childID="";
    $row=mysqli_fetch_assoc($result_parentID);
        
    $nic=$row['NIC_no']; 
    $childID=$row['child_no'];
  

  $query="INSERT INTO fee(child_no,amount,paid_date,month) VALUES ('$childID','$amount','$day','$month')";
  $result=mysqli_query($conn,$query);


  if($result){
      echo 'payment updated';
	}else{
      echo "Error".$query_insert."<br>".$conn->error;
	}


 	mysqli_close($conn);



 ?>