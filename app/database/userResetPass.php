<?php
    //randie..........................................................
    require 'conn.php';
    
    /*$password = "randie";
    $oldpass="rrr";
    $username="sdfghj";*/


    $password = $_POST["password"];
    $password = md5($password);
    $oldpass=$_POST["oldPassword"];
    $oldpass = md5($oldpass);
    $username = $_POST["username"];


    $update = "UPDATE login SET password = '$password' WHERE username='$username' AND password='$oldpass'";
    $result_update = mysqli_query($conn,$update);
    if ($result_update){
        echo "Password Updated";           
    }
    else{
        echo "Incorrect Password";
    }
    mysqli_close($conn)
?>