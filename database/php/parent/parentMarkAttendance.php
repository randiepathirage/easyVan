<?php

 //randie..........................................................
 require "conn.php";

  $date=$_POST['date'];
  $child_name=$_POST['childName'];
  $username=$_POST['username'];
  $morning=$_POST['morning'];
  $evening=$_POST['evening'];

/*
  $date='2021/03/24';
  $child_name='devin';
  $username='randie';
  $morning='';
  $evening='0';*/



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

 
   	$query="INSERT INTO absence_date(child_no,date,abesence_in_the_evening,absence_in_the_morning) VALUES ('$childID','$date','$evening','$morning')";
    $result=mysqli_query($conn,$query);


     if($result){
        echo 'Marked';
	}else{
      echo "Error".$query."<br>".$conn->error;
	}


 	mysqli_close($conn)


 ?>