# 🚀API REST: Sistema de Gestión de Préstamos
Proyecto de backend desarrollado con Java y el ecosistema Spring (Boot, Security, Data). Está enfocado en la implementación de una API RESTful robusta para simular la lógica de negocio de un sistema financiero de préstamos.

---

## 💻 Stack Tecnológico
| Tecnología |   |
| :--- | :--- |
| **Lenguaje** | Java 17+ |
| **Frameworks** | Spring Boot, Spring Security, Spring Data JPA |
| **Documentacion** | SpringDoc (OpenAPI 3 / Swagger UI) |
| **Utilidades** | Lombok |
| **Control de versiones** | Git |

---

## 🛠️ Estructura del Proyecto
El proyecto implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad Cliente y la gestión de creación y lectura para la entidad Préstamo.

---
## 📄 Documentación Interactiva (Swagger UI)

La API cuenta con documentación interactiva y auto-generada utilizando **SpringDoc**.

Una vez que la aplicación se inicie, se puede acceder a la interfaz de usuario de **Swagger UI** para visualizar y probar todos los endpoints:

> 🔗 **URL de Acceso:** `http://localhost:8080/swagger-ui.html`

---
## 🔒 Seguridad (HTTP Basic)

Todos los endpoints de la API están protegidos y requieren autenticación **HTTP Basic**.

* **Usuario:** `admin`
* **Contraseña:** `12345`

---

## 🌐 Endpoints de la API 
| Método HTTP | Endpoint | Objetivo | Notas de Uso |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/clientes` | **Creación** de un nuevo cliente. | El `id` se ignora y se genera automáticamente. |
| **GET** | `/api/clientes` | **Consulta** la lista de clientes activos. | No devuelve clientes con baja = true. |
| **GET** | `/api/clientes/{id}` | **Consulta** una cliente específico por su ID. | Solo devuelve si el cliente está activo.|
| **PUT** | `/api/clientes/{id}` | **Actualiza** completamente los datos de un cliente. | Requiere el objeto JSON completo del cliente. |
| **DELETE** | `/api/clientes/{id}` | **Baja logica** de un cliente por su Id. | Cambia el estado a baja = true, no lo borra. |
| **POST** | `/api/clientes/{clienteId}/prestamos` | **Creación** de un nuevo préstamo para un cliente. |El clienteId de la URL se usa para asociarlo. |
| **GET** | `/api/clientes/{clienteId}/prestamos` | **Consulta** la lista de préstamos de un cliente. |  |
---

### Ejemplo de Solicitud POST Exitosa

```json
{
    "nombre": "Juan",
    "apellido": "Cruz",
    "dni": "11111111"
}
