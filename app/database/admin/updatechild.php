<?php

 require "conn.php";

  $cnum=$_POST['child_no'];
  $firstname=$_POST['first_name'];
  $lastname=$_POST['last_name'];
  $grade=$_POST['grade'];
  $school=$_POST['school'];
  $pickup=$_POST['pickup_location'];
  $vehicle_no=$_POST['vehicle_no'];
  $fee=$_POST['monthly_fee'];


$Sql_Query_child = "UPDATE child SET first_name = '$firstname', last_name = '$lastname', grade = '$grade', school = '$school', pickup_location = '$pickup', vehicle_no = 'vehicle_no', monthly_fee = 'fee' WHERE child_no = $cnum";

 if($conn->query($Sql_Query_child)===TRUE){
            echo 'Record Updated Successfully';
          }

 else
    {
        echo 'Something went wrong';
    }
 mysqli_close($conn);

?>