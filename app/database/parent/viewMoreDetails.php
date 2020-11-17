<?php 
    //randie..........................................................
    require  "conn.php";

    //$child_no=$_POST["childNumber"];
    $child_no="1";

    //get child details 
    $stmt_child ="SELECT first_name,last_name,vehicle_no,start_date,monthly_fee FROM child WHERE child_no='$child_no' ";
    $result_child=mysqli_query($conn,$stmt_child);//executing the query

    $details = array(); 
    

    //traversing through all the result 
    while($row=mysqli_fetch_assoc($result_child)){
        $temp = array();
        
        $temp['firstName'] = $row["first_name"]; 
        $temp['lastName'] = $row["last_name"]; 
        $temp['vehicleNo'] = $row["vehicle_no"]; 
        $temp['startDate'] =$row["start_date"]; 
        $temp['monthlyFee'] = $row["monthly_fee"];

        $vehicle_no= $row["vehicle_no"];

        //get nic numbers
        $stmt_assign = "SELECT driver_NIC_no,owner_NIC_no FROM assign WHERE vehicle_no = '$vehicle_no' ";
        $result_assign=mysqli_query($conn,$stmt_assign);//executing the query

        $driverNic ="";
        $ownerNic="";

        while($row=mysqli_fetch_assoc($result_assign)){

            $driverNic = $row["driver_NIC_no"];
            $ownerNic = $row["owner_NIC_no"];
   
        }
 
        //get owner details
        $stmt_owner = "SELECT NIC_no,contact_no,last_name,first_name FROM user WHERE NIC_no ='$ownerNic'";
        $result_owner=mysqli_query($conn,$stmt_owner);//executing the query

        $temp['ownerNIC'] =""; 
        $temp['ownerContact'] = ""; 
        $temp['ownerLastName'] = ""; 
        $temp['ownerFirstName'] =""; 

        while($row=mysqli_fetch_assoc($result_owner)){

            $temp['ownerNIC'] =$row["NIC_no"]; 
            $temp['ownerContact'] = $row["contact_no"]; 
            $temp['ownerLastName'] =  $row["last_name"]; 
            $temp['ownerFirstName'] =$row["first_name"];
        }
        
        //get driver details
        $stmt_driver = "SELECT user.NIC_no,user.contact_no,user.last_name,user.first_name,parent_owner_driver.license_no FROM user INNER JOIN parent_owner_driver ON user.NIC_no = parent_owner_driver.NIC_no WHERE user.NIC_no ='$driverNic'";
        $result_driver=mysqli_query($conn,$stmt_driver);//executing the query

        $temp['driverNIC'] = ""; 
        $temp['driverContact'] = ""; 
        $temp['driverLastName'] = ""; 
        $temp['driverFirstName'] =""; 
        $temp['licenseNo'] = ""; 

        while($row=mysqli_fetch_assoc($result_driver)){

            $temp['driverNIC'] =  $row["NIC_no"]; 
            $temp['driverContact'] = $row["contact_no"]; 
            $temp['driverLastName'] =  $row["last_name"]; 
            $temp['driverFirstName'] =$row["first_name"]; 
            $temp['licenseNo'] =$row["license_no"]; 
    
        }

        array_push($details, $temp);

    }
    
    //displaying the result in json format 
    echo json_encode($details);

?>