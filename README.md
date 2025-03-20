### Escuela Colombiana de Ingeniería

### Arquitectura Empresarial - AREP

# Taller de Microservicios

En este taller se diseñó y desarrolló una API junto con un monolito en Spring Boot que permite a los usuarios realizar publicaciones de hasta 140 caracteres, registrándolas en un flujo único de posts. La aplicación incluye entidades principales: Usuario y Post-Hilo (Stream).

Además, se creó una aplicación en JavaScript para consumir el servicio, la cual fue desplegada en AWS S3, permitiendo su acceso público a través de internet. Tras verificar el correcto funcionamiento de la aplicación web, se implementó seguridad mediante JWT. Finalmente, el servicio fue desplegado en AWS Lambda, garantizando su disponibilidad y escalabilidad en la nube.

---

## Tabla de Contenido

1. [Introducción](#introducción)
2. [Instalación](#instalación)
3. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
   - [Estructura del Directorio](#estructura-del-directorio)
   - [Capas y Diseño del Proyecto](#capas-y-diseño-del-proyecto)
4. [Reporte de Pruebas](#reporte-de-pruebas)
5. [Diagrama del Proyecto](#diagrama-del-proyecto)
6. [Video del AREP-Twitter](#video-del-arep-twitter)
7. [Autores](#autores)

---

## Instalación

**1.** Clonar el repositorio

```bash
  git clone https://github.com/MiltonGutierrez/AREP-TALLER-7.git

  cd AREP-TALLER-7/local_only
```

**2.** Construir el proyecto mediante maven, donde debes tener previamente instalado este https://maven.apache.org . Luego pruebe el siguiente comando para compilar, empaquetar y ejecutar.

```bash
  mvn clean install
  mvn package
```

**3.** Ejecuta el proyecto con el siguiente comando:

```bash
mvn spring-boot:run

```

**4.** Una vez este corriendo la aplicación prueba los siguiente:

- **Página Principal:**

```bash
  http://localhost:8080
```

## Arquitectura del Proyecto

### **Estructura del directorio**

El directorio del proyecto esta organizado de la siguiente manera:

```plaintext
src
└── main
    ├── java
    │   └── edu.escuelaing.arep.arep_taller_7
    │       ├── controller
    │       │   ├── auth
    │       │   │   ├── PostController
    │       │   │   ├── PostControllerImpl
    │       │   │   ├── UserController
    │       │   │   ├── UserControllerImpl
    │       │
    │       ├── dto
    │       │   ├── LoginDto
    │       │   ├── PostDto
    │       │   ├── TokenDto
    │       │   ├── UserDto
    │       │
    │       ├── exception
    │       │   ├── InvalidCredentialsException
    │       │   ├── PostException
    │       │   ├── TokenExpiredException
    │       │   ├── UserException
    │       │
    │       ├── model
    │       │   ├── PostEntity
    │       │   ├── UserEntity
    │       │
    │       ├── repository
    │       │   ├── PostRepository
    │       │   ├── UserRepository
    │       │
    │       ├── security
    │       │   ├── JwtConfig
    │       │   ├── JwtRequestFilter
    │       │   ├── JwtUserDetailsService
    │       │   ├── JwtUtil
    │       │   ├── SecurityConfiguration
    │       │
    │       ├── service
    │       │   ├── PostService
    │       │   ├── PostServiceImpl
    │       │   ├── UserService
    │       │   ├── UserServiceImpl
    │       │
    │       ├── ArepTaller7Application
    │       ├── StreamLambdaHandler
    │
    ├── resources
    │   ├── static
    │   │   ├── css
    │   │   │   ├── auth.css
    │   │   │   ├── styles.css
    │   │   ├── js
    │   │   │   ├── aplicient.js
    │   │   │   ├── auth.js
    │   │   │   ├── twitter.js
    │   │   ├── index.html
    │   │   ├── twitter.html
    │   │
    │   ├── application.properties
```

### **Capas y diseño del Proyecto**

El proyecto está diseñado siguiendo un sistema de capas que organiza la lógica y responsabilidades del sistema. Estas capas son las siguientes:

#### 1. Capa de Presentación (Frontend)

- **Descripción:** Esta capa se encarga de la interacción con el usuario final. Proporciona la interfaz gráfica para que los usuarios puedan interactuar con el sistema.
- **Componentes:**
  - Archivos HTML, CSS y JavaScript ubicados en `src/main/resources/static`.
- **Responsabilidad:**
  - Mostrar formularios de autenticación y creación de posts.
  - Enviar solicitudes HTTP al backend usando `fetch`.
  - Renderizar los datos obtenidos del backend en la interfaz gráfica.

---

#### 2. Capa de Controladores (Controller)

- **Descripción:** Esta capa actúa como intermediaria entre la capa de presentación y la lógica de negocio. Expone los endpoints REST para que el frontend pueda interactuar con el backend.
- **Componentes:**
  - Controladores ubicados en `src/main/java/edu/escuelaing/arep/arep_taller_7/controller`.
- **Responsabilidad:**
  - Recibir solicitudes HTTP del frontend.
  - Validar y procesar los datos de entrada.
  - Delegar la lógica de negocio a los servicios correspondientes.
  - Retornar respuestas HTTP al frontend.

---

#### 3. Capa de Servicios (Service)

- **Descripción:** Contiene la lógica de negocio del sistema. Implementa las reglas y operaciones necesarias para cumplir con los requisitos funcionales.
- **Componentes:**
  - Servicios ubicados en `src/main/java/edu/escuelaing/arep/arep_taller_7/service`.
- **Responsabilidad:**
  - Implementar las reglas de negocio.
  - Validar datos y aplicar restricciones.
  - Interactuar con la capa de repositorios para acceder a la base de datos.

---

#### 4. Capa de Persistencia (Repository)

- **Descripción:** Se encarga de la interacción con la base de datos. Utiliza JPA/Hibernate para realizar operaciones CRUD sobre las entidades.
- **Componentes:**
  - Repositorios ubicados en `src/main/java/edu/escuelaing/arep/arep_taller_7/repository`.
- **Responsabilidad:**
  - Realizar consultas y operaciones en la base de datos.
  - Persistir y recuperar entidades como `UserEntity` y `PostEntity`.

---

#### 5. Capa de Seguridad (Security)

- **Descripción:** Maneja la autenticación y autorización del sistema. Implementa seguridad mediante JWT.
- **Componentes:**
  - Clases ubicadas en `src/main/java/edu/escuelaing/arep/arep_taller_7/security`.
- **Responsabilidad:**
  - Proteger los endpoints mediante autenticación y autorización.
  - Generar y validar tokens JWT.
  - Configurar políticas de acceso y CORS.

---

#### 6. Capa de Entidades (Model)

- **Descripción:** Define las entidades del sistema que representan las tablas de la base de datos.
- **Componentes:**
  - Clases ubicadas en `src/main/java/edu/escuelaing/arep/arep_taller_7/model`.
- **Responsabilidad:**
  - Mapear las tablas de la base de datos a objetos Java.
  - Definir relaciones entre entidades.

---

### Diagrama del proyecto

## Video del AREP-Twitter

Puedes ver el video del taller funcionando en el siguiente enlace:
## Pruebas Unitarias

### PostTest

Este archivo contiene pruebas unitarias para verificar el comportamiento de las capas de servicio (`PostServiceImpl`) y controlador (`PostControllerImpl`) de la aplicación. A continuación, se describen brevemente las pruebas realizadas:

#### Pruebas para `PostServiceImpl`
Estas pruebas verifican la lógica de negocio y la interacción con el repositorio (`PostRepository`).

1. **`shouldcreatePost`**: Verifica que un post válido se cree correctamente.
2. **`shouldNotCreatePost`**: Verifica que no se cree un post con contenido inválido.
3. **`shouldGetPostById`**: Verifica que se obtenga un post por su ID.
4. **`shouldNotGetPostById`**: Verifica que se lance una excepción si el post no existe.
5. **`shouldUpdatePost`**: Verifica que un post existente se actualice correctamente.
6. **`shouldDeletePost`**: Verifica que un post existente se elimine correctamente.
7. **`shouldGetAllPosts`**: Verifica que se obtengan todos los posts almacenados.

#### Pruebas para `PostControllerImpl`
Estas pruebas verifican la capa de controlador y su interacción con el servicio (`PostService`).

1. **`shouldCreatePostEntity`**: Verifica que el controlador cree un post.
2. **`shouldNotCreatePostEntity`**: Verifica que el controlador maneje errores al crear un post.
3. **`shouldGetPostEntity`**: Verifica que el controlador obtenga un post por su ID.
4. **`shouldNotGetPostEntity`**: Verifica que el controlador maneje errores al buscar un post.
5. **`shouldUpdatePostEntity`**: Verifica que el controlador actualice un post correctamente.
6. **`shouldDeletePostEntity`**: Verifica que el controlador elimine un post correctamente.
7. **`shouldGetAllPostEntities`**: Verifica que el controlador obtenga todos los posts.

![image](https://github.com/user-attachments/assets/041b4b9a-fd06-4d14-890f-0239b6d90d84)
![image](https://github.com/user-attachments/assets/976ab055-b286-467c-94de-0627bce32131)



## Autores

**Milton Gutierrez, Samuel Rojas, Laura Gil** - Desarrolladores y autores del proyecto.
