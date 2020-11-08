<?php 

    require  "conn.php";
     
    $stmt = $conn->prepare("SELECT`NIC_no`,`contact_no`,`last_name`,`first_name`,`address` FROM `user`");//creating a query
    
    $stmt->execute();//executing the query

    $stmt->bind_result($NIC_no, $contact_no, $last_name, $first_name, $address);//binding results to the query 

    $vehicles = array(); 
    
    //traversing through all the result 
    while($stmt->fetch()){
    $temp = array();
    $temp['NIC_no'] = $NIC_no; 
    $temp['contact_no'] = $contact_no; 
    $temp['last_name'] = $last_name; 
    $temp['first_name'] = $first_name; 
    $temp['address'] = $address; 

    array_push($vehicles, $temp);
    }
    
    //displaying the result in json format 
    echo json_encode($vehicles);

    ?>