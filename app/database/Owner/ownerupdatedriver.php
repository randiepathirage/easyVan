<?php

 require "conn.php";


  //$licenseNo=$_POST['license_no'];
  $vehicleNo=$_POST['vehicleNo'];
  $contact_no=$_POST['contact_no'];
  $licenseNo=$_POST['licenseNo'];
  $username=$_POST['username'];
  $email=$_POST['email'];

  $nic="";

  $query="SELECT NIC_no FROM login WHERE username='$username'";
  $result=mysqli_query($conn,$query);

  while($row=mysqli_fetch_assoc($result)){
        
        $nic=$row['NIC_no'];

  }


$Sql_Query_user = "UPDATE user SET contact_no = '$contact_no' WHERE NIC_no = '$nic'";
$Sql_Query_login = "UPDATE login SET username = '$username', email = '$email', username='$username',email='$email' WHERE NIC_no = '$nic'";
$Sql_Query_pod = "UPDATE parent_owner_driver SET license_no = '$licenseNo' WHERE NIC_no = '$nic'";

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