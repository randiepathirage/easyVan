<?php
    require 'conn.php';

    $password = $_POST["password"];
    $password = md5($password);
    $email=$_POST["email"];

    $update = "UPDATE login set password = '$password' WHERE email='$email'";
    $result_login = mysqli_query($conn,$update);
    if ($update){
        echo "Password Updated";           
    }
    else{
        echo "Failed";
    }
    mysqli_close($conn)
?>