{
  before = substr($0,2,length($0)-3);
  after = before;
  gsub(/'/,"",after);
  gsub(/ - /,"-",after);
  gsub(/, /,"_",after);
  gsub(/,/,"_",after);
  gsub(/ /,"_",after);
  gsub(/!/,"_",after);
  gsub(/\&/,"and",after);
  gsub(/\(/,"_",after);
  gsub(/\)/,"_",after);
  gsub(/+/,"_",after);
  print "move \"" before "\" \"" after "\"";
}