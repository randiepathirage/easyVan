<?php

 require "conn.php";


  //$licenseNo=$_POST['license_no'];
  $user_firstname=$_POST['first_name'];
  $user_lastname=$_POST['last_name'];
  $nic_no=$_POST['NIC_no'];
  $username=$_POST['username'];
  $address=$_POST['address'];
  $contact_no=$_POST['contact_no'];
  $email=$_POST['email'];
  //$user_role=$_POST['userRole'];

  /* $user_firstname="isuzz999";
   $user_lastname="isu999";
   $nic_no="9876";
   $username="isu999";
   $address="addressuz99";
   $contact_no=2345;
   $email="emailuz99";
   //$licenseNo="333uz";*/

  //$password=base64_encode($password);

 /* $parent=0;
  $driver=0;
  $owner=0;

  if($user_role=="parent"){
      $parent=1;
  }else if($user_role=="driver"){
     $driver=1;
  }else if($user_role=="owner"){
     $owner=1;
  */

$Sql_Query_user = "UPDATE user SET first_name = '$user_firstname', last_name = '$user_lastname', contact_no = '$contact_no', address = '$address' WHERE NIC_no = $nic_no";
$Sql_Query_login = "UPDATE login SET username = '$username', email = '$email' WHERE NIC_no = $nic_no";
$Sql_Query_pod = "UPDATE parent_owner_driver SET location = '$address' WHERE NIC_no = $nic_no";

 if($conn->query($Sql_Query_login)===TRUE){
    if($conn->query($Sql_Query_user)===TRUE){
        if($conn->query($Sql_Query_pod)===TRUE){
            echo 'Record Updated Successfully';
          }
         }
        }
else
{
 echo 'Something went wrong';
 }
 mysqli_close($conn);

?>