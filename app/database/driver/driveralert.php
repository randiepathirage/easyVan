<?php 

    require  "conn.php";


    $user_name=$_POST['username'];
    $message=$_POST['message'];


    // $user_name="Nimal";
    // $message="sdfvdsdssd";

    // genarate driver NIC using username>>>>>>>>>

    $NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name' ";
    $query_NIC= mysqli_query($conn,$NIC);
    $NIC_result = mysqli_fetch_assoc($query_NIC);
    $Driver_NIC= $NIC_result['NIC_no'];

    //>>>>>>>>>>>>
        
    $mysql_qury = "INSERT INTO emergency_message(driver_NIC_no,message,date,time) VALUES ('$Driver_NIC','$message',now(),now())";

    if($conn->query($mysql_qury)===TRUE){
        echo "insert success";
    }else{
        echo "Error" . $mysql_qury . "<br>" . $conn->error; 
    }


    $conn->close();

?>    