<?php

    require  "conn.php";

  /*  
    $id = $_POST["id"];
    $name = $_POST["name"];    
    $contact = $_POST["contact"];
    $email = $_POST["email"];
    $address = $_POST["address"];
    $licenseNo = $_POST["licence_no"];
    $vehicl_no = $_POST["vehicl_no"];*/

     $id = '222222222V';
     $owner_NIC_no = "123456789";
    $name = "hello";    
    $contact = "0000000";
    $email =" HEllo@gmail.com";
    $address = "gggg";
    $licenseNo = "gggg";
    $vehicle_no = "BBK 9396";
     
    $sql_ParentOwnerDriver = "UPDATE parent_owner_driver SET  license_no = '$licenseNo' WHERE NIC_no = '$id' ";
    $result_PWD = mysqli_query($conn,$sql_ParentOwnerDriver);

    $sql_assign = "UPDATE assign SET vehicle_no = '$vehicle_no', owner_NIC_no = '$owner_NIC_no' , driver_NIC_no = '$id' WHERE driver_NIC_no = $id ";
    $result_assign = mysqli_query($conn,$sql_assign);


    $sql_user = "UPDATE user SET  address = '$address' , contact_no = '$contact' WHERE NIC_no = '$id' ";
    $result_user = mysqli_query($conn,$sql_user);

    $sql_login = "UPDATE login SET  email = '$email' WHERE NIC_no = '$id' ";
    $result_login = mysqli_query($conn,$sql_login);

    if( $result_PWD  ){
         echo "Data Updated POD";
         if( $result_assign ) {

            echo "Data updated assign";

                if ($result_user ){
                    echo "Data updated user";
                        if( $result_login){
                            echo "data updated login";
                        }
                }
         }
    }
    else{
         echo "Failed";
    }
    mysqli_close($conn);
     
        
?>