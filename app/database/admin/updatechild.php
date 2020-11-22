<?php

 require "conn.php";

  $cnum=$_POST['child_no'];
  $pnic=$_POST['parent_NIC_no'];
  $firstname=$_POST['first_name'];
  $lastname=$_POST['last_name'];
  $school=$_POST['school'];
  $grade=$_POST['grade'];
  $pickup=$_POST['pickup_location'];
  $vehicle_no=$_POST['vehicle_no'];
  $fee=$_POST['monthly_fee'];
  $pname=$_POST['parent_name'];

    /*$cnum="2";
    $pnic="986760296v";
    $firstname="Hemna";
    $lastname="Pathirage";
    $school="Musaeus collage";
    $grade="5";
    $pickup="Maharagama";
    $vehicle_no="CBF-7375";
    $fee="6000";
    $pname="zz";*/


$Sql_Query_child = "UPDATE child SET first_name = '$firstname', last_name = '$lastname', grade = '$grade', school = '$school', pickup_location = '$pickup', vehicle_no = '$vehicle_no', monthly_fee = '$fee' WHERE child_no = '$cnum'";

 if($conn->query($Sql_Query_child)===TRUE){
            echo 'Record Updated Successfully';
          }

 else
    {
        echo 'Something went wrong';
    }
 mysqli_close($conn);

?>