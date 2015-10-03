<html>

<head>
<title>Family Tree Index</title>
</head>

<body bgcolor="#f0f0f0">
<h1>Family Tree Index</h1>

<p><i><a href="index.html">Back to main page</a></i>

<p><table border=1>
<tr>
  <td><font size=+1><i>Sort by First Name</i></font>
  <td><font size=+1><i>Sort by Last Name</i></font>
  <td><font size=+1><i>Sort by Year Born</i></font>
  <td><font size=+1><i>Sort by Year Died</i></font>
</tr>

<?php
   # Connect to database
   include("db.php");

   $query1 = "SELECT * FROM People ORDER BY First, Last";
   $col1 = mysql_query($query1);

   $query2 = "SELECT * FROM People ORDER BY Last, First";
   $col2 = mysql_query($query2);

   $query3 = "SELECT * FROM People ORDER BY YrBorn, MonBorn, DayBorn";
   $col3 = mysql_query($query3);

   $query4 = "SELECT * FROM People ORDER BY YrDied, MonDied, DayDied, YrBorn, MonBorn, DayBorn";
   $col4 = mysql_query($query4);

   while ($person1 = mysql_fetch_array($col1))
   {
      print "<tr>";

      print "<td>" . anchor($person1) . "\n";

      $person2 = mysql_fetch_array($col2);
      $prefix2 = $person2["Last"];
      if ($prefix2 != "")
      {
         $prefix2 = $prefix2 . ": ";
      }
      print "<td>" . $prefix2 . anchor($person2) . "\n";

      $person3 = mysql_fetch_array($col3);
      $first3 = $person3["First"];
      $last3 = $person3["Last"];
      $prefix3 = $person3["YrBorn"];
  	  $mon3 = $person3["MonBorn"];
	    if ($mon3 > 0)
	    {
        $prefix3 = $prefix3 . "-" . z2($mon3);
	      $day3 = $person3["DayBorn"];
	      if ($day3 > 0)
	      {
              $prefix3 = $prefix3 . "-" . z2($day3);
	      }
	    }
      print "<td>" . $prefix3 . ": " . anchor($person3) . "\n";

      $person4 = mysql_fetch_array($col4);
      $first4 = $person4["First"];
      $last4 = $person4["Last"];
      $prefix4 = $person4["YrDied"];
      if ($prefix4 == 0)
      {
        if ($person4["Alive"] == 'Y')
        {
          $years = date("Y") - $person4["YrBorn"];
          $months = date("n") - $person4["MonBorn"];
          $days = date("j") - $person4["DayBorn"];
          $age = $years + $months/12.0 + $days/365.0;
          $prefix4 = "Now " . round($age, 1);
        }
        else
        {
          $prefix4 = "Died";
        }
      }
      else
      {
        $mon4 = $person4["MonDied"];
        if ($mon4 > 0)
        {
          $prefix4 = $prefix4 . "-" . z2($mon4);
          $day4 = $person4["DayDied"];
          if ($day4 > 0)
          {
                $prefix4 = $prefix4 . "-" . z2($day4);
          }
        }
      }
      print "<td>" . $prefix4 . ": " . anchor($person4) . "\n";
   }

   db_close();

function spacer($piece)
{
   if ($piece == "")
   {
      return "";
   }
   return $piece . " ";
}

function z2($num)
{
	if ($num >= 10) return "" . $num;
	return "0" . $num;
}

function anchor($person)
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

   $k = $person["KeyName"];
   return "<a href=\"view.php?Key=" . $k . "\">" . rtrim($name) . "</a>";
}

?>

</table>

<p><a href="../family/edit_person.php?Op=Add" target="edit_person">Add a Person</a>&nbsp;&nbsp;
   <font size=-1><i>(Password required)</i></font>

<p><a href="wikitree.php">Show all WikiTree entries</a> (slow!)&nbsp;&nbsp;&nbsp;Click <a href="wikilist.php">here</a> to just show WikiTree Labels.

<p><a href="../family/add_marriage.php" target="add_marriage">Add a Marriage Event</a>&nbsp;&nbsp;
   <font size=-1><i>(Password required)</i></font>

<p><a href="missing_marriages.php" target="missing_marriages">Find Missing Marriages</a>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
