<?php
   # Connect to database
   include("../db.php");

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
   }
   $query = "SELECT * FROM Details, People, Families " . $where . " ORDER BY Details.KeyName";
   $people = mysql_query($query);

   $directory = "Clan,Key,First,Middle,Maiden,Last,MonBorn,DayBorn,YrBorn,CellPhone,HomePhone," .
      "WorkPhone,OtherPhone,HomeEmail,WorkEmail,OtherEmail,HomeAddress1,HomeAddress2,HomeAddress3\n";
   while ($person = mysql_fetch_array($people))
   {
      $directory = $directory . "\"" .
         $person["Clan"] . "\",\"" .
         $person["KeyName"] . "\",\"" .
         $person["First"] . "\",\"" .
         $person["Middle"] . "\",\"" .
         $person["Maiden"] . "\",\"" .
         $person["Last"] . "\",\"" .
         $person["MonBorn"] . "\",\"" .
         $person["DayBorn"] . "\",\"" .
         $person["YrBorn"] . "\",\"" .
         $person["CellPhone"] . "\",\"" .
         $person["HomePhone"] . "\",\"" .
         $person["WorkPhone"] . "\",\"" .
         $person["OtherPhone"] . "\",\"" .
         $person["HomeEmail"] . "\",\"" .
         $person["WorkEmail"] . "\",\"" .
         $person["OtherEmail"] . "\",\"" .
         $person["HomeAddress1"] . "\",\"" .
         $person["HomeAddress2"] . "\",\"" .
         $person["HomeAddress3"] . "\"\n";
   }

   $csv = "directory.csv";
   header("Content-type: application/octet-stream");
   header("Content-Disposition: attachment; filename=\"" . $csv . "\"");
   header("Content-Transfer-Encoding: binary");
   if (strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE'))
   {
      // IE cannot download from sessions without a cache
      header('Cache-Control: public');
   }
   echo $directory;

   db_close();
?>
