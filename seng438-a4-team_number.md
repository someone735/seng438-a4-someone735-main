**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:  28        |
| ----------------- |
| Student Names:      |
| John            |   
| Mark            |   
| Ron             |   
| Lana            |   

# Introduction
This lab assignment is primarily focused on mutation and learning selenium tools, building on the concepts introduced from the lecture slides. To assess mutation coverage in eclipse, our group used the PIT testing tool as part of the mutation testing process. Mutation testing was integrated to enhance the error detection within the original source code. The test cases are specifically designed to identify untested or vulnerable areas.

For part 2 of the lab, it is concentrated on GUI testing of websites through the use of the Selenium IDE extension. This portion of the assignment was to provide hands-on experience with automating test cases with an emphasis on the structure and user interface. By recording and executing these scripts within the Selenium IDE, we gained practice knowledge in automating and executing test cases and testing in general. We also gained insight on how to validate user interactions and ensure the consistency of the website. 



# Analysis of 10 Mutants of the Range class 
| Mutant [include where]        | Killed or Survived | Why |
| ----------------- | --| --|
|line 95, Range constructor, 3. incremented (a++) double local variable number 1 |survived |We did not have any test cases to ensure that the lower parameter would be the same after storing into Range’s lower variable |
| line 105, getLowerBound(), 1. replaced double return with 0.0d | killed | Since our test cases for this method were not hard code, they would respond accordingly to a change in the return value | 
| line 114, getUpperBound(), 2. replaced return of double value with -(x+1) | killed |Since the value would have changed from what is suppose to be true, our test case would have responded accordingly | 
| line 144, contains(), 3. changed conditional boundary | killed | Changing the boundary can mean turning a ≥ to a > or vise versa. Since we have a test case targeting the boundary case of the conditional, this mutant was killed |
| line 123, getLength(), 6. replaced double operation with first member | kill | Since we took into account that these values could be positive, negative, and zero, we created test cases for these scenarios, thus causing it to be dependent on both upper and lower variables. |
| line 132, getCentralValue(), 19. replaced double division with modulus  | kill | Since the return value of this method had to be the sum of half of the lower bound and half of the upper bound, any change in the arithmetic would be detected. |
| line 144, contains(), 1. replaced boolean return with false | survived |  Since we did not explicitly test this method, this mutant survived |
| line 157, intersects(), 3. removed conditional | kill  |Since we had a test case that tested when a range was fully separate from the other, by changing this condition to be false, the output would be incorrect. |
| line 161, intersects(), 3. changed conditional boundary  | survived |We did not have a test case that ensured that b0 < this.upper returned the correct response |
| line 188, constrain(), 2. Incremented (a++) double local variable number 1 | survived | We did not have a test cases that ensure that the value inputted was also the value being used |





# Report all the statistics and the mutation score for each test class
Base test classes
- Data Utilities: 88%
  
