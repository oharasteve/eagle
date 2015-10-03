<?php
  # Connect to database
  include("../db.php");

  $months = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
  $doPix = true;

function initColRows($totalColumns, $totalRows) {
   $boxWidth = 55;                     // Sync with gen.js
   $fontHeight = 12;                   // Sync with gen.js
   $boxHeight = 4 * $fontHeight + 4;   // Sync with gen.js

   $screenWidth = 2 * $totalColumns * $boxWidth;
   $screenHeight = 3 * $totalRows * $boxHeight;
   print "<canvas id=\"cnvs\" width=\"" . $screenWidth . "px\" height=\"" . $screenHeight . "px\"></canvas>\n";

   print "<script>\n";
   print "  var canvas = document.getElementById('cnvs');\n";
   print "  var g = canvas.getContext('2d');\n";
   print "  screenWidth = " . $screenWidth . ";\n";
   print "  screenHeight = " . $screenHeight . ";\n";
   print "\n";
}

function goUpTree($gen, $row, $rows) {
	$prev = $gen;
	while (count($prev) > 0) {
		$curr = findParents($prev);
		doit(--$row, $rows, $prev, $curr);
		$prev = $curr;
	}
	return $row;
}

function goDownTreeNoPix($gen, $rows) {
  global $doPix;
  $doPix = false;
  print "  nopix_();\n";
  goDownTree($gen, 0, $rows);
}

function goDownTree($gen, $row, $rows) {
	$prev = $gen;
	while (count($prev) > 0) {
		$curr = findChildren($prev);
		doit($row++, $rows, $curr, $prev);
		$prev = $curr;
	}

	$gen0 = array();
	doit($row, $rows, $gen0, $prev);
	return $row;
}

function doit($row, $rows, $genChild, $genParent) {
	$sizeParent = count($genParent);
	$sizeChild = count($genChild);
	for ($i=0; $i < $sizeParent; $i++) doRow($row, $rows, $i, $sizeParent, $genParent[$i]);
	for ($i=1; $i < $sizeParent; $i++) maybeWedding($row, $rows, $i, $sizeParent, $genParent);
  if ($row >= 0) {
	  for ($i=0; $i < $sizeChild; $i++) maybeParent($row, $rows, $genParent, $genChild, $i);
  }
	print "\n";
}

function findParents($gen) {
	$result = array();
	foreach ($gen as $name) {
		$query = "SELECT * FROM People WHERE KeyName='" . $name . "'";
		$people = mysql_query($query);
		while ($person = mysql_fetch_array($people)) {
			$father = $person["FatherKey"];
			$mother = $person["MotherKey"];
			if ($father != "") array_push($result, $father);
			if ($mother != "") array_push($result, $mother);
		}
	}
	return $result;
}

function findChildren($gen) {
	$result = array();
	foreach ($gen as $name) {
		$query = "SELECT * FROM People WHERE FatherKey='" . $name . "' OR MotherKey='" . $name . "' ORDER BY YrBorn, MonBorn";
		$people = mysql_query($query);
		while ($person = mysql_fetch_array($people)) {
			$key = $person["KeyName"];
			if (in_array($key, $result)) continue;	// Beware duplicates for married couples.
			array_push($result, $key);

			if ($person["Sex"] == "F") {
				$query2 = "SELECT * FROM Marriages WHERE WifeKey='" . $key . "' AND Divorced != 'Divorced'";
				$marriageList = mysql_query($query2);
				while ($marriage = mysql_fetch_array($marriageList)) {
					array_push($result, $marriage["HusbandKey"]);
				}
			} else {
				$query3 = "SELECT * FROM Marriages WHERE HusbandKey='" . $key . "' AND Divorced != 'Divorced'";
				$marriageList = mysql_query($query3);
				while ($marriage = mysql_fetch_array($marriageList)) {
					array_push($result, $marriage["WifeKey"]);
				}
			}
		}
	}
	return $result;
}

function maybeParent($row, $rows, $genParent, $genChild, $i) {
	$sizeParent = count($genParent);
	$sizeChild = count($genChild);
	$query = "SELECT * FROM People WHERE KeyName='" . $genChild[$i] . "'";
	$people = mysql_query($query);
	while ($person = mysql_fetch_array($people)) {
		$mother = $person["MotherKey"];
		$father = $person["FatherKey"];
    $mom = -1;
    $dad = -1;
    for ($j=0; $j<$sizeParent; $j++) {
      if ($father && $father != "" && $genParent[$j] == $father) $dad = $j;
      if ($mother && $mother != "" && $genParent[$j] == $mother) $mom = $j;
    }

    if ($mom >= 0 && $dad >= 0) {
      $first = $dad < $mom ? $dad : $mom;
			print "  parents(g, " . $row . ", " . $rows . ", " . $first . ", " . $sizeParent . ", " . $i . ", " . $sizeChild . ");\n";
    } else if ($mom >= 0) {
			print "  parent(g, " . $row . ", " . $rows . ", " . $mom . ", " . $sizeParent . ", " . $i . ", " . $sizeChild . ");\n";
    } else if ($dad >= 0) {
			print "  parent(g, " . $row . ", " . $rows . ", " . $dad . ", " . $sizeParent . ", " . $i . ", " . $sizeChild . ");\n";
    }
	}
}

