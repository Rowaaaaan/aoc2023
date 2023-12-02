OUTDIR = .\target
OUTPUT = day1-1.0-SNAPSHOT-jar-with-dependencies.jar
MAVEN ?= mvn

.PHONY: all clean run
	
all:
	mvn clean package
	
run:
	java -jar ${OUTDIR}\${OUTPUT} input.txt

clean:
	mvn clean