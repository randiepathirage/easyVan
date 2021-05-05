<?php
    require  "conn.php";

    $file = fopen('ApplicationLogs.txt','a');

    date_default_timezone_set("Asia/Colombo");
    $day=date("Y-m-d");
    $time=date("h:i:sa");
    

    $license_no=0;
    $user_firstname=$_POST['firstName'];
    $user_lastname=$_POST['lastName'];
    $nic_no=$_POST['NICNo'];
    $username=$_POST['username'];
    $password=$_POST['password'];
    $address=$_POST['address'];
    $contact_no=$_POST['contactNo'];
    $email=$_POST['email'];
    $user_role=$_POST['userRole'];

    
     /*  
    $user_firstname="A";
    $user_lastname="A";
    $nic_no="33765ere5v";
    $username="resasdfvb";
    $password="123";
    $address="A";
    $contact_no=432265456;
    $email="rfsvvcd";
    $user_role="owner";
    $license_no=0;*/

    //$password=md5($password);

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
    
    $parent=0;
    $driver=0;
    $owner=0;
    $admin=0;

    if($user_role=="parent"){
        $parent=1;
    }else if($user_role=="driver"){
        $driver=1;
    }else if($user_role=="owner"){
        $owner=1;
    }else if($user_role=="admin"){
        $admin=1;
    }

    //avoid adding duplicate values 
    $check_email="SELECT * FROM login WHERE email='$email'";
    $email_result= mysqli_query($conn,$check_email);

   $check_username="SELECT username FROM login WHERE username='$username'";
   $username_result= mysqli_query($conn,$check_username);

   $check_nic="SELECT NIC_no FROM login WHERE NIC_no='$nic_no'";
    $nic_result= mysqli_query($conn,$check_nic);

   $check_contact="SELECT * FROM user WHERE contact_no='$contact_no'";
   $contact_result= mysqli_query($conn,$check_contact);



    if(mysqli_num_rows($email_result)>0){
        echo "This email is already registered";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR");  
    }
    else if(mysqli_num_rows($username_result)>0){
        echo "This username is already taken";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR"); 
    }
    else if(mysqli_num_rows($nic_result)>0){
        echo "This NIC number is already registered";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR"); 
    }

    else if(mysqli_num_rows($contact_result)>0){
        echo "This contact number is already registered";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR"); 
    }
    else{
        $query_login="INSERT INTO login(NIC_no,username,password,salt,email) VALUES ('$nic_no','$username','$password','$salt','$email')";

        $query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$address')";

        $query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,parent_flag,driver_flag,owner_flag,admin_flag,license_no) VALUES ('$nic_no','$parent','$driver','$owner','$admin','$license_no')";

        if($conn->query($query_login)===TRUE && $conn->query($query_user)===TRUE && $conn->query($query_parent_owner_driver)===TRUE){
            echo "User Added Successfully";
            fwrite($file,"\n$day  $time   $nic_no      REGISTRATION $user_role    ALERT");
                
        }
        else{
            echo "Error".$query_login."<br>".$conn->error;
            fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail       ERROR");
        }
    }
        $conn->close();
        fclose($file);

?>