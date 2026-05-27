# CineDevoto — Sistema de Gestión de Cine

Aplicación fullstack para la gestión de un cine, desarrollada con Spring Boot en el backend y React en el frontend.

---

## 🚀 Tecnologías

**Backend**
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

**Frontend**
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![CSS](https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white)

---

## 📋 Funcionalidades

- 🎥 Gestión de películas (ABM)
- 🕐 Gestión de funciones y horarios
- 🪑 Reserva de entradas
- 👤 Gestión de usuarios

---

## ⚙️ Cómo correrlo localmente

### Requisitos
- Java 17+
- Node.js 18+
- MySQL

### Backend
```bash
# Clonar el repositorio
git clone https://github.com/RamiroAlonso2003/CineDevoto.git

# Configurar la base de datos en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinedevoto
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

# Correr el proyecto
./mvnw spring-boot:run
```

### Frontend
```bash
cd visualizador
npm install
npm start
```

---

## 📁 Estructura del proyecto
