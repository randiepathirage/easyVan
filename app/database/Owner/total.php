

<?php 
  
    require  "conn.php";

  //$vehicle_no=$_POST["vehicle_no"];
    $vehicle_no = "KK 2332";



    $user = array();

  
    $stmt = "SELECT SUM(`amount`) FROM `expense` WHERE `vehicle_no`="$vehicle_no"";


    $stmt_exe=mysqli_query($conn,$stmt);

   $result=array();
   $result['data'] = array();


    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['amount']= $row['amount'];
     

        array_push($result['data'], $index);
    }

    echo json_encode($result);

   
    

    ?>