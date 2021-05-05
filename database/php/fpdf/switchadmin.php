<?php

 require "conn.php";

  $username=$_POST['username'];
  //$username='admin';
  $nic_no= mysqli_query($conn,"SELECT NIC_no FROM login WHERE username = '$username'");
  $result = mysqli_fetch_array($nic_no);
  $nic = $result['NIC_no'];


$Sql_Query_role = "UPDATE parent_owner_driver SET owner_flag = '1' WHERE NIC_no = '$nic'";

 if($conn->query($Sql_Query_role)===TRUE){
      echo 'Record Updated Successfully';         
    }
  else
  {
    echo 'Something went wrong';
  }
mysqli_close($conn);

?>