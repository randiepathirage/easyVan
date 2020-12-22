<?php 
    //randie...............................................................
    require  "conn.php";

    $number=$_POST["number"];
    //$number="CBF-7375";

    $user = array();

    //access user table
    $stmt = "SELECT user.NIC_no,user.contact_no,user.first_name,user.last_name FROM `user` INNER JOIN vehicle ON user.NIC_no=vehicle.owner_NIC_no WHERE vehicle.number='$number'";//creating a query
    $stmt_exe=mysqli_query($conn,$stmt);//executing the query

   $result=array();
   $result['data'] = array();

    //traversing through all the result 
    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['NIC_no']= $row['NIC_no'];
        $index['contact_no']= $row['contact_no'];
        $index['first_name'] = $row['first_name'];
        $index['last_name'] = $row['last_name'];

        //get driver nic number
        $stmt_assign = "SELECT driver_NIC_no FROM assign WHERE vehicle_no = '$number' ";
        $result_assign=mysqli_query($conn,$stmt_assign);//executing the query

        $driverNic="";

        while($row=mysqli_fetch_assoc($result_assign)){

            $driverNic = $row["driver_NIC_no"];
   
        }

        //get driver details
        $stmt_driver = "SELECT user.NIC_no,user.contact_no,user.last_name,user.first_name,parent_owner_driver.license_no FROM user INNER JOIN parent_owner_driver ON user.NIC_no = parent_owner_driver.NIC_no WHERE user.NIC_no ='$driverNic'";
        $result_driver=mysqli_query($conn,$stmt_driver);//executing the query

        $index['driverNIC'] = ""; 
        $index['driverContact'] = ""; 
        $index['driverLastName'] = ""; 
        $index['driverFirstName'] =""; 
        $index['licenseNo'] = ""; 

        while($row=mysqli_fetch_assoc($result_driver)){

            $index['driverNIC'] =  $row["NIC_no"]; 
            $index['driverContact'] = $row["contact_no"]; 
            $index['driverLastName'] =  $row["last_name"]; 
            $index['driverFirstName'] =$row["first_name"]; 
            $index['licenseNo'] =$row["license_no"]; 
    
        }


        array_push($result['data'], $index);
    }

    echo json_encode($result);

    ?>