<?php

    require  "conn.php";

    
   // $id = $_POST["id"];
    $first_name = $_POST["name"];    
    $contact = $_POST["contact"];
    $email = $_POST["email"];
    $address = $_POST["address"];

/*    $id ='123456789';
    $first_name = "Dimuthu";    
    $contact = "0712323234";
    $email = "dimuthuabc@gmail.com";
    $address = "hakamana";
    */
         //Genarate driver NIC using username

    $NIC = "SELECT NIC_no FROM login  WHERE username = '$first_name'";
    $query_NIC = mysqli_query($conn,$NIC);
    $NIC_result = mysqli_fetch_assoc($query_NIC);
   $id = $NIC_result['NIC_no'];
     
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