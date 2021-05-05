
<?php 
    //randie...............................................................
    require  "conn.php";

    $user_name=$_POST["username"];
    //$user_name="owner1";

    $user = array();

    //get parent nic
    $query_ownerID="SELECT NIC_no FROM login WHERE username='$user_name'";
    $result_ownerID=mysqli_query($conn,$query_ownerID);


    $nic="";

    while($row=mysqli_fetch_assoc($result_ownerID)){
        
        $nic=$row['NIC_no'];
    }


    $stmt = "SELECT location.vehicle_no,location.longitude,location.latitude FROM `location` INNER JOIN vehicle ON location.vehicle_no=vehicle.number WHERE vehicle.owner_NIC_no='$nic'";//creating a query
    $stmt_exe=mysqli_query($conn,$stmt);//executing the query


   $result=array();
   $result['data'] = array();

    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['vehicle_no']= $row['vehicle_no'];
        $index['longitude']= $row['longitude'];
        $index['latitude'] = $row['latitude'];

        array_push($result['data'], $index);
    }

    echo json_encode($result);

?>