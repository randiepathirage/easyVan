<?php 

    require  "conn.php";
     
    $stmt = $conn->prepare("SELECT `number`, `no_of_seats_available`, `total_no_of_seats`, `model`, `type`, `permit_no`, `image` FROM `vehicle` ");//creating a query
    
    $stmt->execute();//executing the query

    $stmt->bind_result($number, $no_of_seats_available, $total_no_of_seats, $model, $type, $permit_no, $image);//binding results to the query 

    $vehicles = array(); 
    
    //traversing through all the result 
    while($stmt->fetch()){
    $temp = array();
    $temp['number'] = $number; 
    $temp['no_of_seats_available'] = $no_of_seats_available; 
    $temp['total_no_of_seats'] = $total_no_of_seats; 
    $temp['model'] = $model; 
    $temp['type'] = $type; 
    $temp['permit_no'] = $permit_no; 
    $temp['image']= $image;
    array_push($vehicles, $temp);
    }
    
    //displaying the result in json format 
    echo json_encode($vehicles);

    ?>