
<?php 
    //randie...............................................................
    require  "conn.php";

    $user_name=$_POST["username"];
    //$user_name="randie";

    $user = array();

    //get parent nic
    $query_parentID="SELECT NIC_no FROM login WHERE username='$user_name'";
    $result_parentID=mysqli_query($conn,$query_parentID);


    $nic="";

    while($row=mysqli_fetch_assoc($result_parentID)){
        
        $nic=$row['NIC_no'];
    }


    $stmt = "SELECT child.first_name,location.longitude,location.latitude FROM `location` INNER JOIN child ON location.vehicle_no=child.vehicle_no WHERE child.parent_NIC_no='$nic'";//creating a query
    $stmt_exe=mysqli_query($conn,$stmt);//executing the query


   $result=array();
   $result['data'] = array();

    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['child_name']= $row['first_name'];
        $index['longitude']= $row['longitude'];
        $index['latitude'] = $row['latitude'];

        array_push($result['data'], $index);
    }

    echo json_encode($result);

?>