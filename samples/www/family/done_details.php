<html>

<body bgcolor="#f0f0f0">

<?php
   # Connect to database
   include("../db.php");

   # Find person name
   $key = $_REQUEST["Key"];
   if (!isset($key))
   {
      print "<p>No person specified.</p>";
   }
   else
   {
      $op = $_REQUEST["Op"];
      $errs = 0;

      if ($op == "Edit" or $op == "Add")
      {
         $values = "Clan = '" . $_REQUEST["FamilyKey"] . "'";
         $values .= ", CellPhone = '" . $_REQUEST["CellPhone"] . "'";
         $values .= ", HomePhone = '" . $_REQUEST["HomePhone"] . "'";
         $values .= ", WorkPhone = '" . $_REQUEST["WorkPhone"] . "'";
         $values .= ", OtherPhone = '" . $_REQUEST["OtherPhone"] . "'";
         $values .= ", HomeEmail = '" . $_REQUEST["HomeEmail"] . "'";
         $values .= ", WorkEmail = '" . $_REQUEST["WorkEmail"] . "'";
         $values .= ", OtherEmail = '" . $_REQUEST["OtherEmail"] . "'";
         $values .= ", HomeAddress1 = '" . $_REQUEST["HomeAddress1"] . "'";
         $values .= ", HomeAddress2 = '" . $_REQUEST["HomeAddress2"] . "'";
         $values .= ", HomeAddress3 = '" . $_REQUEST["HomeAddress3"] . "'";
         $values .= ", WorkOrSchool = '" . $_REQUEST["WorkOrSchool"] . "'";
         $values .= ", WorkAddress1 = '" . $_REQUEST["WorkAddress1"] . "'";
         $values .= ", WorkAddress2 = '" . $_REQUEST["WorkAddress2"] . "'";
         $values .= ", WorkAddress3 = '" . $_REQUEST["WorkAddress3"] . "'";
      }

      if ($op == "Edit")
      {
         $query = "UPDATE Details SET " . $values . " WHERE KeyName='" . $key . "' LIMIT 1";
         print "  <p>Updating " . htmlentities($key) . "</p>\n";
      }
      else if ($op == "Add")
      {
         $query = "INSERT INTO Details SET KeyName='" . $key . "', " . $values;
         print "  <p>Inserting " . htmlentities($key) . "</p>\n";
      }
      else
      {
         $errs++;
         # Not sure what to do ... no valid input. Ignore it I guess.
      }

      if ($errs == 0)
      {
         print "<p>SQL: " . htmlentities($query) . "</p>\n";
         mysql_query($query); # or die('Query failed: ' . mysql_error());
         $stat = db_errno();
         if ($stat == 0)
         {
            print "<p>Result: Success";
            print "  <p><input type=button onclick=\"self.close();\" value=\"Ok\">\n";
         }
         else
         {
            print "<p>Error: " . $stat . ": " . db_error();
            print "<br>Please go back and fix it.";
         }
      }
   }

   db_close();
?>

</body>
</html>
