<?php
 require  "conn.php";
//  if ($_SERVER['REQUEST_METHOD']=='POST'){
//         $name=$_POST['name'];
//         $add=$_POST['address'];
//         $email=$_POST['email'];
//         $user=$_POST['username'];
//         $pass=$_POST['password'];
//         $query="INSERT INTO `androidreg`(`name`, `address`, `email`, `username`, `password`) VALUES ('$name','$add','$email','$user','$pass')";

//         if(mysqli_query($conn,$query)){
//             echo 'Successfully registerd';
//         }else{
//             echo 'Error in registration';
//         }

//     }else{
        
//         printf("\n");
//         echo "\n"."error in request method"; 
//     }

 $user_name=$_POST["username"];
 $user_pass=$_POST["password"];

$query_parent="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND parent_flag LIKE 1";
$parent_result = mysqli_query($conn,$query_parent);

$query_owner="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND owner_flag LIKE 1";
$owner_result = mysqli_query($conn,$query_owner);

$query_driver="SELECT * FROM parent_owner_driver WHERE NIC_no IN(SELECT NIC_no FROM login WHERE username LIKE '$user_name' AND password LIKE '$user_pass') AND driver_flag LIKE 1";
$driver_result = mysqli_query($conn,$query_driver);

if(mysqli_num_rows($driver_result)>0){
    echo "Login Success driver";
}
else if(mysqli_num_rows($owner_result)>0){
    echo "Login Success owner";
}
else if(mysqli_num_rows($parent_result)>0){
    echo "Login Success parent";
}
else{
    echo "Login Fail";
}
    $conn->close();

?>