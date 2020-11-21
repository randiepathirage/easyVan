<?php 

    require  "conn.php";

    $amount = $_POST["amount"];
    $date = strtotime($_POST["date"]);
    $type = $_POST["type"];

    $cast_date = date('Y-m-d',$date);
    $vehicle_no = "cap4528";  //$_POST["vehicle_no"];

    $mysql_qry = "INSERT INTO expense(amount,type,date,vehicle_no) VALUES ('$amount','$type','$cast_date','$vehicle_no')";

    if($conn->query($mysql_qry)===TRUE){
        echo "insert success";
        
    }else{
        echo "Error" . $mysql_qry . "<br>" . $conn->error; 
    }

    $conn->close();

?>