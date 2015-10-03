<html>

<body bgcolor="#f0f0f0">

<?php
   # Connect to database
   include("../db.php");

   $adding = FALSE;
   $op = $_REQUEST["Op"];
   if ($op == "Add")
   {
      $adding = TRUE;
   }
   else
   {
      $op = "Edit";
   }

   # Find person name
   $key = $_REQUEST["Key"];
   if (!isset($key))
   {
      print "<p>No person specified.</p>";
   }
   else
   {
      $errs = 0;

      $YrBorn = $_REQUEST["YrBorn"];

      $mother = $_REQUEST["MotherKey"];
      if ($mother and $mother != "")
      {
         $query2 = "SELECT Sex, YrBorn FROM People WHERE KeyName = '" . $mother . "'";
         $result2 = mysql_query($query2);
         $mom = mysql_fetch_array($result2);
         if (!$mom)
         {
            print "<p><b>No such mother key: " . htmlentities($mother) . ".</b> Please go back and fix it\n";
            $errs++;
         }
         else if ($mom["Sex"] == "M")
         {
            print "<p><b>Mother must be female: " . htmlentities($mother) . ".</b> Please go back and fix it\n";
            $errs++;
         }
         else
         {
            $momborn = $mom["YrBorn"];
            if ($momborn > $YrBorn - 10)
            {
               print "<p><b>Mother is too young: " . htmlentities($mother) . " (b. " . $momborn .
                       ").</b> Please go back and fix it\n";
               $errs++;
            }
            else if ($momborn < $YrBorn - 60)
            {
               print "<p><b>Mother is too old: " . htmlentities($mother) . " (b. " . $momborn .
                       ").</b> Please go back and fix it\n";
               $errs++;
            }
         }
      }

      $father = $_REQUEST["FatherKey"];
      if ($father and $father != "")
      {
         $query3 = "SELECT Sex, YrBorn FROM People WHERE KeyName = '" . $father . "'";
         $result3 = mysql_query($query3);
         $dad = mysql_fetch_array($result3);
         if (!$dad)
         {
            print "<p><b>No such father key: " . htmlentities($father) . ".</b> Please go back and fix it\n";
            $errs++;
         }
         else if ($dad["Sex"] != "M")
         {
            print "<p><b>Father must be male: " . htmlentities($father) . ".</b> Please go back and fix it\n";
            $errs++;
         }
         else
         {
            $dadborn = $dad["YrBorn"];
            if ($dadborn > $YrBorn - 10)
            {
               print "<p><b>Father is too young: " . htmlentities($father) . " (b. " . $dadborn .
                       ").</b> Please go back and fix it\n";
               $errs++;
            }
            else if ($dadborn < $YrBorn - 60)
            {
               print "<p><b>Father is too old: " . htmlentities($father) . " (b. " . $dadborn .
                       ").</b> Please go back and fix it\n";
               $errs++;
            }
         }
      }

      if (!$YrBorn or $YrBorn == "" or $YrBorn < -500)
      {
         print "<p><b>Year born is required. Guess if necessary.</b>\n";
            print "Please go back and fix it\n";
         $errs++;
      }

      $YrDied = $_REQUEST["YrDied"];
      if ($YrDied != 0 and $YrBorn > $YrDied)
      {
         print "<p><b>Can't die (" . $YrDied . ") before being born (" . $YrBorn .
               ").</b> Please go back and fix it\n";
         $errs++;
      }

      if ($errs == 0)
      {
         $values = "First = '" . $_REQUEST["First"] . "'";
         $values .= ", Middle = '" . $_REQUEST["Middle"] . "'";
         $values .= ", Maiden = '" . $_REQUEST["Maiden"] . "'";
         $values .= ", Last = '" . $_REQUEST["Last"] . "'";
         $values .= ", Jr = '" . $_REQUEST["Jr"] . "'";
         $values .= ", Title = '" . $_REQUEST["Title"] . "'";
         $values .= ", Nickname = '" . $_REQUEST["Nickname"] . "'";
         $values .= ", Sex = '" . $_REQUEST["Sex"] . "'";
         $values .= ", MonBorn= '" . $_REQUEST["MonBorn"] . "'";
         $values .= ", DayBorn = '" . $_REQUEST["DayBorn"] . "'";
         $values .= ", YrBorn = '" . $YrBorn . "'";
         $values .= ", WhereBorn = '" . $_REQUEST["WhereBorn"] . "'";
         $values .= ", Alive = '" . $_REQUEST["Alive"] . "'";
         $values .= ", MonDied = '" . $_REQUEST["MonDied"] . "'";
         $values .= ", DayDied = '" . $_REQUEST["DayDied"] . "'";
         $values .= ", YrDied = '" . $YrDied . "'";
         $values .= ", WhereBuried = '" . $_REQUEST["WhereBuried"] . "'";
         $values .= ", MotherKey= '" . $mother . "'";
         $values .= ", FatherKey = '" . $father . "'";
         $values .= ", Note = '" . $_REQUEST["Note"] . "'";
         $values .= ", WikiTree = '" . $_REQUEST["WikiTree"] . "'";
         $values .= ", FindAGrave = '" . $_REQUEST["FindAGrave"] . "'";

         if ($adding)
         {
            $query = "INSERT People SET " . $values . ", KeyName='" . $key . "'";
               print "  <p>Inserting " . htmlentities($key) . "</p>\n";
         }
         else
         {
            $query = "UPDATE People SET " . $values . " WHERE KeyName='" . $key . "' LIMIT 1";
               print "  <p>Updating " . htmlentities($key) . "</p>\n";
         }

         print "<p>SQL: " . htmlentities($query) . "</p>\n";
         mysql_query($query); # or die('Query failed: ' . mysql_error());
         $stat = mysql_errno($dblink);
         if ($stat == 0)
         {
            print "<p>Result: Success";
            print "<p><input type=button onclick=\"self.close();\" value=\"Ok\">\n";
         }
         else
         {
            print "<p>Error: " . $stat . ": " . mysql_error($dblink);
            print "<br>Please go back and fix it";
         }
      }
   }

   db_close();
?>

</body>
</html>
