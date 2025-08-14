# 🏠 Arrenda Lite

Sistema simplificado de arriendo de propiedades temporales con validaciones de fechas, descuentos y cancelación de reservas.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos](#-requisitos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Uso](#-uso)
- [API Documentation](#-api-documentation)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Endpoints](#-endpoints)
- [Base de Datos](#-base-de-datos)
- [Contribución](#-contribución)
- [Licencia](#-licencia)

## 🎯 Descripción

Arrenda Lite es una API REST desarrollada en Spring Boot que permite gestionar el arriendo de propiedades temporales. El sistema incluye funcionalidades para:

- Gestionar propiedades disponibles
- Crear y gestionar reservas
- Validar fechas de disponibilidad
- Calcular precios automáticamente
- Cancelar reservas
- Documentación automática con Swagger/OpenAPI

## ✨ Características

- ✅ **API REST completa** con Spring Boot
- ✅ **Documentación automática** con Swagger/OpenAPI 3.0
- ✅ **Validaciones de fechas** y disponibilidad
- ✅ **Cálculo automático de precios**
- ✅ **Gestión de cancelaciones**
- ✅ **Base de datos PostgreSQL** (configurable)
- ✅ **Validaciones de entrada** con Bean Validation
- ✅ **Manejo de excepciones** global
- ✅ **Lombok** para reducir código boilerplate
- ✅ **Maven** para gestión de dependencias

## 🛠 Tecnologías

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Web MVC**
- **PostgreSQL** (Base de datos principal)
- **H2 Database** (Base de datos en memoria para desarrollo)
- **Swagger/OpenAPI 3.0** (Documentación)
- **Lombok** (Reducción de código)
- **Maven** (Gestión de dependencias)
- **Hibernate** (ORM)

## 📋 Requisitos

- **Java 21** o superior
- **Maven 3.6+** (o usar el wrapper incluido)
- **PostgreSQL 12+** (para producción)
- **Git** (para clonar el repositorio)

## 🚀 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/pamelamichellga01/arrendalite.git
cd arrendalite
```

### 2. Configurar la Base de Datos

#### Opción A: PostgreSQL (Recomendado para producción)

1. Instalar PostgreSQL
2. Crear una base de datos:
```sql
CREATE DATABASE arrendalite;
CREATE USER arrendalite_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE arrendalite TO arrendalite_user;
```

3. Configurar `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/arrendalite
spring.datasource.username=arrendalite_user
spring.datasource.password=tu_password
```

#### Opción B: H2 (Para desarrollo/pruebas)

La aplicación incluye configuración para H2 en memoria. No requiere configuración adicional.

### 3. Compilar y Ejecutar

```bash
# Usando Maven wrapper
./mvnw clean compile
./mvnw spring-boot:run

# O usando Maven instalado
mvn clean compile
mvn spring-boot:run
```

## ⚙️ Configuración

### Variables de Entorno

```properties
# Configuración de la aplicación
spring.application.name=Arrenda Lite
server.port=8080

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/arrendalite
spring.datasource.username=postgres
spring.datasource.password=123456

# Configuración JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Perfiles de Configuración

- **dev**: Configuración para desarrollo con H2
- **prod**: Configuración para producción con PostgreSQL

```bash
# Ejecutar con perfil específico
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📖 Uso

### 1. Iniciar la Aplicación

```bash
./mvnw spring-boot:run
```

### 2. Acceder a la Documentación

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### 3. Probar los Endpoints

```bash
# Obtener todas las propiedades
curl http://localhost:8080/property

# Obtener una propiedad específica
curl http://localhost:8080/property/1

# Crear una reserva
curl -X POST http://localhost:8080/booking \
  -H "Content-Type: application/json" \
  -d '{
    "startDate": "2024-01-15",
    "endDate": "2024-01-20",
    "customerName": "Juan Pérez",
    "property": {"id": 1}
  }'

# Obtener todas las reservas
curl http://localhost:8080/booking

# Cancelar una reserva
curl -X PUT http://localhost:8080/booking/1/cancel
```

## 📚 API Documentation

La aplicación incluye documentación automática generada con Swagger/OpenAPI 3.0.

### Acceso a la Documentación

- **URL**: http://localhost:8080/swagger-ui.html
- **Especificación OpenAPI**: http://localhost:8080/api-docs

### Características de la Documentación

- ✅ Documentación interactiva
- ✅ Ejemplos de request/response
- ✅ Validación de esquemas
- ✅ Pruebas desde la interfaz
- ✅ Códigos de respuesta HTTP
- ✅ Descripción detallada de endpoints

## 📁 Estructura del Proyecto

```
arrendalite/
├── src/
│   ├── main/
│   │   ├── java/com/arrendalite/
│   │   │   ├── ArrendaLiteApplication.java
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── BookingController.java
│   │   │   │   └── PropertyController.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── model/
│   │   │   │   ├── Booking.java
│   │   │   │   └── Property.java
│   │   │   ├── repository/
│   │   │   │   ├── BookingRepository.java
│   │   │   │   └── PropertyRepository.java
│   │   │   └── service/
│   │   │       ├── BookingService.java
│   │   │       └── PropertyService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
└── pom.xml
```

## 🔗 Endpoints

### Propiedades (`/property`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/property` | Obtener todas las propiedades |
| GET | `/property/{id}` | Obtener propiedad por ID |

### Reservas (`/booking`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/booking` | Crear nueva reserva |
| GET | `/booking` | Obtener todas las reservas |
| PUT | `/booking/{id}/cancel` | Cancelar reserva |

## 🗄️ Base de Datos

### Modelos

#### Property (Propiedad)
```java
- id: Long (PK)
- name: String
- dailyPrice: Double
```

#### Booking (Reserva)
```java
- id: Long (PK)
- startDate: LocalDate
- endDate: LocalDate
- totalPrice: Double
- isCancelled: Boolean
- customerName: String
- property: Property (FK)
```

### Datos de Prueba

El archivo `data.sql` incluye datos de ejemplo que se cargan automáticamente:

```sql
INSERT INTO property (name, daily_price) VALUES 
('Casa en la playa', 150.00),
('Apartamento en el centro', 100.00),
('Cabaña en la montaña', 200.00);
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Guías de Contribución

- Sigue las convenciones de código Java
- Agrega tests para nuevas funcionalidades
- Actualiza la documentación según sea necesario
- Usa mensajes de commit descriptivos

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 📞 Contacto

- **Desarrollador**: [Pamela Galvis]
- **Email**: [pamelagalvisdev@gmail.com]
- **GitHub**: [https://github.com/pamelamichellga01]

## 🙏 Agradecimientos

- Spring Boot Team por el excelente framework
- Swagger/OpenAPI por la documentación automática
- La comunidad de desarrolladores Java

---

**¡Gracias por usar Arrenda Lite! 🏠✨**
