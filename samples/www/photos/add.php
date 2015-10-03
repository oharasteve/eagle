<html>
<body>
<?php
   # Connect to database
   include("../db.php");

   $srch = trim($_REQUEST["SrchFor"]);

   $pic = $_REQUEST["Pic"];
   $query = "SELECT * FROM Photos WHERE Photo='" . $pic . "'";
   $results = mysql_query($query);
   $photo = mysql_fetch_array($results);
   $dir = $photo["Directory"];
   $suffix = $photo["Suffix"];
   $fname = $photo["Photo"] . "_thumb" . $suffix;

   if ($dir and $dir != "")
   {
      print "<img src=\"" . $dir . "/thumbs/" . $fname . "\"" . htmlentities($pic) . ">\n";
   }

   print "<form action=\"pic.php\" method=\"post\">\n";
   print "  <input type=hidden name=Update value=\"addem\">\n";
   print "  <input type=hidden name=Pic value=\"" . htmlentities($pic) . "\">\n";

   print "  <table cellpadding=6 border=4><tr>";
      chk("StevenOHara", "Steve");
      chk("ArthurOHara", "Arthur");
      chk("JeanneOHara", "Jeanne");
      print "<td>&nbsp;";
      chk("RichardJohnson", "Richard");
      chk("GiselaJohnson", "Gisela");
   print "<tr>";
      chk("DaisyOHara", "Daisy");
      chk("HarryOHaraIII", "Harry");
      chk("AnnikaOHara", "Annika");
      chk("EmmaOHara", "Emma");
      chk("JasminGreer", "Jasmin");
      chk("CarlGreer", "Carl");
   print "<tr>";
      chk("ShaneOHara", "Shane");
      chk("CharlesOHara", "Charles");
      chk("MatthewOHara", "Matt");
      chk("CarolynOHara", "Carolyn");
      chk("ChristopherGreer", "Chris");
      print "<td>&nbsp;";
   print "<tr>";
      chk("RobinOHara", "Robin");
      chk("PatriciaOHara", "Patty");
      chk("XavierCassoOHara", "Xavi");
      chk("RosieCassoOHara", "Rose");
      chk("HeatherGreer", "Heather");
      chk("LoriJohnson", "Lori");
   print "<tr>";
      chk("LanceOHara", "Lance");
      chk("DavidOHara", "David");
      chk("LenaOHara", "Lena");
      chk("AllyOHara", "Ally");
      chk("JonathanGreer", "Jon");
      chk("DylanThomas", "Dylan");
   print "  </table><br>";

   if ($srch == null)
   {
      print "<p>Search Results: <i>n/a</i>";
   }
   else
   {
      $query = "SELECT * FROM People WHERE KeyName LIKE '%" . $srch . "%'" .
         " OR First LIKE '%" . $srch . "%'" .
         " OR Middle LIKE '%" . $srch . "%'" .
         " OR Maiden LIKE '%" . $srch . "%'" .
         " OR Last LIKE '%" . $srch . "%'" .
         " OR Nickname LIKE '%" . $srch . "%'" .
         " ORDER BY First";
      $results = mysql_query($query);
      $cnt = 0;
      print "<p>Search Results for: " . htmlentities($srch);
      while ($person = mysql_fetch_array($results))
      {
         print "<br>&nbsp;&nbsp;&nbsp;";
         chk($person["KeyName"], pretty($person));
         $cnt++;
      }
      if ($cnt == 0)
      {
         print "<i>No matches found</i>";
      }
   }

   print "  <br><br><input type=button onclick=\"parent.location='pic.php?Pic=" . htmlentities($pic) . "'\" value=\"Cancel\">&nbsp;&nbsp;\n";
   print "  <button type=submit>Update People in Photo</button>\n";
   print "</form>\n";

   print "<hr><form action=\"add.php\" method=\"post\">\n";
   print "  <input type=hidden name=Pic value=\"" . htmlentities($pic) . "\">\n";
   print "  Search for a name:&nbsp;\n";
   print "  <input type=text name=SrchFor>\n";
   print "  &nbsp;&nbsp;<button type=submit>Search</button>\n";
   print "</form>\n";

   db_close();

function chk($key, $val)
{
   global $pic;

   # Make sure not already there! THIS IS REALLY SLOW .... Should query once and save results
   $query = "SELECT * FROM PeoplePhotos WHERE Photo='" . htmlentities($pic) . "' AND KeyName='" . htmlentities($key) . "'";
   $results = mysql_query($query);
   if (mysql_fetch_array($results) != null)
   {
      $disabled = "disabled=disabled checked=checked";
   }
   else
   {
      $disabled = "";
   }

   print "<td><input type=checkbox " . $disabled . "name=CHK_" . htmlentities($key) . ">&nbsp;" . $val . "\n";
}

function spacer($piece)
{
   if ($piece == "")
   {
      return "";
   }
   return $piece . " ";
}

function pretty($person)
{
   $name = spacer($person["Title"]) .
   	  spacer($person["First"]) .
   	  spacer($person["Middle"]) .
   	  spacer($person["Maiden"]) .
   	  spacer($person["Last"]) .
   	  spacer($person["Jr"]);

   $nick = $person["Nickname"];
   if ($nick != "")
   {
      $name = $name . "(" . $nick . ") ";
   }

   return rtrim($name);
}
?>

</body>
</html>
