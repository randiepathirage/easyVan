<?php

    require 'conn.php';
    

    $password = $_POST["password"];
    $email = $_POST["email"]; 

    $salt=generateRandomString();

    function generateRandomString($length = 20) {
       $characters='0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+{}|[]/?~`';
       $charactersLength = strlen($characters);
       $randomString = '';
       for ($i = 0; $i < $length; $i++) {
           $randomString .= $characters[rand(0, $charactersLength - 1)];
       }
       return $randomString;
    }

    //$password=md5($password);
    $password=$password.$salt;
    $password=hash('sha256',$password);


    $update = "UPDATE login SET password = '$password',salt='$salt' WHERE email='$email'";
    $result_update = mysqli_query($conn,$update);
    if ($result_update){
        echo "Password Updated";           
    }
    else{
        echo "Incorrect Password";
    }
    mysqli_close($conn)
?>