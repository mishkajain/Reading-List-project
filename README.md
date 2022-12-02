# Books and Reading Progress Tracking Application

This application allows a user to keep track of what books they are 
currently reading and the progress they have made in completing each book.
Some **features** include a system which allows a user to add or remove books 
to a list of books that they want to keep a track of and system where a user
can create general reading goals and book specific targets. 

Anyone who is looking for a way to keep track of what books they are reading,
how much of each book they have read, create reading lists, etc. can use this 
application. Any user, whether they are already an avid reader, or someone who is 
looking for a way to create and sustain better reading habits, can use and will 
benefit from this application. 

As an avid reader myself, I often find it difficult to keep track of which books 
I'm currently reading, which books I want to start reading and which books I've
finished reading. As much as I like to read, there are times when I don't read 
any of the books I started reading for pleasure for weeks on end. Having a system
which allows you to create a goal and displays how much of it I have completed and
how I can reach my target can be very helpful in creating better habits.

## User Stories

- As a user, I want to be able to add a book to my reading list 
- As a user, I want to be able to rate all the books from 1 to 5
- As a user, I want to be able to indicate my reading status for each book from 1 to 3 (1 = A new book; 2 = In progress; 3 = Completed)
- As a user, I want to be able to assign how many pages each book has
- As a user, I want to be able to view the list of books in my reading list
- As a user, I want to be able to remove a book from my reading list
- As a user, I want to be able to save my reading list to file
- As a user, I want to be able to load my reading list from file

### Phase 4 - Task 2
Thu Nov 24 12:40:30 PST 2022
Added book: 'Harry Potter' to Reading List


Thu Nov 24 12:40:49 PST 2022
Added book: 'Harry Potter 2' to Reading List


Thu Nov 24 12:40:55 PST 2022
Removed book: 'Harry Potter 2' from Reading List


Thu Nov 24 12:40:58 PST 2022
Saved Reading List to file

### Phase 4 - Task 3
- Refactor the GUI class in order to: 
  - reduce coupling between GUI class and the Book class (number of dependencies it has on the Book class)
  - reduce repetitive tasks by method refactoring (eg. a common method for the saveProgressBar and loadProgressBar 
  methods; the only difference between these methods is the message and image which pops up on the frame)
  - improve cohesion by splitting the class into several classes which have a single-focused purpose 
  (eg. creating a single-focused class for the addBookPanel or removeBookPanel, etc) 
  (perhaps also implement an abstract class for those several classes to extend so that the common functionality 
  can be shared, and we can avoid repetition within the code)

