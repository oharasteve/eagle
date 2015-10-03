<?php
  db_open();
  
function db_open()
{
  global $dblink;
  $dblink = mysql_connect("dave.db.hostedresource.com", "userid", "pssword");
  mysql_select_db("database");
}

function db_close()
{
  global $dblink;
  mysql_close($dblink);
}

function db_errno()
{
  global $dblink;
  return mysql_errno($dblink);
}

function db_error()
{
  global $dblink;
  return mysql_error($dblink);
}
?>
