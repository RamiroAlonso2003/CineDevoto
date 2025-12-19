# 🎬 Sistema de Reserva de Películas – Backend

## 📌 Descripción

Este proyecto consiste en el desarrollo de un **sistema backend para un servicio de reserva de películas**. El sistema permite a los usuarios registrarse, iniciar sesión, buscar películas, consultar horarios de exhibición y **reservar asientos para funciones específicas**. Además, los usuarios pueden administrar sus reservas y los administradores pueden gestionar películas, horarios e informes.

El foco principal del proyecto es **comprender y aplicar lógica de negocio compleja**, especialmente relacionada con la **reserva de asientos**, el **modelado de datos**, las **relaciones entre entidades** y la realización de **consultas complejas**.

---

## 🎯 Objetivo

El objetivo de este proyecto es:

* Diseñar un sistema backend realista
* Implementar lógica de negocio compleja (reservas y programación)
* Pensar correctamente el modelo de datos relacional
* Evitar problemas como el overbooking
* Trabajar con consultas complejas y reportes

---

## 👥 Autenticación y autorización de usuarios

* Los usuarios deben poder **registrarse e iniciar sesión**.
* El sistema debe manejar **roles de usuario**:

  * **Administrador**
  * **Usuario regular**
* Los administradores pueden:

  * Administrar películas
  * Administrar horarios de exhibición
  * Ver informes y reservas
  * Promover otros usuarios a administradores
* Los usuarios regulares pueden:

  * Buscar películas
  * Ver horarios de exhibición
  * Reservar asientos
  * Ver y cancelar sus reservas futuras

El administrador inicial puede crearse utilizando **datos iniciales (seed)**.

---

## 🎬 Gestión de películas

Los administradores deben poder:

* Agregar películas
* Actualizar películas
* Eliminar películas

Cada película debe contar con:

* Título
* Descripción
* Imagen de póster
* Género

Las películas deben tener **horarios de exhibición asociados**.

---

## 🕒 Gestión de horarios de exhibición

* Cada película puede tener uno o más horarios de exhibición.
* Los horarios representan funciones específicas en una fecha y hora determinadas.
* El sistema debe permitir consultar las funciones disponibles para una fecha específica.

---

## 💺 Gestión de reservas

Los usuarios deben poder:

* Consultar películas y sus horarios para una fecha específica
* Ver los asientos disponibles para una función
* Seleccionar y reservar uno o más asientos
* Ver sus reservas
* Cancelar únicamente reservas futuras

Los administradores deben poder:

* Ver todas las reservas
* Consultar la capacidad
* Consultar ingresos

---

## ⚠️ Consideraciones de implementación

Durante el desarrollo del sistema se debe pensar en:

* El **modelo de datos** y las relaciones entre entidades
* Cómo **evitar el overbooking** de asientos
* Cómo gestionar correctamente las **reservas concurrentes**
* La programación y validación de horarios de exhibición
* La generación de **informes sobre reservas**
* La correcta **autenticación y autorización** de los usuarios

---

## 🗄 Base de datos

Se recomienda el uso de una **base de datos relacional** como:

* PostgreSQL
* MySQL

---

## 📈 Posibles extensiones

Una vez completado el proyecto base, se pueden agregar funcionalidades adicionales como:

* Procesamiento de pagos
* Notificaciones por correo electrónico

---

## 🧠 Nota final

Este proyecto es intencionalmente complejo y abierto en su diseño. Al completarlo, se obtiene una comprensión sólida de:

* Lógica empresarial compleja
* Modelado de datos relacional
* Manejo de concurrencia
* Consultas complejas

Es un proyecto ideal para fortalecer habilidades backend y de diseño de sistemas.
