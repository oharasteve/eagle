<?php
   # Connect to database
   include("../db.php");

   $query = "SELECT * FROM Photos";
   $people = mysql_query($query);

   $xml = "screensaver.xml";
   header("Content-type: application/octet-stream");
   header("Content-Disposition: attachment; filename=\"" . $xml . "\"");
   header("Content-Transfer-Encoding: binary");
   if (strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE'))
   {
      // IE cannot download from sessions without a cache
      header('Cache-Control: public');
   }

   echo "<?xml version=\"1.0\" encoding=\"windows-1252\" ?>\n";
   echo "<coolcaptions>\n";
   echo "  <authorinfo>\n";
   echo "    <regkey>000</regkey>\n";
   echo "    <username>n/a</username>\n";
   echo "    <scrtitle>Steve website pictures</scrtitle>\n";
   echo "    <author>n/a</author>\n";
   echo "    <email>name@your-company.com</email>\n";
   echo "    <www>www.your-company.com</www>\n";
   echo "  </authorinfo>\n";
   echo "  <options>\n";
   echo "    <durationofscene>20000</durationofscene>\n";
   echo "    <nobjects>0</nobjects>\n";
   echo "    <nsprites>1</nsprites>\n";
   echo "    <randomscenes>0</randomscenes>\n";
   echo "    <usedescriptions>-1</usedescriptions>\n";
   echo "    <marquee>Hello Hello Hello</marquee>\n";
   echo "    <marqueetype>0</marqueetype>\n";
   echo "    <showtime>-1</showtime>\n";
   echo "    <bctype>1</bctype>\n";
   echo "    <birandom>-1</birandom>\n";
   echo "    <stretchlargebitmaps>-1</stretchlargebitmaps>\n";
   echo "    <orbits>xxxxxxoxxx</orbits>\n";
   echo "    <playmedia>0</playmedia>\n";
   echo "    <tcrandom>-1</tcrandom>\n";
   echo "  </options>\n";
   echo "  <lenses>\n";
   echo "    <showlenses>0</showlenses>\n";
   echo "    <nlenses>6</nlenses>\n";
   echo "    <lensesize>120</lensesize>\n";
   echo "  </lenses>\n";
   echo "  <font>\n";
   echo "    <fontsize>60</fontsize>\n";
   echo "    <fontface>Tahoma</fontface>\n";
   echo "    <bold>-1</bold>\n";
   echo "    <italic>0</italic>\n";
   echo "    <logotext>Hello</logotext>\n";
   echo "    <fgtxtype>0</fgtxtype>\n";
   echo "    <fontcolor>1088463</fontcolor>\n";
   echo "    <fontimage>C:\Program Files (x86)\\Cool Captions\\textures\\texture5.jpg</fontimage>\n";
   echo "    <bgtxtype>0</bgtxtype>\n";
   echo "    <backcolor>15777856</backcolor>\n";
   echo "    <backimage>C:\Program Files (x86)\\Cool Captions\\textures\\texture1.jpg</backimage>\n";
   echo "    <bevelwidth>5</bevelwidth>\n";
   echo "    <bevelheight>8</bevelheight>\n";
   echo "    <specularity>20</specularity>\n";
   echo "    <vlightx>-301486674794961</vlightx>\n";
   echo "    <vlighty>301486674794961</vlighty>\n";
   echo "    <vlightz>904550479432826</vlightz>\n";
   echo "    <slightx>-282216260515079</slightx>\n";
   echo "    <slighty>188144173676719</slighty>\n";
   echo "    <slightz>940720868383597</slightz>\n";
   echo "  </font>\n";
   echo "  <images>\n";
   echo "    <shuffle>1</shuffle>\n";
   
   while ($photo = mysql_fetch_array($people))
   {
      $dir = str_replace("/","\\",$photo["Directory"]);
      $caption = $photo["Caption"];
      $year = $photo["Year"];
      $month = $photo["Month"];
      $day = $photo["Day"];
      $pic = $photo["Photo"];
      $fname = $dir . "\\" . $pic . $photo["Suffix"];

      if (strpos($fname, "2007-rood") || strpos($fname, "rarr"))
      {
         continue;
      }

      if ($year and $year > 0)
      {
         $date = $year;

         if ($month and $month > 0)
         {
            if ($day and $day > 0)
            {
               $date = $month . "/" . $day . "/" . $year;
            }
            else
            {
               $date = $month . "/" . $year;
            }
         }

         if ($caption and $caption != "")
         {
            $caption = $caption . " (" . $date . ")";
         }
         else
         {
            $caption = "Date: " . $date;
         }
      }

      if ($caption and $caption != "")
      {
         $caption = $caption . " ";
      }

      echo "    <image>\n" .
           "      <name>C:\\www2" . $fname . "</name>\n" .
           "      <caption>" . htmlspecialchars($caption . "[" . $pic) . "]</caption>\n" .
           "    </image>\n";
   }
   
   echo "  </images>\n";
   echo "  <sprites>\n";
   echo "    <shuffle>-1</shuffle>\n";
   echo "  </sprites>\n";
   echo "  <music>\n";
   echo "    <shuffle>0</shuffle>\n";
   echo "  </music>\n";
   echo "</coolcaptions>\n";
   
   db_close();
?>
