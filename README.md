For this Assignment there are total 6 files present.
TestRunner.java - It is used to run the test cases
demo.feature - It contains all the feature lines for test cases
DemoStepDef.java - It contains the actual code which is linked with feature file
ReadExcelData.java - This file contains code to read excel data by feeding one row at a time
ChromeDriver.exe - As path has to be set for driver everytime and it would be easier for anyone to just clone the repo and execute test cases directly instead of setting the path first
propertyDetails.xlsx - Total 5 columns are included which were needed in execution of testcases which are Type, City, BHK, Locality 1, Locality 2

I have written the code using intelliJ Idea IDE using Java, Cucumber, Gherkin, Selenium, JUnit.
If you want to trigger the pack you can directly clone the repositary from my public repositary and trigger /execute the test cases using the TestRunner file.
I have used rowNumber in feature file so you can edit the excel file (propertyDetails) and accordingly add more rows in excel and then you can add the rowNumber in feature file
as 2,3,4,.... so on.

propertyDetails.xlsx, ChromeDriver.exe, demo.feature are located in resources folder
TestRunner.java, ReadExcelData.java, DemoStepDef.java are located in Java folder
Both resources and Java are located in src/test folder