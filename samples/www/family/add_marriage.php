<html>

<head>
<title>Add a Marriage Event</title>
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
<h1>Create New Marriage Event</h1>
<hr/>
<form action="done_marriage.php" method="post">

<?php
   # Connect to database
   include("../db.php");

   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

   # Husband and Wife
   print "<table>\n";
   print "  <tr valign=bottom>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;Husband Key</i></font>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;Wife Key</i></font>\n";
   print "  <tr>\n";
   print "    <td><input type=\"text\" name=\"HusbandKey\" maxlength=20 value=\"\">\n";
   print "    <td><input type=\"text\" name=\"WifeKey\" maxlength=20 value=\"\">\n";
   print "</table>\n";

   #Month
   print "<table>\n";
   print "  <tr>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;When Married</i></font>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;Year</i></font>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;Where Married</i></font>\n";
   print "    <td><font size=-2><i>&nbsp;&nbsp;Status</i></font>\n";
   print "  <tr valign=bottom>\n";
   print "    <td><select name=monMarried>\n";
   print "      <option value=0></option>\n";
   for ($i=1; $i<=12; $i++)
   {
      print "      <option value=" . $i . ">" . $months[$i-1] . "</option>\n";
   }
   print "    </select>\n";

   # Day
   print "    <select name=dayMarried>\n";
   print "      <option value=0></option>\n";
   for ($i=1; $i<=31; $i++)
   {
      print "      <option value=" . $i . ">" . $i . "</option>\n";
   }
   print "    </select>\n";

   # Year
   print "    <td><input type=\"text\" name=yearMarried size=4 value=\"\">\n";

   # Where married   
   print "    <td><input type=\"text\" name=\"whereMarried\" maxlength=40 value=\"\">\n";

   # Married / Divorced / ...
   print "    <td><select name=stillMarried>\n";
   print "      <option value=\"\" selected>Married</option>\n";
   print "      <option value=\"Divorced\">Divorced</option>\n";
   print "      <option value=\"Widowed\">Widowed</option>\n";
   print "    </select>\n";
   print "</table>\n";

   print "<p><button type=submit>Insert Marriage Event</button>\n";

   db_close();
?>

</form>
<hr/>

<p><font size=-1><i>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font>

</body>
</html>
