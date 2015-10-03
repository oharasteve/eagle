<html>

<head>
<title>Check Family Tree Links</title>
</head>

<body bgcolor="#f0f0f0">
<h1>Check Family Tree Links</h1>

<?php
   include("../db.php");

   check("People", "MotherKey", "People", "KeyName");
   check("People", "FatherKey", "People", "KeyName");
   check2("People", "Sex", array("F", "M"), "KeyName");
   check("Marriages", "HusbandKey", "People", "KeyName");
   check("Marriages", "WifeKey", "People", "KeyName");
   check("Details", "KeyName", "People", "KeyName");
   check("Details", "Clan", "Families", "FamilyKey");
   check("PeoplePhotos", "Photo", "Photos", "Photo");
   check("PeoplePhotos", "KeyName", "People", "KeyName");
   check("WebLinks", "KeyName", "People", "KeyName");
   check("Faces", "Photo", "Photos", "Photo");
   check("Faces", "Person", "People", "KeyName");

   db_close();

function check($tbl1, $col1, $tbl2, $col2)
{
   print "<p><b>Checking " . $tbl1 . " column " . $col1 . "</b>\n";
   $query1 = "SELECT * FROM " . $tbl1;
   $response1 = mysql_query($query1);
   $alreadyComplained = array();
   while ($row = mysql_fetch_array($response1))
   {
      $val = $row[$col1];
      if ($val and $val != "")
      {
         $query2 = "SELECT * FROM " . $tbl2 . " WHERE " . $col2 . "='" . $val . "'";
         $response2 = mysql_query($query2);

         if (!mysql_fetch_array($response2))
         {
		    if ($alreadyComplained[$val] != true)
			{
               print "<br>ERROR: " . $tbl1 . " has a bad " . $col1 . " value of \"" . $val . "\"\n";
			   $alreadyComplained[$val] = true;
			}
         }
      }
   }
}

function check2($tbl1, $col1, $vals, $refer)
{
   print "<p><b>Checking " . $tbl1 . " column " . $col1 . "</b>\n";
   $query3 = "SELECT * FROM " . $tbl1;
   $response3 = mysql_query($query3);
   while ($row = mysql_fetch_array($response3))
   {
      $val = $row[$col1];
      if (!in_array($val, $vals))
      {
         print "<br>ERROR: " . $tbl1 . " has a bad " . $col1 . " value of \"" . $val . "\"\n";
         print " reference=" . $row[$refer] . "\n";
      }
   }
}

?>

</body>
</html>
