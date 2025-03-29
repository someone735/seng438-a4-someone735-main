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
see provided document for analysis table

# Report all the statistics and the mutation score for each test class
see provided document for analysis table

# Analysis drawn on the effectiveness of each of the test classes
 	The RangeTest class was the most effective in detecting the errors in the Range class as its mutation score was initially low which allowed us to easily determine what required improvements. The DataUtilitiesTest already had a relatively high mutation score and due to most of the mutant test cases created to be untestable, our improvements could only increase the mutation score to its current point.

# A discussion on the effect of equivalent mutants on mutation score accuracy
Equivalent mutants can really mess with the accuracy of a mutation score. After trying to increase the coverage, there are changes in the code that look different but actually behave the exact same way as the original code. There is no way for tests to catch them since they cause no change in behaviour. This actually gives the false impression that our test suite is not doing a good job but in reality it is, and thus it is a waste of time trying to increase the score caused by it. On top of that, figuring out which mutants are truly equivalent is not easy and takes a lot of effort and time. So, while mutation testing is super useful, equivalent mutants are one of its biggest disadvantages when it comes to scoring accuracy.

# A discussion of what could have been done to improve the mutation score of the test suites


# Why do we need mutation testing? Advantages and disadvantages of mutation testing

# Explain your SELENUIM test case design process

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
