# 🩺 MediTrack

- MediTrack is a demo application that helps track patients, prescribe their medications, schedule appointments, and manage other medical records. It is built with Spring Boot and PostgreSQL, utilising Docker and Gradle.
- It demonstrates my ability to design and implement backend systems with clean architecture, readable and neat code, REST APIs, and relational database integration. It also showcases my ability to think like a product manager and a designer while I come up with use-cases for this application.

## 💡 Inspiration
- A childhood friend of mine is currently a dermatologist, running a small clinic in my hometown. He wanted to track his patients, their appointments, remind them of appointments and prescribe them medicines in a printable PDF. There are a few applications available in the market for these use cases, but most of them are priced at premium fees and only consumed by high-end hospitals. I am developing this application for individual doctors or small hospitals that cannot afford high-end premium applications.

## 💻 Skills I showcase
- Hexagonal Ports-Adapter Architecture for code maintenance
- Gradle based development
- Docker based setup for Postgres DB
- Iterative use-case driven commits to Github
- Flyway based migrations for Postgres
- Exception handling
- Building and coding iteratively
- Swagger based UI to test endpoints
- Implemented Google SMTP to send emails

## 🚀 Highlights and features

1. JWT token-based account creation of doctors, nurses and other hospital staff within the same application.
2. Patient and prescriptions record management (search, add, update) to Postgres
3. Medicine prescriptions with dates, generate PDFs
4. Sending mails with prescriptions to the patients using Google SMTP (Further expandable to WhatsApp/SMS using Twilio-like APIs)

## Future scope

1. Send Appointment reminders to patients
2. Role-based restrictions on patients' data
3. Upload and view the lab reports of a patient

## 🛠️ Tech Stack

- Backend: Spring Boot (REST APIs, JPA, JWT-Security)
- Database: PostgreSQL (Flyway based migrations)
- Build Tool: Gradle
- Extras: Lombok, Swagger/OpenAPI, Docker, OpenPDF

## 🎥 Demo

▶️ Watch API Demo Video

[![MediTrack Demo](https://img.youtube.com/vi/cQfzTUjsNIE/0.jpg)](https://www.youtube.com/watch?v=cQfzTUjsNIE)

## 📌 Notes

This is a demo project to showcase my backend development skills.
Currently not production ready. First deployment will be to a single user (my doctor friend).
May evolve into a more robust healthcare cloud app in the future.

## 🌱 Future Roadmap

- Frontend (JavaFX/React)
- Automated reminders (SMS/Email/Push)
- Cloud deployment with Docker/Kubernetes

## 👤 Author

Yash Jaiswal
- 💼 Software Engineer
- 🌍 Backend Software Engineer with 7+ years of experience in Spring Boot, REST APIs & microservices
- https://www.linkedin.com/in/yash-jaiswal/
