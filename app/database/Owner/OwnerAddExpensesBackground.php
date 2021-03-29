<?php 

    require  "conn.php";
/*
    $user_name='owner1';
    $amount = '9500';
    $date = strtotime('2021-05-21');
    $type = 'fuel';
    $vehicle_no = 'CBF-7375';*/

    $date = strtotime($_POST["date"]);
    $cast_date = date('Y-m-d',$date);
    $amount = $_POST["amount"];
   // $date = strtotime($_POST["date"]);
    $type = $_POST["type"];
    $vehicle_no=$_POST['vehicle_no'];
   // $cast_date = date('Y-m-d',$date);
    



    $mysql_qry = "INSERT INTO expense(amount,type,date,vehicle_no) VALUES ('$amount','$type','$cast_date','$vehicle_no')";

    if($conn->query($mysql_qry)===TRUE){
        echo "insert success";
        
    }else{
        echo "Error" . $mysql_qry . "<br>" . $conn->error; 
    }

    $conn->close();

?>