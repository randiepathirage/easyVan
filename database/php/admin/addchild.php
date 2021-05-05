<?php

    require "conn.php";

       $pnic=$_POST['parent_NIC_no'];
       $firstname=$_POST['first_name'];
       $lastname=$_POST['last_name'];
       $school=$_POST['school'];
       $grade=$_POST['grade'];
       $pickup=$_POST['pickup_location'];
       $dropoff=$_POST['dropoff_location'];
       $sdate=$_POST['start_date'];
       $vehicle_no=$_POST['vehicle_no'];
       $fee=$_POST['monthly_fee'];

      /* $pnic="881040945v";
       $firstname="Nimesha";
       $lastname="De silva";
       $school="Vishaka College";
       $grade="9";
       $pickup="Maharagama";
       $dropoff="Colombo";
       $sdate="2020-05-01";
       $vehicle_no="SP-8585";
       $fee="6000";*/

     $sql = "INSERT INTO `child` (`child_no`, `parent_NIC_no`, `grade`, `school`, `first_name`, `last_name`, `pickup_location`, `dropoff_location`, `vehicle_no`, `start_date`, `fees`) VALUES (NULL, '$pnic', '$grade', '$school', '$firstname', '$lastname', '$pickup', '$dropoff', '$vehicle_no', '$sdate', '$fee');";

     $result = mysqli_query($conn,$sql);

     if($result){
         echo "Data Inserted";

     }
     else{
         echo "Failed";
     }
     mysqli_close($conn);






?>