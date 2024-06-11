# JTWSpringboot
## Tecnologías
- **Spring Boot**: Framework para el desarrollo de aplicaciones Java.
- **Java 17**: Versión del lenguaje de programación Java utilizada.
- **Spring Security**: Módulo de Spring para la autenticación y autorización.
- **JWT** : Utilizacion de token para la seguridad
- **Postman**: Herramienta para probar y desarrollar APIs.
- **Lombok**: Biblioteca que reduce el código en Java.
- **H2**: Base de datos embebida para pruebas y desarrollo.
- **Swagger**: Herramienta para documentar APIs.
- **IntelliJ IDEA**: Entorno de desarrollo integrado (IDE) para Java.
- **Junit5** : Para las pruebas unitarias (En desarrollo)
- **mvnw ** : Para la compilacion

## Swagger
> Dirección base para la visualización de las APIs:
> [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Endpoints

#### Registro de Usuario
> URL: `http://localhost:8080/auth/register`
> Método: POST

Utiliza Postman para enviar la siguiente estructura JSON:

```json
{
    "username": "Nombre",
    "usuario": "FEDE",
    "correo": "correo@codrsre.com",
    "password": "asssdsdad"
}
```
#### Iniciar seccion

> URL: `http://localhost:8080/auth/login`
> Método: POST

 ```json
{
    "username":"Nombre",
    "usuario":"FEDE",
    "correo":"correo@codrsre.com",
    "password":"asssdsdad"
}
```
> Retorna Token
## Construccion (Build)
- Se tienen 2 opciones montar el proyecto en inteliji y correr como otro
#### Opcion alternativa
- Descargar repo  
 ```cmd
git clone https://github.com/fabian3117/JTWSpringboot/tree/master.git
```
- Descomprimir y en carpeta raiz ( Donde esta el gradlew)
- Ejecutar
##### Se recomienda usar inteliji por que puede dar problemas por db H2
```cmd

mvnw clean package -DskipTests
java -jar target/user-api-0.0.1-SNAPSHOT.jar
```

### Notas
Caracteristicas que concideraria añadir
- *Springboot mail* Enviar mails en caso de registro para la gesttion de usuarios y habilitarlos
- *Thymeleaft* Poder incorporar front procesado internamente desde el back
- *Docker* Utilizar una DB con docker
Verificaciones de datos:
- Verifica que el correo tenga @ y minimo un "."
- La clave no puede ser null
- Utiliza transaccional para evitar malas escrituras en db
- Rol Establece diferente usuario desde ADMIN, USER lo que permitiria poder modificar otros usuarios como reiniciando su clave
  
