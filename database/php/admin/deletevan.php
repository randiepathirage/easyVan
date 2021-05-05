<?php

require 'conn.php';

 $number = $_POST['number'];
 //$number = "001";

 $Query_vehi = "DELETE FROM vehicle WHERE number = '$number'";
 $Query_insurance = "DELETE FROM insurance WHERE vehicle_no = '$number'";
 $Query_license = "DELETE FROM license WHERE vehicle_no = '$number'";
 $Query_assign = "DELETE FROM assign WHERE vehicle_no = '$number'";


 $query_count="SELECT vehicle_no from child where vehicle_no = '$number'";
 $result = mysqli_query($conn,$query_count);
 $rows = mysqli_num_rows($result);

 if($rows == 0) {
      if(mysqli_query($conn,$Query_assign)){
        if(mysqli_query($conn,$Query_insurance)){
            if(mysqli_query($conn,$Query_license)){
                if(mysqli_query($conn,$Query_vehi)){
                     echo 'Data Deleted';
                    }
                  }
                }
             }


    else {
         echo 'Something went wrong';
      }
  }

 else{
     echo 'This Van Cannot Be Deleted';
     }

 mysqli_free_result($result);
 mysqli_close($conn);
?>