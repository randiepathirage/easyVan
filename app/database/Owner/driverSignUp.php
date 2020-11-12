<?php
 require  "conn.php";


 $user_firstname=$_POST['firstName'];
 $user_lastname=$_POST['lastName'];
 $nic_no=$_POST['NICNo'];
 $username=$_POST['username'];
 $password=$_POST['password'];
 $address=$_POST['address'];
 $contact_no=$_POST['contactNo'];
 $email=$_POST['email'];
 $license_no = $_POST['licenseNo'];
 $user_role = "driver";
 $user_middlename = "None";


$query_login="INSERT INTO login(NIC_no,username,password,email) VALUES ('$nic_no','$username',' $password',' $email')";

$query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,middle_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$user_middlename','$address')";

$query_user_role="INSERT INTO user_role(NIC_no,user_role) VALUES ('$nic_no','$user_role')";

$query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,driver_flag,license_no) VALUES ('$nic_no','$user_role','$license_no')";

if($conn->query($query_login)===TRUE){
    if($conn->query($query_user)===TRUE){
        if($conn->query($query_user_role)===TRUE){
            if($conn->query($query_parent_owner_driver)===TRUE){
                echo "Insert Successful Driver";
            }
        }
    }
}
else{
    echo "Error".$query_login."<br>".$conn->error;
}
    $conn->close();

?>