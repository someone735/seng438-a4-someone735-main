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

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
