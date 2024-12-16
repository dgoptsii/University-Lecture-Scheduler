**University Lecture Scheduler**
University Lecture Scheduler is a RESTful API built with Spring Boot, designed to manage and schedule university lectures efficiently. This application allows for user registration, authentication, and the management of lecture schedules for students and teachers.

**Features**

•	User Management:

  -	Register new users (students and teachers).
  
  -	Update user details and passwords.
  
  -	Authenticate users with roles (e.g., ADMIN, TEACHER, STUDENT).
  
  
•	Lecture Scheduling:

  -	Create, update, and delete lecture schedules.

  -	Assign lectures to specific classrooms and time slots.

  -	View and manage the schedules of different users.

•	Administrative Functions:

  -	Admin users can add and manage teachers.

  -	Secure endpoints with role-based access control.


**Technology Stack**

•	Backend:

  -	Spring Boot for building the REST API.

  -	Spring Data JPA for database interaction (MySQL).

  -	Spring Security for authentication and authorization.

•	Testing:

  -	JUnit and Mockito for unit and integration tests.

  -	Spring Boot Test for context loading and MockMvc for web layer testing.
