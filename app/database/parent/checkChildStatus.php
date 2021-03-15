<?php
    //randie..................................................................
    require  "conn.php";

    
    $id = $_POST["no"];
 
    $check_van="SELECT * FROM child_assign WHERE child_no=$id";
    $result = mysqli_query($conn,$check_van);



    if(mysqli_num_rows($result)>0){
         echo "has a van";
    }else{
        echo "no van";
    }

    mysqli_close($conn);
        
?>