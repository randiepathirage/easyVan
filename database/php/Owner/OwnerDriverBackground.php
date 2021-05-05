<?php
 require  "conn.php";

 $userName = $_POST["userName"];
 $user_role = $_POST["userRole"];


 if($user_role=="parent"){
     $parent=1;
 }else if($user_role=="driver"){
    $driver=1;
 }else if($user_role=="owner"){
    $owner=1;
 }



$query_data = " SELECT * FROM login WHERE ( username LIKE ' $userName' ) " ;

$Backg = mysqli_query($conn,$query_data);

echo "meka thamai ".$query_data;

    $conn->close();

?>