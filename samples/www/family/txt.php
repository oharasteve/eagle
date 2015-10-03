<?php
   # Connect to database
   include("../db.php");

   $gap = "   ";
   $crlf = "\r\n";

   # Select clan, maybe
   $clan = $_REQUEST["Clan"];
   if (!isset($clan))
   {
      $clan = "All";
   }

   $where = "WHERE Details.Clan = Families.FamilyKey AND People.KeyName = Details.KeyName";
   if ($clan != "All")
   {
      $where = $where . " AND Details.Clan='" . $clan . "'";
      $directory = $clan . " Family" . $crlf . $crlf;
      $txt = $clan . ".txt";
   }
   else
   {
      $directory = "";
      $txt = "directory.txt";
   }
   $query = "SELECT * FROM Details, People, Families " . $where . " ORDER BY Details.KeyName";
   $people = mysql_query($query);

   while ($person = mysql_fetch_array($people))
   {
      $key = $person["KeyName"];
      $directory = $directory . pretty($person) . " (" .
         nicedate($person["MonBorn"], $person["DayBorn"], $person["YrBorn"]) . ")" . $crlf;

      $sex = $person["Sex"];
      if ($sex == "M")
      {
         $spousetype = "Wife";
         $mytype = "Husband";
         $parenttype = "Father";
      }
      else
      {
         $spousetype = "Husband";
         $mytype = "Wife";
         $parenttype = "Mother";
      }

      $query2 = "SELECT * FROM Marriages WHERE " . $mytype . "Key='" . $key . "'";
      $results2 = mysql_query($query2);
      $marriage = mysql_fetch_array($results2);
      if ($marriage != FALSE)
      {
         if ($marriage["Divorced"] != "Divorced")
         {
            $query3 = "SELECT * FROM People WHERE KeyName='" . $marriage[$spousetype . "Key"] . "'";
        	$results3 = mysql_query($query3);
            $spouse = mysql_fetch_array($results3);
            if ($spouse["Alive"] == "Y")
            {
               $directory = $directory . $gap . $spousetype . ": " . pretty($spouse) . " (" .
                  nicedate($spouse["MonBorn"], $spouse["DayBorn"], $spouse["YrBorn"]) . ")" . $crlf;

               $yrWed = $marriage["YrWed"];
               if ($yrWed != "" and $yrWed != "0")
               {
                  $directory = $directory . $gap . "Anniversary: " .
                     nicedate($marriage["MonWed"], $marriage["DayWed"], $yrWed) . $crlf;
               }
            }
         }

         $query4 = "SELECT * FROM People WHERE " . $parenttype . "Key='" . $key . "' ORDER BY YrBorn, MonBorn";
         $results4 = mysql_query($query4);
         while ($kid = mysql_fetch_array($results4))
         {
            if ($kid["Sex"] == "M")
         	{
         	   $child = "Son";
         	}
         	else
         	{
         	   $child = "Daughter";
         	}
            $directory = $directory . $gap . $child . ": " . pretty($kid) . " (" .
                  nicedate($kid["MonBorn"], $kid["DayBorn"], $kid["YrBorn"]) . ")" . $crlf;
         }
      }

      piece("Cell phone", $person["CellPhone"]);
      piece("Home phone", $person["HomePhone"]);
      piece("Work phone", $person["WorkPhone"]);
      piece("Other phone", $person["OtherPhone"]);
      piece("Home email", $person["HomeEmail"]);
      piece("Work email", $person["WorkEmail"]);
      piece("Other email", $person["OtherEmail"]);
      piece("Address", $person["HomeAddress1"]);
      piece("Address", $person["HomeAddress2"]);
      piece("Address", $person["HomeAddress3"]);
      $directory = $directory . $crlf;
   }

   $directory = $directory . $crlf . "Website: http://www.oharasteven.com";
   $directory = $directory . $crlf . "Email: steve@oharasteve.com" . $crlf;

   header("Content-type: application/octet-stream");
   header("Content-Disposition: attachment; filename=\"" . $txt . "\"");
   header("Content-Transfer-Encoding: binary");
   if (strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE'))
   {
      // IE cannot download from sessions without a cache
      header('Cache-Control: public');
   }
   echo $directory;
   
   db_close();

function piece($lbl, $val)
{
   global $directory, $gap, $crlf;
   if ($val != "")
   {
      $directory = $directory . $gap . $lbl . ": " . $val . $crlf;
   }
}

function nicedate($m, $d, $y)
{
   $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

   if ($m != "" and $m != "0")
   {
      $mon = $months[$m-1];
      if ($d != "" and $d != "0")
      {
         return $mon . " " . $d . ", " . $y;
      }
      return $mon . ", " . $y;
   }

   return $y;
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
