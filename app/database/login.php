<?php
    //randie..............................................................................
    require  "conn.php";

    $file = fopen('accessLogs.txt','a');
    //$write="\nDate2    Time2    Username1    Log Prefix1      Log Msg1";

    date_default_timezone_set("Asia/Colombo");
    $day=date("Y-m-d");
    $time=date("h:i:sa");

    $user_name=$_POST["username"];
    $user_pass=$_POST["password"];


    $user_pass=md5($user_pass);


    //$query_parent="SELECT * FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass'";
    //$parent_result = mysqli_query($conn,$query_parent);

    $query_parent="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND parent_flag LIKE 1";
    $parent_result = mysqli_query($conn,$query_parent);

    $query_owner="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND owner_flag LIKE 1";
    $owner_result = mysqli_query($conn,$query_owner);

    $query_driver="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND driver_flag LIKE 1";
    $driver_result = mysqli_query($conn,$query_driver);

    $query_admin="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND admin_flag LIKE 1";
    $admin_result = mysqli_query($conn,$query_admin);

    if(mysqli_num_rows($driver_result)>0){
        echo "Login Success driver";

        fwrite($file,"\n$day  $time   $user_name      LOGIN driver       Success");
    }
    else if(mysqli_num_rows($owner_result)>0){
        echo "Login Success owner";

        fwrite($file,"\n$day  $time   $user_name    LOGIN owner        Success");
    }
    else if(mysqli_num_rows($parent_result)>0){
        echo "Login Success parent";

        fwrite($file,"\n$day  $time   $user_name      LOGIN parent       Success");
    }
    else if(mysqli_num_rows($admin_result)>0){
        echo "Login Success admin";

        fwrite($file,"\n$day  $time   $user_name       LOGIN admin        Success");
    }
    else{
        echo "Login Fail";

        fwrite($file,"\n$day  $time   $user_name       LOGIN user         Fail");
    }
        $conn->close();
        fclose($file);

?>