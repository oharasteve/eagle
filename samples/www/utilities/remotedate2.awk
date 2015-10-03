BEGIN {
    dir = "";
    print " Remote File" "," "Year" "," "Month" "," "Day";
}

/:$/ {
    dir = substr($1,3,999);
    sub(/:/,"",dir);
}

/^-rw/ {
    month = $6;
    day = $7 + 1;		# Due to CST -vs- PST, last 2 hours in a day ... so, cheat!
    if (day < 10) day = "0" day;
    yr = $8;
    if (index(yr,":") > 0) yr = strftime("%Y");	# Current year, like 2010
    fname = substr($9,1,length($9));
    
    gsub(/Jan/,"01",month);
    gsub(/Feb/,"02",month);
    gsub(/Mar/,"03",month);
    gsub(/Apr/,"04",month);
    gsub(/May/,"05",month);
    gsub(/Jun/,"06",month);
    gsub(/Jul/,"07",month);
    gsub(/Aug/,"08",month);
    gsub(/Sep/,"09",month);
    gsub(/Oct/,"10",month);
    gsub(/Nov/,"11",month);
    gsub(/Dec/,"12",month);
    print  "/" dir "/" fname  "," yr "," month "," day;
}