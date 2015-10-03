<html>

<head>
<title>Family Tree People</title>
<style type="text/css">
  table.bad {
    border-style: solid;
    border-top-width: 0px;
    border-bottom-width: 0px;
    border-left-width: 50px;
    border-right-width: 50px;
  }
</style>
</head>

<body bgcolor="#f0f0f0" onload="self.focus();">

<?php
   # Connect to database
   include("../db.php");

   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

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
   if (!$adding and !isset($key))
   {
      print "<h1>Family Tree People</h1>";
      print "<p>No person specified. Please use ?Key=name on the web address</p>";
   }
   else
   {
      $ok = TRUE;
      if ($adding)
      {
         $heading = "Create New Person";
         $sex = "M";
         $mother = "";
         $father = "";
         $nick = "";
         $first = "";
         $middle = "";
         $maiden = "";
         $last = "";
         $jr = "";
         $ttl = "";
         $whereborn = "";
         $alive = "Y";
         $wheredied = "";
         $note = "";
         $wiki = "";
         $grave = "";
      }
      else
      {
         $query = "SELECT * FROM People WHERE KeyName='" . $key . "'";
         $people = mysql_query($query);
         $person = mysql_fetch_array($people);  # Just grab first person
         if (!$adding and !$person)
         {
            print "<p><b>No such person: " . htmlentities($key) . "</b>\n";
            $ok = FALSE;
         }
         else
         {
            $heading = "Family Tree Information for " . htmlentities($key);
            $sex = $person["Sex"];
            $mother = $person["MotherKey"];
            $father = $person["FatherKey"];
            $nick = $person["Nickname"];
            $first = $person["First"];
            $middle = $person["Middle"];
            $maiden = $person["Maiden"];
            $last = $person["Last"];
            $jr = $person["Jr"];
            $ttl = $person["Title"];
            $whereborn = $person["WhereBorn"];
            $alive = $person["Alive"];
            $wheredied = $person["WhereBuried"];
            $note = $person["Note"];
            $wiki = $person["WikiTree"];
            $grave = $person["FindAGrave"];
         }
      }

      if ($ok)
      {
          print "<h1>" . $heading . " </h1>\n";

          print "<hr>\n";
          print "<form action=\"done_person.php\" method=\"post\">\n";

          if ($sex == "M")
          {
             $sexm = " checked";
             $sexf = "";
          }
          else
          {
             $sexm = "";
             $sexf = " checked";
          }

          print "<p><table><tr valign=bottom>\n";

          if ($adding)
          {
                print "    <td><input type=\"text\" name=\"Key\" value=\"" . htmlentities($key) . "\">\n";
          }
          else
          {
                print "    <td><input type=\"text\" name=\"Key\" readonly value=\"" . htmlentities($key) . "\">\n";
          }

          print "    <td width=100 align=center><input type=\"radio\" name=\"Sex\" value=\"M\"" . $sexm . "><font size=-1>Male</font>\n";
          print "        <br><input type=\"radio\" name=\"Sex\" value=\"F\"" . $sexf . "><font size=-1>Female</font>\n";
          print "    <td><input type=\"text\" name=\"MotherKey\" maxlength=20 value=\"" . $mother . "\">\n";
          print "    <td><input type=\"text\" name=\"FatherKey\" maxlength=20 value=\"" . $father . "\">\n";
          print "    <td><input type=\"text\" name=\"Nickname\" maxlength=20 value=\"" . $nick . "\">\n";
          print "  <tr valign=top>\n";

          if ($adding)
          {
                   print "    <td><font size=-2><i>&nbsp;&nbsp;Key (must be unique)</i></font>\n";
          }
          else
          {
                   print "    <td><font size=-2><i>&nbsp;&nbsp;Key (cannot change)</i></font>\n";
             }

          print "    <td><font size=-2><i>&nbsp;&nbsp;</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Mother Key</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Father Key</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Nickname</i></font>\n";
          print "</table>\n";

          print "<p><table><tr valign=bottom>\n";
          print "    <td><input type=\"text\" name=\"First\" maxlength=20 value=\"" . $first . "\">\n";
          print "    <td><input type=\"text\" name=\"Middle\" maxlength=20 value=\"" . $middle . "\">\n";
          print "    <td><input type=\"text\" name=\"Maiden\" maxlength=20 value=\"" . $maiden . "\">\n";
          print "    <td><input type=\"text\" name=\"Last\" maxlength=20 value=\"" . $last . "\">\n";
          print "    <td><input type=\"text\" name=\"Jr\" maxlength=10 size=6 value=\"" . $jr . "\">\n";
          print "    <td><input type=\"text\" name=\"Title\" maxlength=10 size=6 value=\"" . $ttl . "\">\n";
          print "  <tr valign=top>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;First</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Middle</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Maiden</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Last</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Jr, etc</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Title</i></font>\n";
          print "</table>\n";

          if ($alive == "Y")
          {
             $alive = " checked";
             $dead = "";
          }
          else
          {
             $alive = "";
             $dead = " checked";
          }

          print "<p><table><tr valign=bottom>\n";
          mdy("MonBorn", "DayBorn", "YrBorn");
          print "    <td><input type=\"text\" name=\"WhereBorn\" maxlength=40 value=\"" . $whereborn . "\">\n";
          print "    <td width=100 align=center><input type=\"radio\" name=\"Alive\" value=\"Y\"" . $alive . "><font size=-1>Alive</font>\n";
          print "        <br><input type=\"radio\" name=\"Alive\" value=\"N\"" . $dead . "><font size=-1>Dead</font>\n";
          mdy("MonDied", "DayDied", "YrDied");
          print "    <td><input type=\"text\" name=\"WhereBuried\" maxlength=40 value=\"" . $wheredied . "\">\n";
          print "  <tr valign=top>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;When Born</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Where Born</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;When Died</i></font>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Where Died</i></font>\n";
          print "</table>\n";

          print "<p><table><tr valign=bottom>\n";
          print "    <td><input type=\"text\" name=\"WikiTree\" maxlength=100 size=30 value=\"" . $wiki . "\">\n";
          print "  <tr valign=top>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;WikiTree entry</i></font>\n";
          print "</table>\n";
          
          print "<p><table><tr valign=bottom>\n";
          print "    <td><input type=\"text\" name=\"FindAGrave\" maxlength=100 size=30 value=\"" . $grave . "\">\n";
          print "  <tr valign=top>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Find-A-Grave entry</i></font>\n";
          print "</table>\n";
          
          print "<p><table><tr valign=bottom>\n";
          print "    <td><input type=\"text\" name=\"Note\" maxlength=200 size=110 value=\"" . htmlentities($note) . "\">\n";
          print "  <tr valign=top>\n";
          print "    <td><font size=-2><i>&nbsp;&nbsp;Note</i></font>\n";
          print "</table>\n";

          print "  <br><input type=button onclick=\"self.close();\" value=\"Cancel\">&nbsp;&nbsp;\n";

          if ($adding)
          {
             print "  <button type=submit>Insert</button>\n";
          }
          else
          {
             print "  <button type=submit>Update</button>\n";
          }

          print "  <input type=hidden name=Op value=\"" . $op . "\">\n";
          print "</form>\n";
          print "<hr>\n";
      }
   }

   db_close();

function mdy($monName, $dayName, $yrName)
{
   global $adding;
   global $person;
   global $months;

   if ($adding)
   {
      $mon = 0;
      $day = 0;
      $yr = 0;
   }
   else
   {
      $mon = $person[$monName];
      $day = $person[$dayName];
      $yr = $person[$yrName];
   }

   print "    <td><select name=" . $monName . ">\n";
   print "      <option value=0></option>\n";
   for ($i=1; $i<=12; $i++)
   {
      $sel = "";
      if ($i == $mon)
      {
         $sel = " selected";
      }
      print "      <option value=" . $i . $sel . ">" . $months[$i-1] . "</option>\n";
   }
   print "    </select>\n";

   print "    <select name=" . $dayName . ">\n";
   print "      <option value=0></option>\n";
   for ($i=1; $i<=31; $i++)
   {
      $sel = "";
      if ($i == $day)
      {
         $sel = " selected";
      }
      print "      <option value=" . $i . $sel . ">" . $i . "</option>\n";
   }
   print "    </select>\n";

   if ($yr == "0")
   {
      $yr = "";
   }
   print "    <input type=\"text\" name=\"" . $yrName. "\" size=4 value=\"" . $yr . "\">\n";
}
?>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
