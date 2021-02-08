<?php
    //randie..................................................................
    require  "conn.php";

    
    $id = $_POST["no"];
 
    $remove_van="DELETE FROM child_assign WHERE child_no=$id";
    $result = mysqli_query($conn,$remove_van);



    if($result){
         echo "van removed";
        
    }
    else{
         echo "Failed";
    }
    mysqli_close($conn);
        
?>