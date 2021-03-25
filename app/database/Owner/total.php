

<?php 
  
    require  "conn.php";

  //$vehicle_no=$_POST[vehicle_no];
   $vehicle_no="KK 2332";
    //$user_name="hasindu";


    $user = array();

  
    $stmt = "SELECT SUM(`amount`) AS `total` FROM `expense`  WHERE `vehicle_no`='$vehicle_no'";


    $stmt_exe=mysqli_query($conn,$stmt);

   $result=array();
   $result['ata'] = array();


    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['total']= $row['total'];;
     

        array_push($result['ata'], $index);
    }

    echo json_encode($result);

   
    

    ?>