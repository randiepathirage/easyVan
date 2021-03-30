<?php
    require 'conn.php';
    require 'PHPMailer/PHPMailer.php';
    require 'PHPMailer/SMTP.php';
    require 'PHPMailer/Exception.php';
    require 'admin.php';

    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\SMTP;
    use PHPMailer\PHPMailer\Exception;

    $email = $_POST["email"];
    $code=$_POST["random"];
    //$nic_no = "555";
    //$email = "mruv98@gmail.com";
    //$code="5555";

   
    $sql = "SELECT * FROM login where email LIKE '$email'";
    $query = mysqli_query($conn,$sql);

   if(mysqli_num_rows($query)==1)
   {
        $mail = new PHPMailer();

        //$mail->SMTPDebug = 3;                               // Enable verbose debug output

        $mail->isSMTP();                                      // Set mailer to use SMTP
        $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
        $mail->SMTPAuth = true;                               // Enable SMTP authentication
        $mail->Username = $adminemail;                 // SMTP username
        $mail->Password = $adminpassword;                           // SMTP password
        $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
        $mail->Port = "587";                                    // TCP port to connect to

        $mail->setFrom( $adminemail, 'EasyVan');
        $mail->addAddress($email);     // Add a recipient
        $mail->addReplyTo( $adminemail, 'EasyVan');


       
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
    $mail->smtpClose();
    mysqli_close($conn);

?>