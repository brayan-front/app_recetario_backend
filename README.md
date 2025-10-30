# app_recetario_backend

Proyecto base en Spring Boot (Java 17) para módulos de Recipes e Ingredients.

## Cómo usar

1. Asegúrate de tener Java 17 y Maven instalados y `mvn` disponible en PATH.
2. Desde la raíz del proyecto, ejecutar:
   ```bash
   mvn spring-boot:run
   ```
3. Endpoints:
   - POST /recipes
   - GET /recipes
   - GET /recipes/{id}
   - PUT /recipes/{id}
   - DELETE /recipes/{id}
   - POST /ingredients
   - GET /ingredients
   - GET /ingredients/{id}
   - PUT /ingredients/{id}
   - DELETE /ingredients/{id}
   - GET /units

H2 console: http://localhost:8080/h2-console (JDBC: jdbc:h2:mem:recetario)
