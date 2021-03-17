<?php
    //randie..................................................................
    require  "conn.php";

    
    $id = $_POST["no"];
    //$id='3';
 
    $check_van="SELECT * FROM child WHERE child_no='$id' AND vehicle_no!='NULL'";
    $result = mysqli_query($conn,$check_van);



    if(mysqli_num_rows($result)>0){
         echo "has a van";
    }else{
        echo "no van";
    }

    mysqli_close($conn);
        
?>