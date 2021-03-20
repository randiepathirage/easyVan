<?php
    //randie..................................................................
    require  "conn.php";

    
    $id = $_POST["no"];
    //$id='2';
 
    $remove_van="UPDATE child SET vehicle_no='NULL', start_date='NULL',fees='0.00' WHERE child_no='$id'";
    $result = mysqli_query($conn,$remove_van);



    if($result){
         echo "van removed";
        
    }
    else{
         echo "Failed";
    }
    mysqli_close($conn);
        
?>