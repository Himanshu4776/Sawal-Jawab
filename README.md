# Sawal-Jawab

A modern social Q&A platform built with React + TypeScript frontend and Spring Boot backend.

## Overview

Sawal-Jawab is a question-and-answer platform where users can:
- Post questions
- Answer others' questions
- Vote on questions and answers
- Filter questions by categories and trending topics
- Track their own contributions

## Getting Started

### You can check the backend service live at: 
https://amusing-bravery-production.up.railway.app/

### Postman Collection here:
https://www.postman.com/docking-module-cosmonaut-51576673/workspace/sawal-jawab-workspace

## Tech Stack

### Backend
- Spring Boot
- Spring Security with JWT
- JPA/Hibernate
- MySQL Database


### Frontend
- React 18 with TypeScript
- Vite for build tooling
- Tailwind CSS for styling
- Shadcn UI components
- JWT authentication

## Features

- **Authentication**
  - User registration
  - JWT-based login
  - Secure password handling

- **Questions**
  - Create questions with title and content
  - View all questions
  - Filter questions by category
  - Sort by most voted
  - Personal question tracking

- **Answers**
  - Post answers to questions
  - Vote on answers
  - Track answer history

- **Categories**
  - Organize questions by topics
  - Trending categories
  - Category-based filtering

#### API Endpoints
Auth
```
POST /user/register - Register new user
POST /user/login - User login
```
Questions
```
GET /questions/all - Get all questions
POST /questions/save - Create new question
GET /questions/{id} - Get question by ID
```

Categories
```
GET /categories - Get all categories
POST /categories - Add new category
```

### If you want to run it via local server, please read below.

### Prerequisites
- Node.js 16+
- Java 17+
- MySQL

###Backend Setup
```
cd SawalJawab
./mvnw spring-boot:run
```

### Frontend Setup
```bash
cd Sawal-Jawab-frontend
npm install
npm run dev
```

### Create a .env file in the frontend directory:
```
VITE_API_URL=http://localhost:8080/api
```
### Contributing
- Fork the repository
- Create your feature branch
- Commit your changes
- Push to the branch
- Open a Pull Request


### License
MIT License
