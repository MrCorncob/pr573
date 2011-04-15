LIB_ANTLR := lib/antlr-3.3-complete.jar
ANTLR_SCRIPT := src/Micro.g

all: generate
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java generated/*.java 
	
generate:
	rm -rf generated
	mkdir generated
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(ANTLR_SCRIPT)	

group: 
	@echo ding34, cho111

clean:
	rm -rf classes generated testcases/my_outputs

.PHONY: all generate clean
