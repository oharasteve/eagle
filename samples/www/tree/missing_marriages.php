<html>

<head>
<title>Check for Missing Marriages</title>
</head>

<body bgcolor="#f0f0f0">
<h1>Check for Missing Marriages</h1>

<?php
   # Connect to database
   include("../db.php");

   $query1 = "SELECT * FROM Marriages";
   $response1 = mysql_query($query1);
   $marriages = array();
   while ($row = mysql_fetch_array($response1))
   {
      $husband = $row["HusbandKey"];
	  $wife = $row["WifeKey"];
	  $wed = $husband . "&" . $wife;
	  $marriages[$wed] = true;
   }
   
   $query2 = "SELECT * FROM People";
   $response2 = mysql_query($query2);
   while ($row = mysql_fetch_array($response2))
   {
      $person = $row["KeyName"];
	  $mother = $row["MotherKey"];
	  $father = $row["FatherKey"];
	  if ($mother && $mother != "" && $father && $father != "")
	  {
	     $parents = $father . "&" . $mother;
		 if ($marriages[$parents] != true)
		 {
		    print "<br/>" . $person . " " . $mother . " " . $father . "\n";
		 }
	  }
   }
   
   db_close();
?>

</body>
</html>
