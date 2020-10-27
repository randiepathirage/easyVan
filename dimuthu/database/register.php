<?php
 require  "conn.php";

 $user_firstname=$_POST['firstName'];
 $user_middlename=$_POST['middleName'];
 $user_lastname=$_POST['lastName'];
 $nic_no=$_POST['NICNo'];
 $username=$_POST['username'];
 $password=$_POST['password'];
 $address=$_POST['address'];
 $contact_no=$_POST['contactNo'];
 $email=$_POST['email'];

 
 /*$user_firstname="name";
 $user_middlename="middleName";
 $user_lastname="lastName";
 $nic_no="NICNo";
 $username="username";
 $password="password";
 $address="address";
 $contact_no=2345678;
 $email="email";*/

$query_login="INSERT INTO login(NIC_no,username,password,email) VALUES ('$nic_no','$username',' $password',' $email')";

$query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,middle_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$user_middlename','$address')";

//$query_user_role="INSERT INTO user_role(NIC_no,user_role) 
//VALUES ('$nic_no',' $user_pass',' $user_email')";

if($conn->query($query_login)===TRUE){
    if($conn->query($query_user)===TRUE){
        echo "Insert Successful";
    }
}
else{
    echo "Error".$query_login."<br>".$conn->error;
}
    $conn->close();

?>