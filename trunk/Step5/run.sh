#!/bin/bash
INPUT="testcases_5/input"
OUTPUT="testcases_5/output"
RESULT="testcases_5/result"
COMP="testcases_5/compare"

ls -1 $INPUT | sort -d | while read f 
do
	echo $f 
	java -cp lib/antlr-3.3-complete.jar:classes Micro $INPUT/$f > $OUTPUT/$f.out
done

ls $OUTPUT | sort -d
ls $COMP | sort -d

ls -1 $OUTPUT | while read fn
do
	echo $fn
        diff $OUTPUT/$fn $COMP/$fn > $RESULT/$fn
	opendiff $OUTPUT/$fn $COMP/$fn
done
