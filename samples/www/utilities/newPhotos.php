<html>
<body>

<?php
   include("../db.php");
   
   $query = "LOAD DATA LOCAL INFILE '../utilities/photos.csv' INTO TABLE Photos FIELDS TERMINATED BY ';' ENCLOSED BY '\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n'";
   $result = mysql_query($query);
   print "<p>Result = " . $result . " (1 is good)</p>";
   
   # Look for errors and warnings ...
   $warningCountResult = mysql_query("SELECT @@warning_count");
   if ($warningCountResult)
   {
      $warningCount = mysql_fetch_row($warningCountResult);
	  if ($warningCount[0] > 0)
	  {
         // Have warnings. Just so happens that there is one warning per row
		 // Warning 1261: Row 1 doesn't contain data for all columns 
		 print "<p>Rows = " . $warningCount[0];
		 
		 // Following is useless. Should also be a "while" instead of an "if"
         //$warningDetailResult = mysql_query("SHOW WARNINGS");
         //if ($warningDetailResult)
		 //{
         //   $warning = mysql_fetch_row($warningDetailResult);
         //   print "<li>" . $warning[0] . " " . $warning[1] . ": " . $warning[2];
         //}
      }//Else no warnings
   } 
 
   db_close();
?>

</body>
</html>

