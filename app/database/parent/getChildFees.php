<?php
    //randie................................................................
    require  "conn.php";
  	
  	$childId=$_POST['childId'];
  	//$childId="2";

  	$query="SELECT fees FROM child WHERE child_no='$childId'";
   	$result=mysqli_query($conn,$query);

    $fee="";

    while($row=mysqli_fetch_assoc($result)){
        
        $fee=$row['fees'];
    }

    if($result){
    	echo "$fee";
    }else{
    	echo "error";
    }


    $conn->close();

?>
