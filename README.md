# FoodHub-Order-Management-REST-API
Tech Stack: Java | Spring Boot | Spring Data JPA | Hibernate | MySQL | RESTful APIs

Highlights:

-Developed a modular backend service for restaurant, menu, and order management, implementing full CRUD workflows, validation, and relational data mapping using Spring Boot and MySQL.

-Engineered a clean, layered architecture with Controller–Service–Repository separation, custom exception handling, and standardized HTTP responses (201/400/404/409) to ensure robust error reporting and production-grade API usability.

API Design

-Create Order – POST /api/orders
-Get Order by ID – GET /api/orders/{orderId}
-Update Order Status – PUT /api/orders/{orderId}/status
-Get Menu for Restaurant – GET /api/restaurants/{restaurantId}/menu
-Delete/Cancel Order – DELETE /api/orders/{orderId}
