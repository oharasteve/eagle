BEGIN {
  i = 0;
}

/bytes/ {
  files[i] = $1;
  bytes[i] = $3;
  b[i] = $3;
  gsub(/,/, "", b[i]);
  avg[i] = b[i] / files[i] / 1000
  i++;
}

END {
  otherFiles = files[4] - files[0] - files[1] - files[2] - files[3];
  otherBytes = b[4] - b[0] - b[1] - b[2] - b[3];
  otherAvg = otherBytes / otherFiles / 1000;
  
  printf("s+@files0@+%'d+;\n", files[0]);
  printf("s+@bytes0@+%s+;\n", bytes[0]);
  printf("s+@avg0@+%'dk+;\n", avg[0]);
  printf("s+@files1@+%'d+;\n", files[1]);
  printf("s+@bytes1@+%s+;\n", bytes[1]);
  printf("s+@avg1@+%'dk+;\n", avg[1]);
  printf("s+@files2@+%'d+;\n", files[2]);
  printf("s+@bytes2@+%s+;\n", bytes[2]);
  printf("s+@avg2@+%'dk+;\n", avg[2]);
  printf("s+@files3@+%'d+;\n", files[3]);
  printf("s+@bytes3@+%s+;\n", bytes[3]);
  printf("s+@avg3@+%'dk+;\n", avg[3]);
  printf("s+@files4@+%'d+;\n", otherFiles);
  printf("s+@bytes4@+%'d+;\n", otherBytes);
  printf("s+@avg4@+%'dk+;\n", otherAvg);
  printf("s+@files5@+%'d+;\n", files[4]);
  printf("s+@bytes5@+%s+;\n", bytes[4]);
  printf("s+@avg5@+%'dk+;\n", avg[4]);
  printf("s+@dirs5@+%'d+;\n", files[5]);
}