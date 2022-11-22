# Card Game for ECM2414

## Run the program in CLI
1. Navigate to `src` folder
2. `javac CardGame.java`
3. `java CardGame`
#### or
1. Navigate to folder with CardGame.jar (This is necessary to have the output files appear in correct directory)
2. `java -jar CardGame.jar`

## Testing on Mac OS terminal
1. Download [junit-platform-console-standalone-1.9.1](https://search.maven.org/remotecontent?filepath=org/junit/platform/junit-platform-console-standalone/1.9.1/junit-platform-console-standalone-1.9.1.jar)
2. Navagate to `src` folder from code 
3. `javac CardGame.java`
4. `javac -cp .:{PATH_TO/junit-platform-console-standalone-1.9.1.jar} CardGameTestSuite.java`
5. `java -jar {PATH_TO/junit-platform-console-standalone-1.9.1.jar} --class-path . --scan-class-path`

Note: Should work the same on Windows (untested) - Need to download the JUnit4

## Authors
- Sam Tebbet - 710046012
- Rhys Broughton - 710043307