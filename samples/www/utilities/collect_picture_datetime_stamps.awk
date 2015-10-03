BEGIN {
}

/ AM / {
    print "UPDATE Photos SET WhenAdded='" $7 "-" $5 "-" $6 " " $2 ":" $3 "' WHERE Photo = '" $8 "';"
}

/ PM / {
    print "UPDATE Photos SET WhenAdded='" $7 "-" $5 "-" $6 " " ($2+12) ":" $3 "' WHERE Photo = '" $8 "';"
}