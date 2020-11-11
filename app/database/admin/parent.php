<?php
 require "conn.php";

if ($conn->connect_error) {

 die("Connection failed: " . $conn->connect_error);
}

$query = mysqli_query($conn,"SELECT username FROM login INNER JOIN parent_owner_driver ON parent_owner_driver.NIC_no = login.NIC_no where parent_flag=1");

if($query){
    while($row=mysqli_fetch_array($query)){
        $flag[]=$row;
    }
    print(json_encode($flag));
}
mysqli_close($conn);
?>