

<?php 
  
    require  "conn.php";

  //$user_name=$_POST["username"];
    $user_name="dimuthu";



    $user = array();

  
    $stmt = "SELECT vehicle.number
            FROM `vehicle`     
            INNER JOIN login ON login.NIC_no=vehicle.owner_NIC_no 
            WHERE login.username='$user_name'";


    $stmt_exe=mysqli_query($conn,$stmt);

   $result=array();
   $result['data'] = array();


    while($row=mysqli_fetch_assoc($stmt_exe)){

        $index['Van ID']= $row['number'];
      

        array_push($result['data'], $index);
    }

    echo json_encode($result);

   
    

    ?>