![image_2025-03-28_232656642](https://github.com/user-attachments/assets/5dc47d81-9c89-4d57-8347-e3bd1a677ee6)
- Range: 65%
  
![image_2025-03-28_232802347](https://github.com/user-attachments/assets/73db8f8f-c2f3-469a-b506-1ebbf73de661)

New test classes
Data Utilities: 90%, We are unable to increase the DataUtilities class any further as most of the mutants related to post increment and decrement of variables that we were unable to test for in addition to equivalence mutants. 

# Analysis drawn on the effectiveness of each of the test classes
 The RangeTest class was the most effective in detecting the errors in the Range class as its mutation score was initially low which allowed us to easily determine what required improvements. The DataUtilitiesTest already had a relatively high mutation score and due to most of the mutant test cases created to be untestable, our improvements could only increase the mutation score to its current point.

# A discussion on the effect of equivalent mutants on mutation score accuracy
 Equivalent mutants can really mess with the accuracy of a mutation score. After trying to increase the coverage, there are changes in the code that look different but actually behave the exact same way as the original code. There is no way for tests to catch them since they cause no change in behaviour. This actually gives the false impression that our test suite is not doing a good job but in reality it is, and thus it is a waste of time trying to increase the score caused by it. On top of that, figuring out which mutants are truly equivalent is not easy and takes a lot of effort and time. So, while mutation testing is super useful, equivalent mutants are one of its biggest disadvantages when it comes to scoring accuracy.

# A discussion of what could have been done to improve the mutation score of the test suites
 One of the ways to improve the mutation score in the test suites was to create new tests in covering methods that were implicitly tested as they were used in the other methods. Examples of this would be the contains method. In our assignment three RangeTest, we did not explicitly test the contains method but it was used in the constrain method. This meant that we did not fully test the contains method which then allowed the mutants to survive the original test cases. By creating test cases that specifically tested the contains method, we were then able to kill these mutants and increase the mutation score. Another way to improve the mutation score in the test suites was by creating test cases to ensure high instruction coverage. Examples of this would be the combineIgnoringNaN and how arguments are inputted. When observing the Range class code, it was found that a certain section of code in the min and max methods were not covered. Based on the section of code, it related to how the arguments were passed specifically how the second parameter would be NaN. By ensuring these sections of code were covered, it ultimately helped increase the mutation score. 

# Why do we need mutation testing? Advantages and disadvantages of mutation testing
By measuring how many mutants are killed, we can get insight into how well the tests actually detect real bugs. Even if test coverage tools show high coverage, the tests could still be weak. Mutation testing uncovers such weaknesses leading to more reliable tests.
Advantages:
Detects Gaps in Test Suits: it detects test cases that are not strong enough or do not cover certain logic
Enhance the Code Basic Coverage: since mutation testing measures whether the test can detect faulty logic and not only executing it, this helps us in increasing the code coverage.
Code Quality: improves overall code quality.
Disadvantages:
Setup and Tooling: requires setup of specific tools and can be hard to maintain.
Difficult to Analyze Reports: Mutation testing can generate a lot of reports and failure points, which can be difficult to interpret. 
Risk of False Positives: the possibility of equivalent mutants that don’t affect behaviour.


# Explain your SELENUIM test case design process
Choosing which website to use, we first explore each website to check the complexity and design as well as analyze all the different functionalities that are testable. We chose the GAP website as its complexity and the number of functions are well-suited for this part. After choosing which website, we listed as many functions that can be tested, for example, searching an item, navigating through the nav bar, adding to cart, removing item from cart, etc. From that list, we narrowed it down to some main functionality. We chose functions that require inputting/typing, and clicking. This way, we can test and validate the execution more effectively to see if the correct outcome and data values are ensured. 

# Explain the use of assertions and checkpoints
The usage of assertions and checkpoints (validations) are done automatically by Selenium and manually for some to make sure that the function is correctly implemented. Selenium adds validation after any form of user input (mouse click, typing, scrolling). Executing the test cases with these inputs, Selenium will make sure that the test will fail at that checkpoint if validation is failed. This is also the same when putting validation/checkpoints manually.  



# how did you test each functionaity with different test data
We began identifying and analyzing how the test would be recorded. Some of the tests required multiple verification and checks in one recording such as for removing product from cart, we had to add a product, check if it is there, then remove it and verify that it is removed by using “verify element not present”. As for the test involving gift card input, we had to test the text to verify that it did not work, and then with the correct promo code using “verify text”. Testing the shopping cart feature, we recorded individual tests for adding product, verifying its presence, removing, and then confirming its removal. This is a multi-step test, requiring multiple verification to ensure the accuracy of each step and avoid potential spiraling conflicts by chained actions. This testing approach through methodical testing, allowed us to test various functionality of the GAP website which can be reproduced

# Discuss advantages and disadvantages of Selenium vs. Sikulix
Selenium relies on HTML code locators to capture the user interactions. It captures actions such as scrolling, clicking, mouse hovers, etc. to map them to different HTML classes on the website. This is different when compared to Sikulix which uses image recognition to record and locate all the user interactions. 
	Selenium:
		Advantage: It automates web interactions using precise element targeting and allows recorded test to be replayed. 
		Disadvantage: limited to web-based applications and is not consistent when the page states are dynamic. 
	Sikulix: 
		Advantage: it uses image recognition which makes it compatible with many graphical interfaces other than web-based applications
		Disadvantage: Very sensitive to visual changes, any layout modification or image scaling could produce conflicts

# How the team work/effort was divided and managed
Since only one person was able to run Pitest on their machine, everyone else worked on selenium and the report.

Pitest - John

Selenium/report - Lana, Mark, Ron, John


# Difficulties encountered, challenges overcome, and lessons learned
Difficulties encountered during this assignment was that Pitest was not working on people’s machines and only one person can use it. Another difficulty was that during selenium test case designing, sometimes it would not do the right commands, it would sometimes just skip over some of them and not work at all. We overcame this by just designing a bunch of test cases and used the ones that worked. We learnt a big lesson during this lab, and it is that it is hard when only one computer can handle the Pitest, everyone is scrambling on what to do.

# Comments/feedback on the lab itself
This lab was pretty challenging and tough, the lab itself was pretty confusing due to the lack of information about Pitesting and Selenium. Overall, we hope that in the future, this lab will contain more information for students that take this course. Pitesting was really hard due to how the lab did not really explain it well. 

