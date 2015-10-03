<html>

<head>
<title>GED Entry</title>
</head>

<body bgcolor="#f0f0f0">
<?php
   # Connect to database
   include("../db.php");

   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

   $now = getdate();
   $currMon = $now["mon"];	# 1 - 12
   $currDay = $now["mday"]; # 1 - 31
   $currYr = $now["year"];  # e.g., 2004

   $key = $_REQUEST["Key"];
   
   $query = "SELECT * FROM People WHERE KeyName='" . $key . "'";
   $results = mysql_query($query);
   $person = mysql_fetch_array($results);

   print "<br/>0 HEAD";

   doPerson($person, 1);
   
   print "<br/>0 TRLR";
   
   db_close();
   
function doPerson($person, $seq)
{
   $first = $person["First"];
   $middle = $person["Middle"];
   $maiden = $person["Maiden"];
   $last = $person["Last"];
   
   if ($maiden == "")
   {
      $surname = $last;
	  $marriedName = "";
   }
   else
   {
      $surname = $maiden;
	  $marriedName = $last;
   }
   
   $name = $first . " " . $middle . " " . $maiden . " " . $last;
   
   $monBorn = $person["MonBorn"];
   $dayBorn = $person["DayBorn"];
   $yrBorn = $person["YrBorn"];

   print "<br/>0 @I" . $seq . "@ INDI";
   
   print "<br/>1 NAME " . $first . " " . $middle . " /" . $surname . "/";
   print "<br/>2 GIVN " . $first;
   print "<br/>2 _MIDN " . $middle;
   print "<br/>2 SURN " . $surname;
   print "<br/>2 _MARN " . $marriedName;
   
   print "<br/>1 SEX " . $person["Sex"];
   print "<br/>1 BIRT";
   print "<br/>2 DATE " . $dayBorn . " " . $months[$monBorn] . " " . $yrBorn;
   print "<br/>2 PLAC " . $person["WhereBorn"];
}
?>
</body>
</html>