<?php
   include("../db.php");

   // Collect table names
   $tables = array();
   $result = mysql_query("SHOW TABLES");
   while ($tbl = mysql_fetch_row($result))
   {
      $tables[] = $tbl[0];
   }
   
   print "# Created by backupDB: " . date("Y-m-d H:i:s") . "\n";
   
   // Cycle through the tables
   foreach ($tables as $table)
   {
      $result = mysql_query("SELECT * FROM " . $table);
      $num_fields = mysql_num_fields($result);
      
      $create = mysql_fetch_row(mysql_query("SHOW CREATE TABLE ".$table));
      print "\n" . $create[1] . ";\n\n";
      
      for ($i = 0; $i < $num_fields; $i++) 
      {
	     // Cycle through all the rows for this table
         while ($row = mysql_fetch_row($result))
         {
            print "INSERT INTO `" . $table . "` VALUES (";
            for ($j=0; $j<$num_fields; $j++) 
            {
               if ($j > 0) print ",";

               if (isset($row[$j]))
               {
			      if (is_numeric($row[$j]))
				  {
                     print $row[$j];
				  }
				  else
				  {
                     $txt = addslashes($row[$j]);
                     $txt = ereg_replace("\n", "\\n", $txt);
                     print "'" . $txt . "'" ;
				  }
               }
               else
               {
                  print "''";
               }
            }
            print ");\n";
         }
      }
      print "\n";
   }

   db_close();
?>
