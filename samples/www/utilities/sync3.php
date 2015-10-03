<?php
    $topDir = "..";
	
    $code = $_REQUEST["code"];
	if ($code == "justPhotos") $topDir = "../photos";
	if ($code == "justDox") $topDir = "../Dox";
	$skipEm = ($code == "noPhotosDox");
	
    doDir($topDir, $skipEm);

// Careful, this is recursive. And slow!
function doDir($currdir, $skipEm)
{
    $thelist = array();
    if ($handle = opendir($currdir))
	{
        while (false !== ($file = readdir($handle)))
        {
			$full = $currdir . '/' . $file;
			if (is_dir($full))
			{
				if ($file != "." && $file != "..")
				{
				    if ( ! $skipEm || ($file != "photos" && $file != "Dox"))
					{
						doDir($full, false);
					}
				}
			}
			else
    		{
				$when = date("Y-n-d H:i:s", filemtime($full));
                array_push($thelist, $currdir . ',' . $file . ',' . $when . ',' . filesize($full));
            }
        }
        closedir($handle);
    }
	
    sort($thelist);
    foreach($thelist as $value)
    {
        echo $value . "\n";
    }
}
?>
