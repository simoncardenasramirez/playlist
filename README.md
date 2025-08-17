# Playlist API (Spring Boot + JPA + H2 + JWT)

API REST para gestionar **listas de reproducción** y sus **canciones**, con autenticación **JWT** y base de datos en memoria **H2**.

## 🚀 Tecnologías
- Java 21
- Spring Boot 3 (Web, Security, Data JPA)
- H2 (runtime, en memoria)
- Maven
- JWT

## 📦 Endpoints principales

### Autenticación
- `POST /auth/register` – Registra usuario (body: `{"email","password"}`)  
- `POST /auth/login` – Devuelve `{"token": "..."}`

> Usa el token en los demás endpoints con `Authorization: Bearer <token>`.

### Listas de reproducción
- `POST /lists` – Crea lista (201 Created).  
  Body:
  ```json
  {
    "nombre": "Mi Lista 1",
    "descripcion": "Demo",
    "canciones": [
      { "titulo": "Song A", "artista": "Artist A", "album": "Album A", "anno": "2024", "genero": "Pop" }
    ]
  }


GET /lists – Retorna todas las listas.

GET /lists/{listName} – Retorna la lista por nombre. (404 si no existe)

DELETE /lists/{listName} – Elimina la lista. (204 No Content o 404)

🔐 Credenciales de prueba

Usuario: demo@correo.com

Password: Secreta123

Si no existen en tu BD H2, puedes registrarlos con /auth/register.

🧪 Colección Postman

Archivo JSON en: postman/playlist-api.postman_collection.json

(Opcional) Link público Postman: [colócala aquí si la generas]

⚙️ Configuración
Requisitos

Java 21

Maven 3.9+

Cómo correr
mvn spring-boot:run


La app queda en http://localhost:8080.

H2 Console

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:playlistsdb

User: sa

Pass: (vacío)

🔑 JWT

El login (/auth/login) devuelve {"token":"..."}.

Envíalo como header: Authorization: Bearer <token>.

🌐 CORS

Permitido por configuración para http://localhost:4200 (Angular).
Si quieres ajustar, revisa el SecurityConfig y/o el bean CorsConfigurationSource.

🧰 Scripts útiles
# Compilar y tests
mvn clean verify

# Arrancar
mvn spring-boot:run

🧪 Pruebas unitarias

Incluidas con JUnit/Mockito. Ejecuta:

mvn test

🗂️ Estructura (resumen)

src/main/java/com/priv/co/playlist__api

  ├─ controller
  
  ├─ service
  
  ├─ persistence
  
  │   ├─ entity
  
  │   └─ repository
  
  ├─ config/security
  
  └─ PlaylistApiApplication.java

src/main/resources
  └─ application.properties

postman/
  └─ playlist-api.postman_collection.json
