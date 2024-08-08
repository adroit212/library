Library is a RESTful API to manage a library's book inventory and lending system.

System Specifications are:

Java: JDK-21
Spring-boot: 3.3.2
Database: MongoDB 7.0.12
Local Database port: 27017
Local Database name: admin
Server port: 8081
Documentation: Postman


The following endpoints are available for this software:
Please note that 8081 is the server port. You can choose to change this and use as configured on your computer.

-------------------------------------- Users Endpoints --------------------------------------
1.  Create user (For creating a single user record): 
    POST http://localhost:8081/users

    Request Body:
    {
      "fullName": "John Doe",
      "email": "john@email.com",
      "membershipDate": "2024-07-20"
    }
    
3.  Update user (For updating a single user record): 
    PUT http://localhost:8081/users/{userId}

    Request Body:
    {
      "fullName": "John Doe",
      "email": "john@email.com",
      "membershipDate": "2024-07-20"
    }
    
5.  Delete user (For deleting a single user record): 
    DELETE http://localhost:8081/users/{userId}
    
7.  Single user (For fetching single user record record): 
    GET http://localhost:8081/users/{userId}
    
9.  All Users (For fetching all users records):
    GET http://localhost:8081/users

-------------------------------------- Books Endpoints --------------------------------------
1.  Add book (For creating a single book record): 
    POST http://localhost:8081/books

    Resquest Body:
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "publishedYear": 1925,
      "isbn": "9780743273565",
      "copiesAvailable": 5
    }
    
3.  Update book (For updating a single book record): 
    PUT http://localhost:8081/books/{bookId}

    Resquest Body:
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "publishedYear": 1925,
      "isbn": "9780743273565",
      "copiesAvailable": 5
    }

5.  Delete book (For deleting a single book record): 
    DELETE http://localhost:8081/books/{bookId}
    
6.  Single book (For fetching single book record): 
    GET http://localhost:8081/books/{bookId}

7.  All Books (For fetching all books records):
   GET http://localhost:8081/books

-------------------------------------- Loans Endpoints --------------------------------------
1.  Borrow book (For creating a single loan record): POST http://localhost:8081/loans
    POST http://localhost:8081/loans

    Request Body:
    {
      "bookId": "66b36655e8bf001962705503",
      "userId": "66b364bee8bf001962705501",
      "loanDate": "2024-08-07"
    }
    
3.  Return book (For updating returnDate of a single loan record and marking it as returned): 
    PUT http://localhost:8081/loans/{loanId}/{returnDate}

4.  Single Loan (For fetching single loan record): 
    GET http://localhost:8081/loans/{loanId}

5.  All Loans (For fetching all loan records): 
    GET http://localhost:8081/loans
    
7.  User Loans (For fetching all loan record for a given user):
    GET http://localhost:8081/loans/user?userId=

NOTE: The application.properties file of this software can be modified according to what you have on your personal computer

