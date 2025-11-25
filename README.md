# Project-02-Android-App

We will work with a team to create an android application.

Project Goals and Requirements
The primary goal of this project is to practice designing, implementing, and testing an Android application. The focus is more on the process than the final product. The instructor is evaluating design skills and teamwork abilities.
The project will make use of various software development practices and tools, including:
Design documents
Android Room persistence
GitHub (for version control and collaboration)
Intent factories
Unit tests
And more!

Part 1: The Plan
Create a plan for the development of your application. There are several example projects listed below to give an idea of scope and style however you may create your own project. 
All projects MUST meet the requirements laid out in this document.
Github repository and Github issues
Requirement: A public github repository with all teammates as contributors

One member of your team will create a public github repository and invite the rest of the team to contribute. 
Steps:
Create an Android project on your local machine
Create, commit and push, a .gitignore file
Please use either gitignore.io or install the JetBrains plugin
Make note of what OSs your Teammates are using. 
Invite the rest of the team to contribute
The team will pull the repo onto their machines

Design Document
Requirements: A pdf of the initial design document
Each team will create and submit a design document. The design document will be based on this example. The document must include 6-8 use cases and the following:

The initial document must address the following:
What is the application?
Title and short description.
6-8 use cases
An entity relationship diagram showing what tables will be used
A testing plan
Mockups of the app
This can be done by creating layouts in Android Studio, using Draw.io, drawing on a tablet (if you are fortunate enough to have one), paper, or a whiteboard.
A link to a github repository where the initial project has been published
The repo must be public. I am aware this means other students can see it but each project should be unique. Given the nature of this project it is VERY obvious if someone copies from another student. I want the repos to be public because then it will serve as a really nice portfolio piece for future job applications. 🙂
Everyone working on the project must be a contributor
Github issues
Requirements: at least 2 issues PER use case. 
Create github issues with the initial steps that will be taken to get started. These issues must be assigned to teammates and must be SMART. 

Examples:

Good example
Create the POJO,UnitTests, and documentation (JavaDoc) that represents Users by the end of the first sprint. The POJO will follow the ERD and have three fields, Name, LastAccessDate, and Password.

Bad Example
Make some tables.

Part 2:The Database and Initial Activities
At this point the application should be well under way. The database should be working and each teammate should have at least one activity merged to main. Note that the database does not need to be COMPLETE but getting the database working should be the top priority. If the project consumes an API that should be the next priority.

Requirements:
A complete design document with updated user stories
Updated diagrams in the document including accurate ERDs
Each teammate must have at least one branch merged into main
Each teammate must have at least one test 
Either a UI test (espresso) or a unit test
Test the database 
The Database should be functional

Part 3: The Test
This is an assignment where other people use your application.  You must have a working build at this point. A working build != to a complete build.  There must be some functionality, preferably an aspect of the core functionality.

Requirements:
Have a running application
Review the work of other teams

Part 4: The Application
This is the final submission for project 2. The main feature of part 3 is a video walkthrough of the application. I want a video for several reasons. The first, and most important, is that (as you have no doubt discovered) Android is FINICKY, what works on your system may not work on mine. By submitting a video I get to see the application working as it is expected to work. The second, and only slightly less important, reason is it makes grading go faster. 🙂

There is also a presentation. You will present your work to the class.
What should be included in the video/presentation
Highlight which rubric items have been completed in the project. The easiest way to do this is to literally highlight the rubric items. Share the rubric on screen and tell me what you have done. 
Suggested video outline
Show the rubric with all the completed items highlighted.
Walk through each rubric with each teammate explaining what they contributed
I especially want to see:
Persistence, FORCE QUIT the application and show persistence. 
Completed issues
Each teammate should have at least three or four
Unit tests
Part 3 Deliverables

The above mentioned video
Youtube links are preferred but you may also submit a video file saved to a Google drive
A .zip file containing the COMPLETE project
A link on how to do this.
A .pdf with all of the required items.

Example applications
You may use any of these ideas as an application or as a starter for your application. 

All four of these applications would display an initial screen prompting the user to either log in or sign up. This means that all four would have a 'users' table with fields for userId, username, password, and isAdmin. The user table may have more fields but those three would be the minimum.
Ecommerce Applications
A video walkthrough of an ecommerce app
NO Airline Tracking application
Grade/Assignment tracker
An application that allows users to enter, track, and update their grades for a class. This would have three levels of user:
Normal users would be able to accept assignments and view grades. 
Teacher users would be able to create and grade assignments
Teachers would also have all the abilities of a normal user.
Admin users would be able to create users and courses.
Admins would also have all the abilities of a normal user.
Legally Distinct Pocket Monster Arena
A game that allows users to train creatures and have them fight other creatures.

Normal users would be able to choose creatures, train creatures, and battle other creatures
Admin users would be able to create users, creatures, and arenas
Admins would also have all the abilities of a normal user
Trivia Game / Study guide
Users are presented with a series of questions with randomized multiple choice answers. 
Scores are recorded and displayed on a leaderboard. Individual users may also view their performance on past questions (in/correct, number of times guessed, etc). 

Admin users would have all the features of a normal user plus the ability to create new questions and categories. 

