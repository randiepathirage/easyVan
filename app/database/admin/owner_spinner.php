<?php

 require "conn.php";
 $sql = "SELECT login.username FROM login JOIN parent_owner_driver ON login.NIC_no = parent_owner_driver.NIC_no WHERE parent_owner_driver.owner_flag=1";

 if(!$conn->query($sql)){
  echo "error in connecting database.";
 }
 else{
    $result = $conn->query($sql);

    if($result->num_rows>0){

      $return_arr['owners'] = array();

      //fetches a result row as an associative array
      while($row = $result -> fetch_array()){
        array_push($return_arr['owners'], array('username' => $row['username']));
      }

      echo json_encode($return_arr);
  }
 }
  
mysqli_close($conn);

?>