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
