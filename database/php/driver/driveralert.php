<?php 

    require  "conn.php";


    $user_name=$_POST['username'];
    $message=$_POST['message'];


    //  $user_name="Senal";
    //  $message="sdfvdsdssd";

    // genarate driver NIC using username>>>>>>>>>

    $NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name' ";
    $query_NIC= mysqli_query($conn,$NIC);
    $NIC_result = mysqli_fetch_assoc($query_NIC);
    $Driver_NIC= $NIC_result['NIC_no'];

    //>>>>>>>>>>>>

    //Genarate vehicle number.........................
    $VehicelNO = "SELECT vehicle_no FROM assign WHERE driver_NIC_no = '$Driver_NIC';";

    $query_VehicelNO = mysqli_query($conn,$VehicelNO);

    $VN_result = mysqli_fetch_assoc($query_VehicelNO);

    $Result_VehicelNO = $VN_result['vehicle_no'];
    //...............................................

    // genarate parent nic  >>>>>>>>>

    $parent_nic = "SELECT parent_NIC_no FROM child WHERE vehicle_no = '$Result_VehicelNO';";
    $query_parentNIC = mysqli_query($conn,$parent_nic);
    $parentNIC_result = mysqli_fetch_assoc($query_parentNIC);
    $result_parentNIC = $parentNIC_result['parent_NIC_no'];
 
    //>>>>>>>>>>>>
        
    $mysql_qury = "INSERT INTO emergency_message(parent_NIC_no,driver_NIC_no,message,date,time) VALUES ('$result_parentNIC','$Driver_NIC','$message',now(),now())";

    if($conn->query($mysql_qury)===TRUE){
        echo "Alert send";
    }else{
        echo "Error" . $mysql_qury . "<br>" . $conn->error; 
    }


    $conn->close();

?>    