BEGIN {
    dir = "";
    print " Local File" "," "Month" "," "Day" "," "Year";
}

/Directory of / {
    dir = substr($0, 15, 999);
	gsub(/C:\\www2/, "", dir);
    gsub(/\\/,"/",dir);
}

/\/.*\// && !/<DIR>/ {
    fname = $5;
    split($1,date,"/");
    print dir "/" fname "," date[3] "," date[1] "," date[2];
}