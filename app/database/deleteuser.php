<?php

require 'conn.php';

 $NIC_no = $_POST['NIC_no'];
 //$NIC_no = "9990";

$Query_user = "DELETE FROM user WHERE NIC_no = '$NIC_no'";
$Query_login = "DELETE FROM login WHERE NIC_no = '$NIC_no'";
$Query_pod = "DELETE FROM parent_owner_driver WHERE NIC_no = '$NIC_no'";

 if(mysqli_query($conn,$Query_login)){
    if(mysqli_query($conn,$Query_user)){
        if(mysqli_query($conn,$Query_pod)){
            echo 'Record deleted Successfully';
                }
              }
            }
else
{
 echo 'Something went wrong';
 }

 mysqli_close($conn);
?>