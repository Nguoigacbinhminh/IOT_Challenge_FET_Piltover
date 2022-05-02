<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="The main page of F1 manager IoT project!">
        <title>F1 manager</title>
        <link href="./css/style.css" rel="stylesheet" />
    </head>
    <body>
        <style>
            table{
                border-color: black;
                border:solid 4px;
                background-color: white;
            }

            table td{
                border: inset 2px;
            }
        </style>

        <div class="wrapper">
            <div class="header">
                <div class="header_text"><u><b>Manage main page of IoT project</b></u></div>
            </div>

            <div class="nav">
                <a href="add_patient.html" target="popup" onclick="window.open('add_patient.html','popup','width=600,height=600'); return false;">Add Patient</a>
            </div>

            <div class="container">

                <div class="tablearea">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th>Address</th>
                        </tr>

                        <?php
                            include 'php/db_connect.php';
                            $conn = OpenCon();
                            // echo "Connected Successfully";
                            if ($conn->connect_error){
                                die("connection failed: ".$conn->connect_error);
                            }
                            $sqlgetin4="SELECT * FROM PatientsList";
                            $result= $conn-> query($sqlgetin4);

                            if ($result->num_rows>0){
                                while($row=$result->fetch_assoc()){
                                    echo "<tr><td>".$row["ID"]."</td><td>".$row["Name"]."</td><td>".$row["Age"]."</td><td>".$row["Gender"]."</td><td>".$row["Address"]."</td></tr>";
                                }
                                echo "</table>";
                            }
                            else{
                                echo "0 result!";
                            }
                            $conn->close();
                        ?>PatientsList
                    </table>
                    
                </div>

                <div class="tablearea">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Address</th>
                            <th>Temperature</th>
                            <th>HeartRate</th>
                            <th>SpO2</th>
                        </tr>

                        <?php
                            // include 'php/db_connect.php';
                            $conn = OpenCon();
                            // echo "Connected Successfully";
                            if ($conn->connect_error){
                                die("connection failed: ".$conn->connect_error);
                            }
                            $sqlgetin4="SELECT * FROM WarningPatient";
                            $result= $conn-> query($sqlgetin4);

                            if ($result->num_rows>0){
                                while($row=$result->fetch_assoc()){
                                    echo "<tr><td>".$row["ID"]."</td><td>".$row["Address"]."</td><td>".$row["Temperature"]."</td><td>".$row["HeartRate"]."</td><td>".$row["SpO2"]."</td></tr>";
                                }
                                echo "</table>";
                            }
                            else{
                                echo "0 result!";
                            }
                            $conn->close();
                        ?>
                        
                    </table>
                </div>

            </div>

            <div class="footer">
                <div clas="copyright"><u><b>This is property of FET_Piltover teams</b></u>
                    <div class="note">
                        Ha Duyen Duc
                        <br>Nguyen Hoang Huy
                        <br>Nguyen Huu Hung
                        <br>Pham Dang Dang
                        <br>Nguyen Van Duc
                    </div>
                    
                </div>
            </div>
            
        </div>

        
    </body>
</html>