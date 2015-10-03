<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>O'Hara Genogram</title>
<script src="Graphs/gen.js" type="text/javascript"></script>
</head>
<body bgcolor="#F0F0F0">

<br/>
<h1><center>O'Hara Genogram</center></h1>
<p>This tree is huge and probably off the screen. You might try scrolling or changing the view to 50%.</p>

<center>

<?php
  include("gen.php");

	# Connect to database
	$rows = 42;
  $cols = 20;
	initColRows($cols, $rows);

	$gen0 = array();
	$gen1 = array("AbigaelOHara", "AdalineOHara", "AnnalieseOHara", "AvangelineOHara", "AnnaWiggins", "JacobWiggins");
	$gen2 = array("ShaneOHara", "JamieOHara", "RobinOHara", "RobertWiggins", "LanceOHara");
	$gen3 = array("StevenOHara", "DaisyOHara");

	$row = $rows;
	doit(--$row, $rows, $gen0, $gen1);
	doit(--$row, $rows, $gen1, $gen2);
	doit(--$row, $rows, $gen2, $gen3);

	goUpTree($gen3, $row, $rows);

	done();
?>

</center>
<hr/><font size=-1><i>Last Updated on 9/19/2015
<br/>Email: <a href="mailto:steve@oharasteve.com">steve@oharasteve.com</a></i></font></p>

</body>
</html>
