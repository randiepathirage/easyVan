<?php

require 'conn.php';

$cnum = $_POST['child_no'];


$Query_child = "DELETE FROM child WHERE child_no = '$cnum'";
$Query_abdate = "DELETE FROM absence_date WHERE child_no = '$cnum'";

 if(mysqli_query($conn,$Query_child)){
    if(mysqli_query($conn,$Query_abdate)){
            echo 'Data Deleted';
                }
              }

else
{
 echo 'Something went wrong';
 }

 mysqli_close($conn);
?>