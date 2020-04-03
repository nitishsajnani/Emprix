### RegAuth- Selenium Automation
---
#### System Requirement:

* JDK 1.8 or above
* Maven 3.2 or above
* Eclipse or IDE of choice in case there is need to update the script. (optional)
* For execution of scripts on Chrome or Internet explorer you need to have executable files for both drivers respectively and paste them at location "\src\test\resources\drivers" in project folder.

###Git use below commands
Git clone: to copy url from git hub
git add . : use for adding file in project
git commit -m : use for commiting the code with message
git push: use for pushing the code on github


#### Execution Steps
Please follow the instructions to execute the tests on local:

1. Checkout the code from github
2. Open terminal and navigate to the checkout directory
3. Checkout the refactor branch
  * git checkout refactor
4. According to the Test Scope use following commands
  - To Execute the complete test suite

	```clean test -DxmlFile=suite.xml  -DbrowserName=chrome```
  - To Execute the single test suite
    
	``` mvn clean integration-test -Dtest="Test suite name"```
    
#### Result Files:	
The Test Execution Results will be stored in the following directory once the test has completed

    ./target/test-output/emailable-report.html (for complete test suite)
    ./target/surefire-reports/emailable-report.html (for single test suite)

 #### Maven Jenkins and Git:   
 run over jenkins
 run command java -jar jenkins.war  
 Create maven project
 configure github with your credentials
 now give goals
 and then click now Build Now
 
 
    
    
    
