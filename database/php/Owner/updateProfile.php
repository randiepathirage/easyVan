<?php

    require  "conn.php";

    
    $id = $_POST["id"];
    $first_name = $_POST["name"];    
    $contact = $_POST["contact"];
    $email = $_POST["email"];
    $address = $_POST["address"];
     
    $sql_user = "UPDATE user    
                SET  contact_no = '$contact', address = '$address', first_name = '$first_name' 
                WHERE NIC_no = '$id' ";

    $result_user = mysqli_query($conn,$sql_user);

    $sql_login = "UPDATE login 
                    SET  email = '$email' 
                    WHERE NIC_no = '$id' ";


    $result_login = mysqli_query($conn,$sql_login);



    if($result_user && $result_login){
         echo "Update Successfull";
        
    }


    else{
         echo "Error";
    }


    mysqli_close($conn);
     
        
?>