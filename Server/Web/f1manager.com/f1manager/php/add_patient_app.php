<!DOCTYPE html>
<html>
    <head></head>
    <body>
    <?php
        include 'db_connect.php';
        $conn= OpenCon();

        // $conn = new mysqli($dbhost,$dbuser,$dbpass,$db) or die("Connection failed: %s\n". $conn->error);

        $id = (int)$_REQUEST['id'];
        $name = $_REQUEST['name'];
        $age = (int)$_REQUEST['age'];
        $gender = (int)$_REQUEST['gender'];
        $addr = $_REQUEST['addr'];

        $sqlqueri = "INSERT INTO PatientsList VALUES ('$id','$name','$age','$gender','$addr')";


        if ($conn->query($sqlqueri)==TRUE){
            echo "Patient added to the database!";
        }else{
            echo "Error: ". $sqlqueri."<br>".$conn->error;
        }

        mysqli_close($conn);

        ?>
    </body>
</html>

