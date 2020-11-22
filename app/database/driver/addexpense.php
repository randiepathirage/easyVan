<?php 

    require  "conn.php";

    $user_name=$_POST['username'];
    $amount = $_POST["amount"];
    $date = strtotime($_POST["date"]);
    $type = $_POST["type"];

    $cast_date = date('Y-m-d',$date);
// genarate driver NIC using username>>>>>>>>>

$NIC = "SELECT NIC_no FROM login  WHERE username = '$user_name' ";
$query_NIC= mysqli_query($conn,$NIC);
$NIC_result = mysqli_fetch_assoc($query_NIC);
$Driver_NIC= $NIC_result['NIC_no'];
//>>>>>>>>>>>>

//Genarate vehicle number.........................
$VehicelNO =
"SELECT vehicle_no 
FROM assign
WHERE driver_NIC_no='$Driver_NIC';
";

$query_VehicelNO= mysqli_query($conn,
$VehicelNO);

$VN_result = mysqli_fetch_assoc($query_VehicelNO);

$Result_VehicelNO= $VN_result ['vehicle_no'];
//...............................................

    //$vehicle_no = "cap4528";  //$_POST["vehicle_no"];

    $mysql_qry = "INSERT INTO expense(amount,type,date,vehicle_no) VALUES ('$amount','$type','$cast_date','$Result_VehicelNO')";

    if($conn->query($mysql_qry)===TRUE){
        echo "insert success";
        
    }else{
        echo "Error" . $mysql_qry . "<br>" . $conn->error; 
    }

    $conn->close();

?>