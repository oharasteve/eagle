<html>

<head>
<title>Upcoming Birthdays and Anniversaries</title>
</head>

<body bgcolor="#f0f0f0">

<h2>Upcoming Birthdays and Anniversaries</h2>

<p><font size=-1><i><a href="index.php">Back to Family Page</a></i></font></p>

<hr>

<table border=0 cellpadding=10>
  <tr>
    <td align=center><font size=+2><b>Birthdays</b>
    <td align=center><font size=+2><b>Anniversaries</b>
  <tr valign=top>

<?php
   # Connect to database
   include("../db.php");

   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

   # Select clan, maybe
   # Broken because 'People' table does not include Clan!
   # $clan = $_REQUEST["Clan"];
   # if (!isset($clan))
   # {
      $clan = "All";
   # }

   # Select month, maybe
   $currMon = $_REQUEST["Month"];
   if (isset($currMon))
   {
      $currDay = 1;
      $nextMon = $currMon;
   }
   else
   {
      $now = getdate();
      $currMon = $now["mon"];	# 1 - 12
      $currDay = $now["mday"]; # 1 - 31
      # $currYr = $now["year"];  # e.g., 2004

      $nextMon = $currMon + 1;
      if ($nextMon > 12)
      {
         $nextMon = 1;
      }
   }

   print "<td><table border=1 cellpadding=2>\n";
   print "<thead>\n";
   print "  <td><i>&nbsp;Birthday&nbsp;</i>\n";
   print "  <td><i>&nbsp;Name&nbsp;\n";
   print "</thead>\n";
   $cnt = dobd($clan, $currMon, $currDay);
   if ($currMon != $nextMon)
   {
      $cnt += dobd($clan, $nextMon, 1);
   }
   if ($cnt == 0)
   {
      print "<tr>\n";
      print "<td colspan=2><center><i>(none found)</i></center>\n";
   }
   print "</table>\n";

   print "<td><table border=1 cellpadding=2>\n";
   print "<thead>\n";
   print "  <td><i>&nbsp;Anniversary&nbsp;</i>\n";
   print "  <td><i>&nbsp;Husband&nbsp;\n";
   print "  <td><i>&nbsp;Wife&nbsp;\n";
   print "</thead>\n";
   $cnt = doann($clan, $currMon, $currDay);
   if ($currMon != $nextMon)
   {
      $cnt += doann($clan, $nextMon, 1);
   }
   if ($cnt == 0)
   {
      print "<tr>\n";
      print "<td colspan=3><center><i>(none found)</i></center>\n";
   }
   print "</table>\n";

   db_close();

function dobd($clan, $mon, $sday)
{
   $where = "WHERE MonBorn='" . $mon . "' AND Alive='Y'";
   if ($clan != "All")
   {
      $where = $where . " AND Clan='" . $clan . "'";
   }
   $query = "SELECT * FROM People " . $where . " ORDER BY DayBorn";
   $people = mysql_query($query);

   $cnt = 0;
   while ($person = mysql_fetch_array($people))
   {
      $day = $person["DayBorn"];
      if ($day >= $sday)
      {
         print "<tr>\n";
         print "<td>&nbsp;" . prt($mon, $day, $person["YrBorn"]) . "&nbsp;\n";
         print "<td>&nbsp;" . pretty($person) . "&nbsp;\n";
         $cnt++;
      }
   }
   return $cnt;
}

function doann($clan, $mon, $sday)
{
   $query = "SELECT * FROM Marriages WHERE MonWed='" . $mon . "' ORDER BY DayWed";
   $marriages = mysql_query($query);

   $cnt = 0;
   while ($marriage = mysql_fetch_array($marriages))
   {
      $day = $marriage["DayWed"];
      if ($day >= $sday and $marriage["Divorced"] != "Divorced")
      {
         $where2 = "WHERE KeyName='" . $marriage["HusbandKey"] . "' AND Alive='Y'";
         if ($clan != "All")
         {
            $where2 = $where2 . " AND Clan='" . $clan . "'";
         }
         $query2 = "SELECT * FROM People " . $where2;
         $result2 = mysql_query($query2);
         $husband = mysql_fetch_array($result2);

         $where3 = "WHERE KeyName='" . $marriage["WifeKey"] . "' AND Alive='Y'";
         if ($clan != "All")
         {
            $where3 = $where3 . " AND Clan='" . $clan . "'";
         }
         $query3 = "SELECT * FROM People " . $where3;
         $result3 = mysql_query($query3);
         $wife = mysql_fetch_array($result3);

         if ($husband and $wife)
         {
            print "<tr>\n";
            print "<td>&nbsp;" . prt($mon, $day, $marriage["YrWed"]) . "&nbsp;\n";
            print "<td>&nbsp;" . pretty($husband) . "&nbsp;\n";
            print "<td>&nbsp;" . pretty($wife) . "&nbsp;\n";
            $cnt++;
         }
      }
   }
   return $cnt;
}

function prt($mon, $day, $yr)
{
   global $months;
   return $months[$mon-1] . " " . $day . ", " . $yr;
}

function pretty($person)
{
   $nick = $person["Nickname"];
   if ($nick != null and $nick != "")
   {
      $nick = " (" . $nick . ")";
   }
   return $person["First"] . " " . $person["Last"] . $nick;
}
?>

</table>

<p><hr><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
