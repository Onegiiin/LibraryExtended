# Small Library Application

This is a small library management application built using Spring MVC and JdbcTemplate with PostgreSQL. It includes entities such as **Person** and **Book**.

Requirements:
1) Pages for adding, changing, and deleting a person.
2) Pages for adding, changing, and deleting books
3) A page with a list of all people (people are clickable - clicking takes you to the person's page).
4) A page with a list of all books (books are clickable - clicking takes you to the book page).
5) The person's page, which shows the values of his fields and a list of books that he
took. If the person has not taken any books, instead of the list there should be the text "The person
has not taken any books yet".
6) The page of the book, which shows the values of the fields of this book and the name of the person
who took this book. If this book was not taken by anyone, the text "This book is free" should be there.
7) On the book page, if the book is taken by a person, there should be a "Release book" button next to his name. This button is pressed by the librarian when the reader
returns this book back to the library. After clicking on this button, the book becomes free again and disappears from the list of books of the person.
8) On the book page, if the book is available, there should be a drop-down list (<select>) with all the people and the "Assign book" button. This button is pressed by the librarian
when the reader wants to take this book home. After clicking on this button, the book should start to belong to the selected person and should appear in his book list.
9) All fields must be validated using @Valid and Spring Validator, if required.
