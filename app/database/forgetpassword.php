<?php
    require 'conn.php';
    require 'PHPMailer/PHPMailerAutoload.php';
    require 'PHPMailer/class.phpmailer.php';
    require 'PHPMailer/class.smtp.php';
    require 'admin.php';

    $email = $_POST["email"];
    $code=$_POST["random"];
    //$nic_no = "555";
    //$email = "mruv98@gmail.com";


    $sql = "SELECT * FROM login where email LIKE '$email'";
    $query = mysqli_query($conn,$sql);

   if(mysqli_num_rows($query)===1)
   {
        $mail = new PHPMailer;

        //$mail->SMTPDebug = 3;                               // Enable verbose debug output

        $mail->isSMTP();                                      // Set mailer to use SMTP
        $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
        $mail->SMTPAuth = true;                               // Enable SMTP authentication
        $mail->Username = $adminemail;                 // SMTP username
        $mail->Password = $adminpassword;                           // SMTP password
        $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
        $mail->Port = 587;                                    // TCP port to connect to

        $mail->setFrom( $adminemail, 'EZvan');
        $mail->addAddress($email);     // Add a recipient
        $mail->addReplyTo( $adminemail, 'EZvan');


        /*$mail->Subject = 'Forgot Password for EZvan';
        $mail->isHTML(true);
        $mail->Body    = "click here the link below :
         "https://localhost/easyvan/resetPassword.php?".key=$email;*/


         //$url="http://". $_SERVER["HTTP_HOST"] . dirname($_SERVER["PHP_SELF"]) ."/resetform.php?".key=$email;
         $url="http://localhost/easyvan/resetform.php?key=$email";
         $mail->Subject = 'Your password reset code';
         $mail->isHTML(true);
         $mail->Body    = "<h1>You requested a password reset</h1>
                                here is your code to reset the password $code";


        if(!$mail->send()) {
            echo 'Message could not be sent.';
            echo 'Mailer Error: ' . $mail->ErrorInfo;
        } else {
            $msg["mail"] = "send";
            echo json_encode($msg);
        }
    }
    else
    {
        echo "Enter A Valid Email";
    }
    mysqli_close($conn);

?>