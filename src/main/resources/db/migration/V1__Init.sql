CREATE TABLE Student(
    id int primary key,
    name varchar(25) NOT NULL,
    surname varchar(25) NOT NULL,
    middleName varchar(25),
    birthdate date,
    studentGroup varchar(6)
);