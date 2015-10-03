<?php
   //
   // Sample usage: <iframe src="readfile.php?fname=whatev.java" width=700 height=150>
   //

   print "<html>";
   print "<body bgcolor=\"#f0f0f0\">";

   $fname = $_REQUEST["fname"];
   print "<p>Open <a href=\"" . $fname . "\">" . $fname . "</a></p><hr>";

   print "<pre>";

   print "<b>" . htmlspecialchars($fname) . "</b>\n\n";

   // Security: Do not allow relative file references
   $bads = array(":", "\\");	// Allow directories with slash (/) ...
   $fname2 = str_replace($bads, "", $fname);

   $handle = fopen($fname2, "r");
   while (!feof($handle))
   {
      $buffer = fgets($handle);
      print htmlspecialchars($buffer);
   }
   fclose($handle);

   print "</pre>";
   print "</body>";
   print "</html>";
?>
