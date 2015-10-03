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

   $outlook = "First Name,Last Name,Name,Mobile Phone,Home Phone," .
      "Business Phone,E-mail Address,Home Street,Home City,Home State\n";
   while ($person = mysql_fetch_array($people))
   {
      $outlook = $outlook . "\"" .
         $person["First"] . "\",\"" .
         $person["Last"] . "\",\"" .
         $person["First"] . " " . $person["Last"] . "\",\"" .
         $person["CellPhone"] . "\",\"" .
         $person["HomePhone"] . "\",\"" .
         $person["WorkPhone"] . "\",\"" .
         $person["HomeEmail"] . "\",\"" .
         $person["HomeAddress1"] . "\",\"" .
         $person["HomeAddress2"] . "\",\"" .
         $person["HomeAddress3"] . "\"\n";
   }

   $csv = "outlook.csv";
   header("Content-type: application/octet-stream");
   header("Content-Disposition: attachment; filename=\"" . $csv . "\"");
   header("Content-Transfer-Encoding: binary");
   if (strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE'))
   {
      // IE cannot download from sessions without a cache
      header('Cache-Control: public');
   }
   echo $outlook;

   db_close();
?>
