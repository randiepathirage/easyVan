<?php

    require  "conn.php";

    
    $user_name = $_POST["username"];    
    $contact = $_POST["contact"];
    $email = $_POST["email"];
    $address = $_POST["address"];



    //Genarate driver NIC using username>>>>>>>>>

    $NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name';";
    $query_NIC = mysqli_query($conn,$NIC);
    $NIC_result = mysqli_fetch_assoc($query_NIC);
    $Driver_NIC = $NIC_result['NIC_no'];
    //>>>>>>>>>>>>
     
    $sql_user = "UPDATE user    
                SET  contact_no = '$contact', address = '$address', first_name = '$user_name' 
                WHERE NIC_no = '$Driver_NIC' ";

    $result_user = mysqli_query($conn,$sql_user);

    $sql_login = "UPDATE login 
                    SET  email = '$email' 
                    WHERE NIC_no = '$Driver_NIC' ";


    $result_login = mysqli_query($conn,$sql_login);



    if($result_user && $result_login){
         echo "Update Successfull";
        
    }


    else{
         echo "Error";
    }


    mysqli_close($conn);
     
        
?>