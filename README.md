# Clyde – Educational Platform

Clyde is an educational platform designed to streamline interaction between teachers and students, built with modern, secure, and scalable technologies.

---

## 🚀 Features

- Upload and access to class materials (PDFs, links, etc.)
- Task creation with deadlines
- Submission of assignments by students
- Grading system with feedback
- Role-based access: **Teacher** and **Student**
- Classroom management via enrollment codes
- Activity history tracking

---

## 🛠️ Tech Stack

### Backend
- **Java 21** + **Spring Boot**
- Spring Security with **JWT Authentication**
- Spring Data JPA with **MySQL**
- DTO mapping and clean architecture structure

### Frontend
- **React Native** (Mobile-first experience)

---

## 🧱 Architecture

This project follows a modular, clean architecture:

```
com.clyde
├── config              # Global configuration (CORS, Swagger, Security)
├── domain              # Core business entities, enums, DTOs and repository interfaces
├── application         # Business logic (services, mappers)
├── infrastructure      # Security & persistence implementations (JWT, DB adapters)
├── presentation        # REST Controllers
└── exception           # Custom exception handling
```

---

## 🧪 Running Locally

1. **Clone the repository**  
```bash
git clone https://github.com/coder-gabrielsantos/clyde.git
```

2. **Set your database configs** in `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clyde
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the app**
```bash
./mvnw spring-boot:run
```

---

## 🔐 Authentication

The platform uses **JWT (JSON Web Tokens)** for secure access.  
Endpoints are protected based on user roles (`STUDENT`, `TEACHER`).

---

## 📌 Roadmap

- [x] Define core architecture
- [x] Setup project with Git and Maven
- [x] Implement authentication system
- [ ] Create classroom module
- [ ] Enable file upload for materials and submissions
- [ ] Build full mobile app in React Native
- [ ] Add notifications and calendar integration

---

## 🙌 Contributing

Wanna help make Clyde better?

1. Fork the repo
2. Create a feature branch
3. Submit a pull request with a clear description

---

## 📄 License

This project is open-source under the MIT License.

---

> Developed with 💻 and ☕ by Gabriel Santos
