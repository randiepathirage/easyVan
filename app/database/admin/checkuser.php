<?php

require 'conn.php';

 //$username = $_POST['username'];
 $username = "ruvidu";

 $nic_no= mysqli_query($conn,"SELECT NIC_no FROM login WHERE username = '$username'");
 $result = mysqli_fetch_array($nic_no);
 $nic = $result['NIC_no'];
 $Query_role = "SELECT * FROM parent_owner_driver WHERE NIC_no = '$nic' AND owner_flag='1' AND admin_flag='1'";
 $result = mysqli_query($conn,$Query_role);
 $rows = mysqli_num_rows($result);

 if($rows>0){
            echo 'user is an owner';
        }

else
{
 echo 'Something went wrong';
 }

 mysqli_close($conn);
?>