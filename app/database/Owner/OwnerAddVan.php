<?php
 require  "conn.php";

//variable 

$vehicleNo=$_POST['vehicleNo'];
$vehicleType=$_POST['vehicleType'];
$totalSeats=$_POST['totalSeats'];
$model=$_POST['model'];
$permitNo=$_POST['permitNo'];
$caretaker=$_POST['caretaker'];
$condition=$_POST['condition'];
$username=$_POST['name'];

/*
$vehicleNo="CDA 2823";
 $vehicleType="Van";
$totalSeats='10';
$model="dOLPING";
$permitNo="Ak47";
$caretaker="Randie";
$condition="AC";*/
//$owner ='123456789';
//$username=$_POST['name'];

/*$owner = "SELECT username FROM login WHERE username = $username";*/
$owner = '123456789';


$query_add="INSERT INTO vehicle( number ,type,owner_NIC_no,total_no_of_seats,AC_none_AC, caretaker, permit_no, model , no_of_seats_available) 

	VALUES ('$vehicleNo' , '$vehicleType','$owner' , '$totalSeats', '$condition', '$caretaker', '$permitNo', '$model', '$totalSeats')";

/*
$query_add="INSERT INTO vehicle(type,total_no_of_seats,model,permit_no,caretaker,A/C_non_A/C,owner_NIC_no) VALUES ($vehicleType','$totalSeats','$model','$permitNo','$caretaker',' $condition','1243')";

*/
if($conn->query($query_add)===TRUE){
    //if($conn->query($query_user)===TRUE){
      //  if($conn->query($query_user_role)===TRUE){
          //  if($conn->query($query_parent_owner_driver)===TRUE){
                echo "Insert Successful Van";
          /*  }
        }
    }*/
}
else{
    echo "Error".$query_add."<br>".$conn->error;
}
    $conn->close();


?>