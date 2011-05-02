#!/bin/bash
#
# This script requires that the tests and this script (named test.sh) be set up 
# in the root directory of your project step as:
#
# |-- test.sh
# `-- testcases
#	  |-- input
#	  |   `-- ".micro files from project webpage"
#	  `-- output
#	  	  |-- ".tiny files from project webpage"
#		  `-- ".IR files from project webpage"
#
# Setting up of the example output folder is optional.
# The script automatically runs your exe and stores the output files in the folder "my_outputs"
# inside the "testcases" folder.
# Modify further according to your specific needs. Also, don't forget to get the correct
# version of 'tinyR' executable for your specific test server too.

extMicro="micro"
extTiny="live"
extIR="IR"
C_MICRO_EXE="micro"
JAVA_MICRO_EXE="Micro"
MY_OUTPUT_DIR="my_outputs"
TINY="tiny"
OPTION="-live"

# Set the following parameter: 1 for Java, 0 for C/C++
IS_JAVA=1

DIFF_ARGS=-b
# **** Set correct library name below ****
JAVA_LIB_NAME=antlr-3.3-complete.jar

if [ $IS_JAVA -eq 2 ]; then
  	echo "Set IS_JAVA var in script first: 1 for Java, 0 for C/C++ (make sure you set the proper antlr library name for Java too)."
	echo "Read comments in the script for more details."
	exit
fi

# The commands below run from the following directory. So all locations need 
# to be addressed relative to this.
cd ./testcases/input
echo "Beginning tests. Now scanning for all .micro files in the folder \"/testcases/input\"..."

if [ ! -d ../$MY_OUTPUT_DIR ]; then
	mkdir ../$MY_OUTPUT_DIR
fi

for FILE in $( ls *.$extMicro )
do

#	FILE_IR=${FILE%"$extMicro"}${extIR}
	FILE_TINY=${FILE%"$extMicro"}${extTiny}

	echo "Generating tiny output for $FILE..."
	# Modify the following lines according to your exe requirements
	if [ $IS_JAVA -eq 1 ]; then
		# Handle Java case
		java -cp ../../lib/$JAVA_LIB_NAME:../../classes $JAVA_MICRO_EXE $FILE $OPTION  > ../$MY_OUTPUT_DIR/$FILE_TINY
	else
		#Handle C/C++ case
		../../build/$C_MICRO_EXE $FILE > ../$MY_OUTPUT_DIR/$FILE_TINY
	fi

# Use following command to do any diff operations
#	diff $DIFF_ARGS ../$myOutputdir/$FILE_TINY ../output/$FILE_TINY
	echo "Running $FILE_TINY..."
	../../$TINY ../$MY_OUTPUT_DIR/$FILE_TINY nostats

done

cd ../..
echo "Tests finished"

