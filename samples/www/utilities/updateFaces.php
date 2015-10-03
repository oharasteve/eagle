<html>
<body>

<?php
   include("../db.php");
   
   $query = "TRUNCATE TABLE Faces;";
   $result = mysql_query($query);
   print "<p>Result1 = " . $result . " (1 is good)</p>";
   $query = "LOAD DATA LOCAL INFILE '../utilities/faces.csv' INTO TABLE Faces FIELDS TERMINATED BY ',' ENCLOSED BY '\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n' IGNORE 1 LINES";
   $result = mysql_query($query);
   print "<p>Result2 = " . $result . " (1 is good)</p>";
   
   db_close();
?>

</body>
</html>

