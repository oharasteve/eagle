BEGIN {
    dir = "";
    print "Size" "," "File";
}

/Directory of / {
    sc = index($0, "www2");
    dir = substr($0, sc+4, 999);
    gsub(/\\/,"/",dir);
}

/\/.*\// && !/<DIR>/ {
    size = $4;
    fname = $5;
    gsub(/,/,"",size);
    print size "," dir "/" fname;
}