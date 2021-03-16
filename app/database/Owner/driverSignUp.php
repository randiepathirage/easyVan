<?php
 require  "conn.php";

$file = fopen('ApplicationLogs.txt','a');

date_default_timezone_set("Asia/Colombo");
$day=date("Y-m-d");
$time=date("h:i:sa");

 $user_firstname=$_POST['firstName'];
 $user_lastname=$_POST['lastName'];
 $nic_no=$_POST['NICNo'];
 $username=$_POST['username'];
 $password=$_POST['password'];
 $address=$_POST['address'];
 $contact_no=$_POST['contactNo'];
 $email=$_POST['email'];
 $license_no = $_POST['licenseNo'];

 $ownerName =$_POST['ownerName'];
 
 $user_role = "driver";

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




   $check_nic="SELECT NIC_no FROM login WHERE NIC_no='$nic_no'";
    $nic_result= mysqli_query($conn,$check_nic);

   $check_contact="SELECT * FROM user WHERE contact_no='$contact_no'";
   $contact_result= mysqli_query($conn,$check_contact);


   $check_username="SELECT username FROM login WHERE username='$username'";
   $username_result= mysqli_query($conn,$check_username);



if(mysqli_num_rows($nic_result)>0){
        echo "This NIC is already registered";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR");  
    }
    else if(mysqli_num_rows($contact_result)>0){
        echo "This Contact_no is already taken";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR"); 
    }
     else if(mysqli_num_rows($username_result)>0){
        echo "This username is already taken";
        fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail      ERROR"); 
    }

else{
    

        //select owner NIC
         $owner = "SELECT NIC_no FROM login WHERE username ='$ownerName' ";
        $results = mysqli_query($conn,$owner);

        $result = mysqli_fetch_assoc($results);

        $owner =  $result['NIC_no'];



        //update DB
        $query_login="INSERT INTO login(NIC_no,username,password,salt,email) VALUES ('$nic_no','$username',' $password','$salt',' $email')";

        $query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$user_middlename','$address')";

        $query_user_role="INSERT INTO user_role(NIC_no,user_role) VALUES ('$nic_no','$user_role')";

        $query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,driver_flag,license_no) VALUES ('$nic_no','1','$license_no')";

        $query_assign = "INSERT INTO assign(driver_NIC_no, owner_NIC_no) VALUES ('$nic_no','$owner')";

        if($conn->query($query_login)===TRUE){
            if($conn->query($query_user)===TRUE){
                if($conn->query($query_user_role)===TRUE){
                    if($conn->query($query_parent_owner_driver)===TRUE){
                    	if($conn->query($query_assign)===TRUE){
                        	echo "Insert Successful Driver";
                            fwrite($file,"\n$day  $time   $nic_no      REGISTRATION $user_role    ALERT");
                    	}
                    }
                }
            }
        }

        else{
            echo "Error".$query_login."<br>".$conn->error;
            fwrite($file,"\n$day  $time   $nic_no      REGISTRATION fail       ERROR");
        }
}
    $conn->close();
    fclose($file);

?>