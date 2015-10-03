<html>

<body bgcolor="#f0f0f0">

<?php
   # Connect to database
   include("../db.php");

   # Find person name
   $husbandKey = $_REQUEST["HusbandKey"];
   $wifeKey = $_REQUEST["WifeKey"];
   if (!isset($husbandKey) || !isset($wifeKey))
   {
      print "<p>Husband and Wife keys are both required.</p>";
   }
   else
   {
      $errs = 0;

      $YrBorn = $_REQUEST["YrBorn"];

      $query2 = "SELECT Sex, YrBorn FROM People WHERE KeyName = '" . $wifeKey . "'";
      $result2 = mysql_query($query2);
      $wife = mysql_fetch_array($result2);
      if (!$wife)
      {
         print "<p><b>No such person key: " . htmlentities($wifeKey) . ".</b> Please go back and fix it\n";
         $errs++;
      }
      else if ($wife["Sex"] == "M")
      {
         print "<p><b>Wife must be female: " . htmlentities($wifeKey) . ".</b> Please go back and fix it\n";
         $errs++;
      }

      $query3 = "SELECT Sex, YrBorn FROM People WHERE KeyName = '" . $husbandKey . "'";
      $result3 = mysql_query($query3);
      $husband = mysql_fetch_array($result3);
      if (!$husband)
      {
         print "<p><b>No such person key: " . htmlentities($husbandKey) . ".</b> Please go back and fix it\n";
         $errs++;
      }
      else if ($husband["Sex"] == "F")
      {
         print "<p><b>Husband must be male: " . htmlentities($husbandKey) . ".</b> Please go back and fix it\n";
         $errs++;
      }

      if ($errs == 0)
      {
		 $mon = $_REQUEST["monMarried"];
		 $day = $_REQUEST["dayMarried"];
		 $yr = $_REQUEST["yearMarried"];

		 $values = "HusbandKey='" . rtrim($husbandKey) . "'";
		 $values .= ", WifeKey='" . rtrim($wifeKey) . "'";
		 if ($mon != '0') $values .= ", MonWed=" . $mon;
		 if ($day != '0') $values .= ", DayWed=" . $day;
		 if ($yr != '') $values .= ", YrWed=" . $yr;
		 $values .= ", `Where`='" . $_REQUEST["whereMarried"] . "'";
		 $values .= ", Divorced='" . $_REQUEST["stillMarried"] . "'";

         $query = "INSERT Marriages SET " . $values;
   	     print "  <p>Inserting " . htmlentities($key) . "</p>\n";

         print "<p>SQL: " . htmlentities($query) . "</p>\n";
         mysql_query($query);
         $stat = db_errno();
         if ($stat == 0)
         {
            print "<p>Result: Success";
            print "<p><input type=button onclick=\"self.close();\" value=\"Ok\">\n";
         }
         else
         {
            print "<p>Error: " . $stat . ": " . db_error();
            print "<br>Please go back and fix.";
         }
      }
   }

   db_close();
?>

</body>
</html>
