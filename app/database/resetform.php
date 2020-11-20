<?php
require 'conn.php';
$email = $_GET["key"];

/*if(isset($_GET['key'])){
 $email = $_GET['key'];
}*/

$sql = "SELECT password FROM login where email='$email'";
$result = mysqli_query($conn,$sql);
if(mysqli_num_rows($result) === 1){

    if (isset($_POST['submit'])){
        $password = $_POST['password'];
        $cpassword = $_POST['cpassword'];


        if ($password == "" && $cpassword == ""){
            echo "Fields are empty";
        }else{
            if ($password == $cpassword){
                $password=md5($password);
                $update = "UPDATE login set password = '$password' WHERE email='$email'";
                if (mysqli_query($conn,$update)){
                    echo "<h2> Password is changed successfully !!!</h2>";
                }else{
                    echo "Error refresh and reclick the email link";
                }
            }else{
                echo "Passwords do not match";
            }
        }
    }else{
        echo "Click Submit to change the Password";
    }
  }

?>

<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
    <form action  = "" method = "post">
        <h1><?php echo "Welcome ".$email?></h1>
        Enter New Password : <input type = "text" name = "password"> <br>
        Confirm Password : <input type = "text" name = "cpassword"> <br>
        <input type = "submit" name = "submit">

     </form>

</body>
</html>