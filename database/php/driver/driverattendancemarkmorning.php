<?php 

require  "conn.php";

$user_name = $_POST['username'];
$fname = $_POST["fname"];


//$user_name="Nimal";


// genarate driver NIC using username>>>>>>>>>

$NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name' ";
$query_NIC= mysqli_query($conn,$NIC);
$NIC_result = mysqli_fetch_assoc($query_NIC);
$Driver_NIC= $NIC_result['NIC_no'];
//>>>>>>>>>>>>

//Genarate vehicle number using NIC.........................
$VehicelNO = "SELECT vehicle_no FROM assign WHERE driver_NIC_no = '$Driver_NIC';";

$query_VehicelNO= mysqli_query($conn,$VehicelNO);

$VN_result = mysqli_fetch_assoc($query_VehicelNO);

$Result_VehicelNO= $VN_result ['vehicle_no'];
//...............................................

//Genarate child number using vehicle no.........................

$childNo = "SELECT child_no FROM child WHERE first_name = '$fname';";
$query_Childno = mysqli_query($conn,$childNo);
$CN_result = mysqli_fetch_assoc($query_Childno);
$Result_ChildNO = $CN_result['child_no'];

//...............................................

//Genarate child no.........................
$mysql_qury = "INSERT INTO attendance(child_no,date,time,morning) VALUES ('$Result_ChildNO',now(),now(),'1')";

//...........................................

if($conn->query($mysql_qury)===TRUE){
    echo "submit success";
}else{
    echo "Invalid entry" . $mysql_qury . "<br>" . $conn->error; 
}


$conn->close();

?>