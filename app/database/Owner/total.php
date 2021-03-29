

<?php 
  
    require  "conn.php";

    $username=$_POST["owner"];

    $nic_no= mysqli_query($conn,"SELECT NIC_no FROM login WHERE username = '$username'");
    $result = mysqli_fetch_array($nic_no);
    $nic = $result['NIC_no'];

    $vehicle_no = mysqli_query($conn,"SELECT vehicle_no FROM assign WHERE owner_NIC_no = '$nic'");
    $result = mysqli_fetch_array($vehicle_no);
    $vehicle = $result['vehicle_no'];


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