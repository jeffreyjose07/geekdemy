# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.  Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.geektrust.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.com)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)

#Best Practices
You can read our best practices [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#best-practices)
Also check out our code quality tools [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#code-quality-tools)
Also make sure to follow these guides to write clean code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-clean-code)
Also make sure to follow these guides to write testable code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-testable-code)
Also make sure to follow these guides to write secure code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-secure-code)
Also make sure to follow these guides to write reusable code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-reusable-code)
Also make sure to follow these guides to write maintainable code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-maintainable-code)
Also make sure to follow these guides to write efficient code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-efficient-code)
Also make sure to follow these guides to write DRY code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-dry-code)
Also make sure to follow these guides to write KISS code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-kiss-code)
Also make sure to follow these guides to write SOLID code [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#write-solid-code)

#Getting Badges
Badges are given as a part of the submission process. You can find the details [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#badges)
Here are guides to get badges [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java#get-badges)
Here are articles to get OOPs badges [here](https://help.geektrust.com/article/4-earning-oops-badge)
Here are articles to get readability [here](https://help.geektrust.com/article/36-earning-readability-badge)
Here are articles to get unit tests [here](https://help.geektrust.com/article/39-earning-tests-badge)
Here are articles to get maintainability [here](https://help.geektrust.com/article/39-earning-tests-badge)
