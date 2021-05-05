<?php 
    //randie..........................................................
    require  "conn.php";

   $user_name=$_POST["username"];

    $stmt ="SELECT child_no,grade,school,first_name,last_name,pickup_location,dropoff_location FROM child INNER JOIN `login` ON child.parent_NIC_no=login.NIC_no WHERE login.username='$user_name'";//creating a query
    $result_stmt=mysqli_query($conn,$stmt);//executing the query

    $children = array(); 
    
    //traversing through all the result 
    while($row=mysqli_fetch_assoc($result_stmt)){
        $temp = array();
        
        $temp['childNo']=$row['child_no'];
        $temp['grade'] = $row["grade"]; 
        $temp['school'] = $row["school"]; 
        $temp['firstName'] = $row["first_name"]; 
        $temp['lastName'] =$row["last_name"]; 
        $temp['pickupLocation'] = $row["pickup_location"];
        $temp['dropoffLocation'] =$row["dropoff_location"];

        array_push($children, $temp);
    }
    
    //displaying the result in json format 
    echo json_encode($children);

?>