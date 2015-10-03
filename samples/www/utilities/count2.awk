BEGIN {
  jpgs = 0;
  mpegs = 0;
  save = "no file";
  printf("=================================================\r\n");
  printf("\r\n");
}

/jpg/ {
  jpgs += $2;
  mpegs += $4;
  printf("jpg=%5d mpeg=%3d  %s\r\n", $2, $4, save);
  next;
}

{
  save = substr($0, 0, length($0)-1);
}

END {
  printf("\r\n");
  printf("jpg=%5d mpeg=%3d\r\n", jpgs, mpegs);
  printf("\r\n");
}