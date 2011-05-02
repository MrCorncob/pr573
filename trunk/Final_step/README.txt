<ECE 573 Project Final>

Ning Ding
Hyun Dok Cho

1. Expected Behavior of Your Makefile
  1.1 Printing your (and your partner's) ID
		$ make group
  1.2 Cleaning all generated files
		$ make clean
  1.3 Building your compiler
		$ make all

2. Expected Behavior of Your Compiler - make output
	For with no liveness analysis
 		$ java -cp lib/antlr-3.3-complete.jar:classes Micro "path_to_your_testcases/test.micro" > "path_to_your_testcases/my_output/test.nolive"
	For with liveness analysis
 		$ java -cp lib/antlr-3.3-complete.jar:classes Micro "path_to_your_testcases/test.micro" -live > "path_to_your_testcases/my_output/test.live" 

3. Run tiny
	For with no liveness analysis
		$tinyR "path_to_your_testcases/my_output/test.nolive
	For with liveness analysis
		$tiny "path_to_your_testcases/my_output/test.live
	
4. test_live.sh and test_nolive.sh is included.
