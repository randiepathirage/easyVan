<?php
    //randie..................................................................
    require  "conn.php";

    
    $vehicle_no = $_POST["no"];
    //$id='2';
 
    $remove_van="DELETE FROM assign WHERE vehicle_no='$vehicle_no'";
    $result = mysqli_query($conn,$remove_van);



    if($result){
         echo "Driver removed";
        
    }
    else{
         echo "Failed";
    }
    mysqli_close($conn);
        
?>