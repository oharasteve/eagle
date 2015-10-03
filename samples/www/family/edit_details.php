<html>

<head>
<title>Phone Number and Address</title>
</head>

<body bgcolor="#f0f0f0" onload="self.focus();">

<?php
   # Connect to database
   include("../db.php");

   # Find person name
   $key = $_REQUEST["Key"];
   if (!isset($key))
   {
      print "<h1>Phone Number and Address</h1>";
      print "<p>No person specified. Please use ?Key=name on the web address</p>";
   }
   else
   {
      print "<h1>Phone Number and Address for " . htmlentities($key) . "</h1>\n";

      $op = $_REQUEST["Op"];
      $rdonly = "";
      $max20 = "";
      $max40 = "";
      if ($op != "Edit" and $op != "Add")
      {
         $op = "View";
         $rdonly = " readonly";
      }
      else
      {
         $max20 = " maxlength=20";
         $max40 = " maxlength=40";
      }

      if ($op != "Add")
      {
         $query = "SELECT * FROM Details, Families WHERE Details.Clan=Families.FamilyKey AND KeyName='" . $key . "'";
         $people = mysql_query($query);
         $person1 = mysql_fetch_array($people);  # Just grab first person
      }
      else
      {
         $person1 = array("");
      }

      # Look up name in the main table
      $query2 = "SELECT * from People where KeyName='" . $key . "'";
      $people2 = mysql_query($query2);
      $person2 = mysql_fetch_array($people2);

      print "<hr>";

#NAME
      print "<p>Name: <a href=\"../tree/view.php?Key=" . $key . "\">" . pretty($person2) . "</a>\n";

#BORN
      $monBorn = $person2["MonBorn"];
      $dayBorn = $person2["DayBorn"];
      $yrBorn = $person2["YrBorn"];
      print "<br>Born: " . niceDate($monBorn, $dayBorn, $yrBorn) . "\n";

#MOTHER
      $motherKey = $person2["MotherKey"];
      if ($motherKey != "")
      {
         $Mquery = "SELECT * FROM People WHERE KeyName='" . $motherKey . "'";
      	 $Mresults = mysql_query($Mquery);
         $mother = mysql_fetch_array($Mresults);
         if ($mother)
         {
            ref("Mother", $op, $motherKey, $mother);
         }
      }

#FATHER
      $fatherKey = $person2["FatherKey"];
      if ($fatherKey != "")
      {
   	     $Fquery = "SELECT * FROM People WHERE KeyName='" . $fatherKey . "'";
   	     $Fresults = mysql_query($Fquery);
   	     $father = mysql_fetch_array($Fresults);
   	     if ($father)
   	     {
            ref("Father", $op, $fatherKey, $father);
   	     }
      }

#SPOUSE, ANNIVERSARY
      $sex = $person2["Sex"];
      if ($sex == "M")
      {
         $spousetype = "Wife";
         $mytype = "Husband";
      }
      else
      {
         $spousetype = "Husband";
         $mytype = "Wife";
      }

      $query1 = "SELECT * FROM Marriages WHERE " . $mytype . "Key='" . $key . "'";
      $results1 = mysql_query($query1);
      $marriage = mysql_fetch_array($results1);
      if ($marriage != FALSE)
      {
         if ($marriage["Divorced"] != "Divorced")
         {
            $query2 = "SELECT * FROM People WHERE KeyName='" . $marriage[$spousetype . "Key"] . "'";
        	$results2 = mysql_query($query2);
            $spouse = mysql_fetch_array($results2);

            #  ex. Husband: John Doe (1901-2001)
            ref($spousetype, $op, $spouse["KeyName"], $spouse);

            $yrWed = $marriage["YrWed"];
            if ($yrWed != "0")
            {
               $dayWed = $marriage["DayWed"];
               $monWed = $marriage["MonWed"];
               print "<br>Married: " . nicedate($monWed, $dayWed, $yrWed) . "\n";
            }
         }
      }

#CHILDREN
      if ($sex == "M")
      {
         $parenttype = "Father";
      }
      else
      {
         $parenttype = "Mother";
      }

      $Cquery = "SELECT * FROM People WHERE " . $parenttype . "Key='" . $key . "' ORDER BY YrBorn, MonBorn";
      $Cresults = mysql_query($Cquery);
      while ($kid = mysql_fetch_array($Cresults))
      {
      	 if ($kid["Sex"] == "M")
      	 {
      	    $child = "Son";
      	 }
      	 else
      	 {
      	    $child = "Daughter";
      	 }
         ref($child, $op, $kid["KeyName"], $kid);
      }

#FORM
      print "<form action=\"done_details.php\" method=\"post\">\n";
      print "<input type=\"hidden\" name=\"Key\" value=\"" . $key . "\">";

      # Name and Family
      print "    <p><table border=0 cellpadding=0 cellspacing=0>\n";
      print "    <tr valign=top><td align=center>Family:&nbsp;\n";

      if ($op == "View")
      {
         $clan = $person1["FamilyName"];
         print "  <td><input type=\"text\"" . $rdonly . " value=\"" . $clan . "\">\n";
      }
      else
      {
         print "    <td><select name=\"FamilyKey\">\n";
         $query3 = "SELECT * FROM Families ORDER BY FamilyKey";
         $families = mysql_query($query3);
         $clan = $person1["Clan"];
         while ($fam = mysql_fetch_array($families))
         {
            $fkey = $fam["FamilyKey"];
            $fname = $fam["FamilyName"];
            $fselected = ($clan == $fkey ? " selected" : "");
            print "       <option value=\"" . $fkey . "\"" . $fselected . ">" . $fname . "</option>\n";
         }
         print "  </select>\n";
      }

      # Phone numbers
      print "  <p><tr valign=top><td align=center>Phone Numbers:&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max20 . " name=CellPhone value=\"" .
			htmlentities($person1["CellPhone"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max20 . " name=HomePhone value=\"" .
			htmlentities($person1["HomePhone"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max20 . " name=WorkPhone value=\"" .
			htmlentities($person1["WorkPhone"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max20 . " name=OtherPhone value=\"" .
			htmlentities($person1["OtherPhone"]) . "\">&nbsp;&nbsp;\n";
      print "  <tr valign=top><td>&nbsp;\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Cell</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Home</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Work</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Other</font></i>\n";
      print "  <tr><td>&nbsp;</td></tr>\n";

      # Email addresses
      print "  <p><tr valign=top><td align=center>Email Addresses:&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=HomeEmail value=\"" .
			htmlentities($person1["HomeEmail"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=WorkEmail value=\"" .
			htmlentities($person1["WorkEmail"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=OtherEmail value=\"" .
			htmlentities($person1["OtherEmail"]) . "\">&nbsp;&nbsp;\n";
      print "  <tr valign=top><td>&nbsp;\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Home</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Work</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Other</font></i>\n";
      print "  <tr><td>&nbsp;</td></tr>\n";

      # Home Address
      print "  <p><tr valign=top><td align=center>Home Address:&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=HomeAddress1 value=\"" .
			htmlentities($person1["HomeAddress1"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=HomeAddress2 value=\"" .
			htmlentities($person1["HomeAddress2"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=HomeAddress3 value=\"" .
			htmlentities($person1["HomeAddress3"]) . "\">&nbsp;&nbsp;\n";
      print "  <tr valign=top><td>&nbsp;\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 1</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 2</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 3</font></i>\n";
      print "  <tr><td>&nbsp;</td></tr>\n";

      # Work Address
      print "  <p><tr valign=top><td align=center>Work Address:&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=WorkOrSchool value=\"" .
			htmlentities($person1["WorkOrSchool"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=WorkAddress1 value=\"" .
			htmlentities($person1["WorkAddress1"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=WorkAddress2 value=\"" .
			htmlentities($person1["WorkAddress2"]) . "\">&nbsp;&nbsp;\n";
      print "    <td><input type=\"text\"" . $rdonly . $max40 . " name=WorkAddress3 value=\"" .
			htmlentities($person1["WorkAddress3"]) . "\">&nbsp;&nbsp;\n";
      print "  <tr valign=top><td>&nbsp;\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Company or school</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 1</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 2</font></i>\n";
      print "    <td valign=top><i><font size=-2>&nbsp;&nbsp;Line 3</font></i>\n";
      print "  <tr><td>&nbsp;</td></tr>\n";

      print "  </table>\n";

      if ($op == "Edit")
      {
         print "  <input type=button onclick=\"self.close();\" value=\"Cancel\">&nbsp;&nbsp;\n";
         print "  <button type=submit>Update</button>\n";
      }
      else if ($op == "Add")
      {
         print "  <input type=button onclick=\"self.close();\" value=\"Cancel\">&nbsp;&nbsp;\n";
         print "  <button type=submit>Insert</button>\n";
      }
      else ## ($op == "View")
      {
         print "  <input type=button onclick=\"self.close();\" value=\"Done\">\n";
      }

      print "  <input type=hidden name=Op value=\"" . $op . "\">\n";
      print "</form>\n";
   }

   db_close();
   
function ref($lbl, $op, $key, $person)
{
   $query4 = "SELECT * FROM Details WHERE KeyName ='" . $key . "'";
   $results4 = mysql_query($query4);
   if (mysql_fetch_array($results4))
   {
      print "<br>" . htmlentities($lbl) . ": <a href=\"edit_details.php?Op=" . $op . "&Key=" . $key . "\">" .
			pretty($person) . "</a>\n";
   }
   else
   {
      print "<br>" . htmlentities($lbl) . ": " . pretty($person) . "\n";
   }
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

   return htmlentities(rtrim($name));
}

function nicedate($m, $d, $y)
{
   $monthArray = array("January", "February", "March", "April", "May", "June", "July", "August",
                  "September", "October", "November", "December");

   if ($m != "" and $m != "0")
   {
      $mon = $monthArray[$m-1];
      if ($d != "" and $d != "0")
      {
         return $mon . " " . $d . ", " . $y;
      }
      return $mon . ", " . $y;
   }

   return $y;
}
?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
