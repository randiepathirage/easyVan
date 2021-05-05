<?php 
    //randie..........................................................
    require  "conn.php";

    $stmt ="SELECT review,date,rate FROM rate";//creating a query
    $result_stmt=mysqli_query($conn,$stmt);//executing the query

    $reviews = array(); 
    
    //traversing through all the result 
    while($row=mysqli_fetch_assoc($result_stmt)){
        $temp = array();
        
        $temp['review']=$row['review'];
        $temp['date'] = $row["date"]; 
        $temp['rate'] = $row["rate"]; 

        array_push($reviews, $temp);
    }
    
    //displaying the result in json format 
    echo json_encode($reviews);

?>