function doRow($row, $rows, $col, $cols, $key) {
  global $months;
	$query = "SELECT * FROM People WHERE KeyName='" . $key . "'";
	$people = mysql_query($query);
	$person = mysql_fetch_array($people);

	$first = $person["Title"];
	if ($first == "King" or $first == "Queen") {
		$last = $person["First"];
		$jr = $person["Jr"];
		if ($jr and $jr != "" and $jr != "Saint") {
			$last = $last . " " . $jr;
		}
	} else {
		$first = $person["Nickname"];
		if (!$first or $first == "") {
			$first = $person["First"];
		}
		$last = $person["Maiden"];
		if (!$last or $last == "") {
			$last = $person["Last"];
		}
		if ($last == "de la Villebeuvre") {
			$last = "de la V.";
		}
	}

	$year = $person["YrBorn"];
	$died = $person["YrDied"];
	if (!$died or $died == "0") {
		$month = $person["MonBorn"];
		$day = $person["DayBorn"];
		if (!$month or $month == "0") {
			$year1 = "born";
      $year2 = $year;
		} else if (!$day or $day == "0") {
			$year1 = "b. " . $months[$month-1];
      $year2 = $year;
		} else {
			$year1 = $months[$month-1] . " " . $day;
      $year2 = $year;
		}
	} else {
		$year1 = $year . "-";
    $year2 = $died;
	}

	if ($person["Sex"] == "F") {
		$sex = "girl";
	} else {
		$sex = "boy";
	}
	print "  " . $sex . "(g, " . $row . ", " . $rows . ", " . $col . ", " . $cols . ", '" .
			addslashes($first) . "', '" . addslashes($last) . "', '" . $year1 . "', '" . $year2 . "');\n";

  global $doPix;
  if ($doPix) {
    $query = "SELECT * FROM PeoplePhotos AS p, Faces AS f WHERE " .
        "f.Person = p.KeyName AND " .
        "p.KeyName = '" . $key . "' AND " .
        "p.Photo = f.Photo " .
        "ORDER BY f.HighYear DESC";
    $faceList = mysql_query($query);
    $faces = mysql_fetch_array($faceList);
    if (! $faces) {
      $query = "SELECT * FROM Faces WHERE Person = '" . $key . "' ORDER BY HighYear DESC";
      $faceList = mysql_query($query);
      $faces = mysql_fetch_array($faceList);
    }

    if ($faces) {
      $query = "SELECT * FROM Photos WHERE Photo = '" . $faces["Photo"] . "';";
      $photoList = mysql_query($query);
      $photo = mysql_fetch_array($photoList);
      $photoname = $photo["Directory"] . "/" . $photo["Photo"] . $photo["Suffix"];
      $width = $photo["Width"];
      $height = $photo["Height"];

      $left = round(($faces["Left"] * $width) / 65535);
      $top = round(($faces["Top"] *  $height) / 65535);
      $right = round(($faces["Right"] *  $width) / 65535);
      $bottom = round(($faces["Bottom"] * $height) / 65535);

      print "  face(g, " . $row . ", " . $rows . ", " . $col . ", " . $cols . ", '" . $photoname . "', " .
          $left . ", " . $right . ", " . $top . ", " . $bottom . ");\n";
    } else {
      $line1 = "";
      $line2 = "";
      $line3 = "";
      $line4 = "";
      $born = $person["WhereBorn"];
      $died = $person["WhereBuried"];
      if ($born) {
        $line1 = "Born in";
        $line2 = $born;
        if ($died) {
          $line3 = "Died in";
          $line4 = $died;
        }
      } else if ($died) {
        $line1 = "Died in";
        $line2 = $died;
      }
      print "  noface(g, " . $row . ", " . $rows . ", " . $col . ", " . $cols .
          ", '" . $line1 . "', '" . $line2 . "', '" . $line3 . "', '" . $line4 . "');\n";
    }
  }

	return $col;
}

function maybeWedding($row, $rows, $col, $cols, $gen) {
  global $months;
	$query = "SELECT * FROM Marriages WHERE HusbandKey='" . $gen[$col-1] . "' AND WifeKey='" . $gen[$col] . "' OR " .
			"HusbandKey='" . $gen[$col] . "' AND WifeKey='" . $gen[$col-1] . "'";
	$marriageList = mysql_query($query);
	while ($marriage = mysql_fetch_array($marriageList)) {
		$mon = $marriage["MonWed"];
		$day = $marriage["DayWed"];
		$year = $marriage["YrWed"];
		if ($year == 0) {
      $line1 = "";
      $line2 = "m.";
      $line3 = "";
      $line4 = "";
    } else {
      if ($mon == 0) {
        $line1 = "";
        $line2 = "m.";
        $line3 = $year;
        $line4 = "";
      }
      else if ($day == 0) {
        $line1 = "m.";
        $line2 = $months[$mon-1];
        $line3 = $year;
        $line4 = "";
      } else {
        $line1 = "m.";
        $line2 = $months[$mon-1];
        $line3 = $day;
        $line4 = $year;
      }
    }
		print "  wed(g, " . $row . ", " . $rows . ", " . ($col-1) . ", " . $cols . ", '" .
        $line1 . "', '" . $line2 . "', '" . $line3 . "', '" . $line4 . "');\n";
	}
}

function done() {
   print "</script>\n";
   db_close();
}
?>
