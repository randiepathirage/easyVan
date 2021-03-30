<?php
 require  "conn.php";


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

 
/* $user_role = "driver";
 $user_middlename = "None";*/
/*  $user_firstname="dshde";
 $user_lastname="ss";
 $nic_no="1234567";
 $username="driver1";
 $password="Driver123@";
 $address="111";
 $contact_no="s1212";
 $email="ddd@gmail.com";
 $license_no = "1s21212";

 $ownerName ="owner";

 $NIC_no=123456789;*/
 






   $check_nic="SELECT NIC_no FROM login WHERE NIC_no='$nic_no'";
    $nic_result= mysqli_query($conn,$check_nic);

   $check_contact="SELECT * FROM user WHERE contact_no='$contact_no'";
   $contact_result= mysqli_query($conn,$check_contact);


   $check_username="SELECT username FROM login WHERE username='$username'";
   $username_result= mysqli_query($conn,$check_username);



if(mysqli_num_rows($nic_result)>0){
        echo "This NIC is already registered";  
    }
    else if(mysqli_num_rows($contact_result)>0){
        echo "This Contact_no is already taken"; 
    }
     else if(mysqli_num_rows($username_result)>0){
        echo "This username is already taken"; 
    }

else{
    

        //select owner NIC
         $owner = "SELECT NIC_no FROM login WHERE username ='$ownerName' ";
        $results = mysqli_query($conn,$owner);

        $result = mysqli_fetch_assoc($results);

        $owner =  $result['NIC_no'];



        //update DB
        $query_login="INSERT INTO login(NIC_no,username,password,email) VALUES ('$nic_no','$username',' $password',' $email')";

        $query_user="INSERT INTO user(NIC_no,contact_no,last_name,first_name,address) VALUES ('$nic_no','$contact_no','$user_lastname','$user_firstname','$address')";

       /* $query_user_role="INSERT INTO user_role(NIC_no,user_role) VALUES ('$nic_no','$user_role')";*/

        $query_parent_owner_driver="INSERT INTO parent_owner_driver(NIC_no,driver_flag,license_no) VALUES ('$nic_no','1','$license_no')";

        $query_assign = "INSERT INTO assign(driver_NIC_no, owner_NIC_no,vehicle_no) VALUES ('$nic_no','$owner','$v_no')";

        if($conn->query($query_login)===TRUE){
            if($conn->query($query_user)===TRUE){
               /* if($conn->query($query_user_role)===TRUE)*/
                    if($conn->query($query_parent_owner_driver)===TRUE){
                    	if($conn->query($query_assign)===TRUE){
                        	echo "Insert Successful Driver";
                    	}
                    }
                
            }
        }

        else{
            echo "Error".$query_login."<br>".$conn->error;
            echo "fail";
        }
}
    $conn->close();

?>