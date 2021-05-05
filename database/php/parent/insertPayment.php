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
  $username='randie';
  $month='January';*/



  //get parent nic
    $query_parentID="SELECT NIC_no FROM login WHERE username='$username'";
    $result_parentID=mysqli_query($conn,$query_parentID);

    $nic="";

    while($row=mysqli_fetch_assoc($result_parentID)){
        
        $nic=$row['NIC_no'];
    }



  //get ownerid and parent id 
    $query_childID="SELECT child_no FROM child WHERE first_name='$child_name' AND parent_NIC_no='$nic'";
    $result_childID=mysqli_query($conn,$query_childID);

    $childID="";

    while($row=mysqli_fetch_assoc($result_childID)){
        
        $childID=$row['child_no'];
    }

  $query="INSERT INTO fee(child_no,amount,paid_date,month) VALUES ('$childID','$amount','$day','$month')";
  $result=mysqli_query($conn,$query);


  if($result){
      echo 'payment updated';
	}else{
      echo "Error".$query."<br>".$conn->error;
	}


 	mysqli_close($conn);



 ?>