<?php

require 'conn.php';

 $NICno = $_POST['NIC_no'];
 //$NICno = "000";

$Query_user = "DELETE FROM user WHERE NIC_no = '$NICno'";
$Query_login = "DELETE FROM login WHERE NIC_no = '$NICno'";
$Query_pod = "DELETE FROM parent_owner_driver WHERE NIC_no = '$NICno'";

 if(mysqli_query($conn,$Query_login)){
    if(mysqli_query($conn,$Query_user)){
        if(mysqli_query($conn,$Query_pod)){
            echo 'Data Deleted';
                }
              }
            }
else
{
 echo 'Something went wrong';
 }

 mysqli_close($conn);
?>