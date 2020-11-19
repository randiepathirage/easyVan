<?php 
    //randie..........................................................
    require  "conn.php";
     
    $stmt ="SELECT number,no_of_seats_available,total_no_of_seats,model, type,AC_nonAC,caretaker,start_location FROM vehicle";//creating a query
    
    $result_stmt=mysqli_query($conn,$stmt);//executing the query

    $vehicles = array(); 
    
    //traversing through all the result 
    while($row=mysqli_fetch_assoc($result_stmt)){
        $temp = array();
        
        $number=$row["number"];
        $temp['no_of_seats_available'] = $row["no_of_seats_available"]; 
        $temp['total_no_of_seats'] = $row["total_no_of_seats"]; 
        $temp['model'] = $row["model"]; 
        $temp['type'] =$row["type"]; 
        $temp['AC_nonAC'] = $row["AC_nonAC"];
        $temp['caretaker'] =$row["caretaker"];
        $temp['start_location'] =$row["start_location"];


        $schools = "SELECT school FROM school WHERE vehicle_no = '$number' ";//creating a query
        $result_schools=mysqli_query($conn,$schools);//executing the query
        $schoolsarr="";

        while($row=mysqli_fetch_assoc($result_schools)){
   
            $schoolsarr=$schoolsarr.$row["school"];  
            $schoolsarr=$schoolsarr." ,"; 
        }
        $temp['school']=$schoolsarr;

        $towns = "SELECT town FROM town WHERE vehicle_no = '$number' ";//creating a query
        $result_towns=mysqli_query($conn,$towns);//executing the query
        $townsarr="";

        while($row=mysqli_fetch_assoc($result_towns)){
    
            $townsarr=$townsarr.$row["town"];  
            $townsarr=$townsarr." -"; 
        }
        $temp['town']=$townsarr;
        
        array_push($vehicles, $temp);
    }
    
    //displaying the result in json format 
    echo json_encode($vehicles);

?>


