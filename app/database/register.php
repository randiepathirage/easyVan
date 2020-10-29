<?php
 require  "conn.php";

 $license_no=0;
 $user_firstname=$_POST['firstName'];
 $user_middlename=$_POST['middleName'];
 $user_lastname=$_POST['lastName'];
 $nic_no=$_POST['NICNo'];
 $username=$_POST['username'];
 $password=$_POST['password'];
 $address=$_POST['address'];
 $contact_no=$_POST['contactNo'];
 $email=$_POST['email'];
 $user_role=$_POST['userRole'];

 //$password=base64_encode($password);

 $parent=0;
 $driver=0;
 $owner=0;

 if($user_role=="parent"){
     $parent=1;
 }else if($user_role=="driver"){
    $driver=1;
 }else if($user_role=="owner"){
    $owner=1;
 }
 /*
 $user_firstname="name";
 $user_middlename="middleName";
 $user_lastname="lastName";
 $nic_no="NICNo";
 $username="username";
 $password="password";
 $address="address";
 $contact_no=2345678;
 $email="email";*/

$query_login="INSERT INTO login(NIC_no,username,password,email) VALUES ('$nic_no','$username','$password',' $email')";

$query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,middle_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$user_middlename','$address')";

$query_user_role="INSERT INTO user_role(NIC_no,user_role) VALUES ('$nic_no',' $user_role')";

$query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,parent_flag,driver_flag,owner_flag,license_no) VALUES ('$nic_no',' $parent','$driver','$owner',' $license_no')";

if($conn->query($query_login)===TRUE){
    if($conn->query($query_user)===TRUE){
        if($conn->query($query_user_role)===TRUE){
            if($conn->query($query_parent_owner_driver)===TRUE){
                echo "Insert Successful";
            }
        }
    }
}
else{
    echo "Error".$query_login."<br>".$conn->error;
}
    $conn->close();

?>