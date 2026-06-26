# ExamGrade System

A full-stack online examination and quiz management system built using **Java, Spring Boot, MySQL, HTML, CSS, Bootstrap, and JavaScript**.

## Overview

ExamGrade is a web-based platform that allows teachers to create and manage quizzes while enabling students to attempt exams online and view their results. The system provides role-based access for teachers and students with secure authentication using JWT.

---

## Features

### Teacher Module

* Teacher Registration & Login
* JWT Authentication & Authorization
* Create New Quizzes
* Add Multiple Questions
* Add Multiple Choice Options
* Mark Correct Answers
* View Created Quizzes
* Manage Quiz Details

### Student Module

* Student Registration & Login
* View Available Quizzes
* Search Quizzes
* Attempt Online Exams
* Navigate Between Questions
* Submit Quiz Responses
* Automatic Result Calculation

### Security

* Spring Security
* JWT Authentication
* Role-Based Access Control
* Protected APIs

---

## Technology Stack

### Backend

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication
* Maven

### Frontend

* HTML5
* CSS3
* Bootstrap 5
* JavaScript (Vanilla JS)

### Database

* MySQL

---

## Project Structure

src
│
├── controller
│   ├── AuthController
│   ├── TeacherQuizController
│   └── StudentQuizController
│
├── service
│   ├── AuthService
│   ├── TeacherQuizService
│   └── StudentQuizService
│
├── repository
│   ├── UserRepository
│   ├── QuizRepository
│   ├── QuestionRepository
│   └── OptionRepository
│
├── entity
│   ├── User
│   ├── Quiz
│   ├── Question
│   └── Option
│
├── dto
├── security
└── config


---

## Database Schema

### Users

sql
users
-----
id
name
email
password
role
department
subject
created_at


### Quizzes

sql
quizzes
-------
id
title
subject
description
class_group
total_marks
duration_minutes
passing_score
created_by
created_at


### Questions

sql
questions
---------
id
question_text
quiz_id


### Options

sql
options
-------
id
option_text
correct
question_id


---

## API Endpoints

### Authentication

http
POST /api/users/register
POST /api/users/login


### Teacher APIs

http
POST /api/teacher/quizzes
GET  /api/teacher/quizzes
GET  /api/teacher/quizzes/{id}


### Student APIs

http
GET  /api/student/quizzes
GET  /api/student/quizzes/{id}
POST /api/student/quizzes/{id}/submit


---

## Installation

### Clone Repository

bash
git clone  https://github.com/poonamkumari24/examgrade.git
cd examgrade


### Configure Database

Create a MySQL database:

sql
CREATE DATABASE examgrade;


Update application.properties :

properties
spring.datasource.url=jdbc:mysql://localhost:3306/examgrade
spring.datasource.username=user
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update


### Build Project

bash
mvn clean install


### Run Application

bash
mvn spring-boot:run


Application runs on:http://localhost:8080


---

## Future Enhancements

* Quiz Timer
* Result Analytics Dashboard
* PDF Report Generation
* Leaderboard
* Question Bank
* Exam Scheduling
* Email Notifications

---

## Author

**Poonam Kumari**

Java Backend Developer | Spring Boot Developer

---

## License

This project is created for educational and learning purposes.
