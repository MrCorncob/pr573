#!/bin/bash
INPUT="testcases/input"
OUTPUT="testcases/output"
RESULT="testcases/my_outputs"

ls $OUTPUT | sort -d
ls $RESULT | sort -d

ls -1 $OUTPUT | while read fn
do
	echo $fn
        opendiff $OUTPUT/$fn $RESULT/$fn
done
