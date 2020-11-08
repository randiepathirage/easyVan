<?php
    require  "conn.php";
    
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
    $nic_no="fdefe";
    $username="efdewfe";
    $password="123";
    $address="A";
    $contact_no=555555515556;
    $email="feedfe";
    $user_role="parent";
    $license_no=0;*/

    $password=md5($password);
    
    $parent=0;
    $driver=0;
    $owner=0;

    if($user_role=="parent"){
        $parent=1;
    }else if($user_role=="driver"){
        $driver=1;
    }else if($user_role=="owner"){
        $owner=1;
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
    }
    else if(mysqli_num_rows($username_result)>0){
        echo "This username is already taken"; 
    }
    else if(mysqli_num_rows($nic_result)>0){
        echo "This NIC number is already registered"; 
    }

    else if(mysqli_num_rows($contact_result)>0){
        echo "This contact number is already registered"; 
    }
    else{
        $query_login="INSERT INTO login(NIC_no,username,password,email) VALUES ('$nic_no','$username','$password','$email')";

        $query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$address')";

        $query_user_role="INSERT INTO user_role(NIC_no,user_role) VALUES ('$nic_no','$user_role')";

        $query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,parent_flag,driver_flag,owner_flag,license_no) VALUES ('$nic_no','$parent','$driver','$owner','$license_no')";

        if($conn->query($query_login)===TRUE && $conn->query($query_user)===TRUE && $conn->query($query_user_role)===TRUE && $conn->query($query_parent_owner_driver)===TRUE){
            echo "Insert Successful";
                
        }
        else{
            echo "Error".$query_login."<br>".$conn->error;
        }
    }
        $conn->close();

?>