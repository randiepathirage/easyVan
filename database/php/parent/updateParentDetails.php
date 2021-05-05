<?php


    //randie..................................................................
    require  "conn.php";

    
    $id = $_POST["id"];
    $name = $_POST["name"];    
    $contact = $_POST["contact"];
    $email = $_POST["email"];
    $address = $_POST["address"];
    /*
    $id ="986760296v" ;
    $name ="rerr";    
    $contact ="3424242";
    $email = "wererww";
    $address ="sfsff" ;*/
     
    $sql_user = "UPDATE user SET  contact_no = '$contact', address = '$address' WHERE NIC_no = '$id' ";
    $result_user = mysqli_query($conn,$sql_user);

    $sql_login = "UPDATE login SET  email = '$email' WHERE NIC_no = '$id' ";
    $result_login = mysqli_query($conn,$sql_login);

    if($result_user && $result_login){
         echo "Data Updated";
        
    }
    else{
         echo "Failed";
    }
    mysqli_close($conn);
     
        
?>