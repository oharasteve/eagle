<html>

<head>
<title>Family Phone Numbers and Addresses</title>
</head>

<body bgcolor="#f0f0f0">
<h1>Family Phone Numbers and Addresses</h1>

<p><font size=-1><i><a href="../index.html">Back to Main Page</a></i></font>

<?php
   # Connect to database
   include("../db.php");

   # Select clan, maybe
   $clan = $_REQUEST["Family"];
   if (!isset($clan))
   {
      $clan = "All";
   }

   $query = "SELECT * FROM Families ORDER BY FamilyKey";
   $families = mysql_query($query);

   print "<p><form action=\"index.php\" method=\"get\">\n";
   print "  Family: <select name=\"Family\">\n";
   print "    <option>All</option>\n";
   while ($fam = mysql_fetch_array($families))
   {
      $fkey = $fam["FamilyKey"];
      $fname = $fam["FamilyName"];
      $fselected = ($clan == $fkey ? " selected" : "");
      print "    <option value=\"" . $fkey . "\"" . $fselected . ">" . $fname . "</option>\n";
   }
   print "  </select>\n";
   print "  <input type=\"submit\" value=\"Refresh\">\n";
   print "</form>\n";

   print "<table border=1 cellpadding=2>\n";
   print "<thead>\n";
   print "  <td><i>&nbsp;Name&nbsp;\n";
   print "  <td><i>&nbsp;Family&nbsp;\n";
   print "  <td><i>&nbsp;Cell phone&nbsp;\n";
   print "  <td><i>&nbsp;Home phone&nbsp;\n";
   print "  <td><i>&nbsp;B'day&nbsp;\n";
   print "  <td><i>&nbsp;Anniv.&nbsp;\n";
   print "  <td><i>&nbsp;Actions&nbsp;\n";
   print "</thead>\n";

   $where = "WHERE Details.Clan=Families.FamilyKey";
   if ($clan != "All")
   {
      $where = $where . " AND Details.Clan='" . $clan . "'";
   }
   $query = "SELECT * FROM Details, Families " . $where . " ORDER BY KeyName";
   $people = mysql_query($query);
   while ($person = mysql_fetch_array($people))
   {
      $key = $person["KeyName"];

      # Put apostrophe back in OHara
      $fkey = $person["Clan"];

      # Look up name in the main table
      $query2 = "SELECT * from People where KeyName='" . $key . "'";
      $people2 = mysql_query($query2);
      $person2 = mysql_fetch_array($people2);

      # Does this once per person in the selected clan
      $nick = $person2["Nickname"];
      if ($nick != null and $nick != "")
      {
         $nick = " (" . $nick . ")";
      }

      #birthday
      $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
      $bday = $person2["DayBorn"];
      if ($bday and $bday > 0)
      {
         $bday = $months[$person2["MonBorn"]-1] . " " . $bday;
      }
      else
      {
         $bday = "";
      }

      #anniversary
      if ($person2["Sex"] == "M")
      {
         $spouse = "Husband";
      }
      else
      {
         $spouse = "Wife";
      }
      $query3 = "SELECT * from Marriages where " . $spouse . "Key='" . $key . "'";
      $people3 = mysql_query($query3);
      $person3 = mysql_fetch_array($people3);
      $anniv = $person3["DayWed"];
      if ($anniv and $anniv > 0 and $person3["Divorced"] != "Divorced")
      {
         $anniv = $months[$person3["MonWed"]-1] . " " . $anniv;
      }
      else
      {
         $anniv = "";
      }

      print "<tr>\n";
      print "<td>&nbsp;" . htmlentities($person2["First"]) . " " . htmlentities($person2["Last"]) . htmlentities($nick) . "&nbsp;\n";
      print "<td>&nbsp;" . htmlentities($person["FamilyName"]) . "&nbsp;\n";
      print "<td><tt>&nbsp;" . htmlentities($person["CellPhone"]) . "&nbsp;</tt>\n";
      print "<td><tt>&nbsp;" . htmlentities($person["HomePhone"]) . "&nbsp;</tt>\n";
      print "<td><tt>&nbsp;" . $bday . "&nbsp;</tt>\n";
      print "<td><tt>&nbsp;" . $anniv . "&nbsp;</tt>\n";
      print "<td>&nbsp;<font size=-1>\n";
      print "  <a href=\"edit_details.php?Op=View&Key=" . $key . "\" target=\"FamilyPhone\">View</a>&nbsp;\n";
      print "  <a href=\"edit_details.php?Op=Edit&Key=" . $key . "\" target=\"FamilyPhone\">Edit</a>&nbsp;\n";
      # print "  <a href=\"edit_details.php?Op=Dup&Key=" . $key . "\" target=\"FamilyPhone\">Dup</a>&nbsp;\n";
      # print "  <a href=\"edit_details.php?Op=Delete&Key=" . $key . "\" target=\"FamilyPhone\">Delete</a>&nbsp;\n";
      print "  </font>\n";
   }

   print "</table>\n";

   print "<p>Please send me an email to add, remove or fix names. --Steve\n";

   print "<p><form action=bday.php method=post>\n";
   print "  <input type=hidden name=Clan value=\"" . $clan . "\">\n";
   print "  <input type=submit value=\"Birthdays coming up\">\n";
   print "</form>\n";

   print "<p><form action=txt.php method=post>\n";
   print "  <input type=hidden name=Clan value=\"" . $clan . "\">\n";
   print "  <input type=submit value=\"Download to a text file\">\n";
   print "</form>\n";

   print "<p><form action=csv.php method=post>\n";
   print "  <input type=hidden name=Clan value=\"" . $clan . "\">\n";
   print "  <input type=submit value=\"Download to an Excel .csv file\">\n";
   print "</form>\n";

   print "<p><form action=outlook.php method=post>\n";
   print "  <input type=hidden name=Clan value=\"" . $clan . "\">\n";
   print "  <input type=submit value=\"Download to an Outlook .csv file\">\n";
   print "</form>\n";

   db_close();
?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
