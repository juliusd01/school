# count number of files in Apple folder
cd Apple
ls -l | grep -v ^l | wc -l

# count number of files in Pear folder
cd ../Pear
ls -l | grep -v ^l | wc -l